package de.cakedown.swagmapper;

import de.cakedown.swagmapper.reader.CollectionReader;
import de.cakedown.swagmapper.reader.JavaBeanReader;
import de.cakedown.swagmapper.reader.Reader;
import de.cakedown.swagmapper.resolve.ClassHierarchyResolver;
import de.cakedown.swagmapper.writer.JavaBeanWriter;
import de.cakedown.swagmapper.writer.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.BiConsumer;

public class SwagMapper {

    Logger logger = LoggerFactory.getLogger(SwagMapper.class);

    private static Map<Class<?>, Class<? extends Reader>> readerMap = new LinkedHashMap<>();
    private static Map<Class<?>, Class<? extends Writer>> writerMap = new LinkedHashMap<>();

    private static Map<Class<?>, Class<?>> instatiationMap = new LinkedHashMap<>();

    private static Map<Class<?>, Class<? extends Reader>> readerMatchMap = new LinkedHashMap<>();
    private static Map<Class<?>, Class<? extends Writer>> writerMatchMap = new LinkedHashMap<>();

    static {
        readerMap.put(Collection.class, CollectionReader.class);
        instatiationMap.put(List.class, ArrayList.class);
    }

    private Class<?> findMatchingReader(Object toRead) {
        Class readClass = toRead.getClass();
        return readerMatchMap.computeIfAbsent(readClass, aClass -> {
            ClassHierarchyResolver resolver = new ClassHierarchyResolver(readerMap.keySet());

            Optional<Class<?>> key = resolver.findMatchFor(readClass);
            logger.debug("Result: {}", key);
            return readerMap.getOrDefault(key.orElse(null), JavaBeanReader.class);
        });
    }

    private Class<?> findMatchingWriter(Object toWrite) {
        Class writerClass = toWrite.getClass();
        return writerMatchMap.computeIfAbsent(writerClass, aClass -> {
            ClassHierarchyResolver resolver = new ClassHierarchyResolver(writerMap.keySet());

            Optional<Class<?>> key = resolver.findMatchFor(writerClass);
            Class resultClass = writerMap.getOrDefault(key.orElse(null), JavaBeanWriter.class);
            logger.debug("Result: {}, {}", key, resultClass);
            return resultClass;
        });
    }

    public void addWriter(Class<?> writableClass, Class<? extends Writer> writerClass) {
        writerMap.put(writableClass, writerClass);
        writerMatchMap.clear();
    }

    public void addReader(Class<?> readableClass, Class<? extends Reader> readerClass) {
        readerMap.put(readableClass, readerClass);
        readerMatchMap.clear();
    }


    public <T, E> T map( E source, T destination, BiConsumer<E, T> explicitMapping) {
        Class<?> readerClass = findMatchingReader(source);
        try {
            Reader reader = (Reader)readerClass.newInstance();
            Class<?> writerClass = findMatchingWriter(destination);
            Writer writer = (Writer)writerClass.newInstance();
            reader.read(source, mapable -> {
                Class<?> sourceType = mapable.getType();
                if (Class.class.isAssignableFrom(sourceType)){
                    return;
                }
                logger.debug(mapable.toString());
                writer.write(destination, mapable, this);
            });
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return destination;
    }

    public <T> Optional<T> provideNew(Class<T> type) {
        try {
            Class newType = instatiationMap.get(type);
            Class instatiateType = newType != null ? newType : type;
            return Optional.of((T)instatiateType.newInstance());
        } catch (InstantiationException e) {
            logger.warn("Unable to map {}. Cannot instatiate target type: {} ", type);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
