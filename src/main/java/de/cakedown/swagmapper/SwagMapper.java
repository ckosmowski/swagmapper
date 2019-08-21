package de.cakedown.swagmapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;

public class SwagMapper {

    Logger logger = LoggerFactory.getLogger(SwagMapper.class);

    private static Map<Class, Class<? extends Reader>> readerMap = new LinkedHashMap<>();
    static {
        readerMap.put(Serializable.class, SpecialReader.class);
        readerMap.put(TestInterface.class, SpecialReader.class);
        readerMap.put(Main.Test.class, SpecialReader.class);
        readerMap.put(Main.TestDerived.class, SpecialReader.class);
    }

    private <T> T findMatching(Object toRead, Map<Class, T> map, T defaultElement) {
        Class readClass = toRead.getClass();
        logger.debug("Searching reader for: {}", readClass.getName());
        T foundElement = null;
        List<Class> matching = new ArrayList<>();
        for (Class clazz : map.keySet()) {
            logger.debug("Comparing with: {}", clazz.getName());
            if (clazz.isAssignableFrom(toRead.getClass())) {
                logger.debug("Matched: {} and: {}", readClass.getName(), clazz.getName());
                matching.add(clazz);
            }
        }
        Optional<Class> key = matching.stream().sorted(new ClassHierarchyComparator()).findFirst();
        if (!key.isPresent()){
            return defaultElement;
        }
        logger.debug("Result: {}", key);
        return map.get(key);
    }


    public <T> T map(Object source, T destination) {
        findMatching(source, readerMap, null);
        return destination;
    }
}
