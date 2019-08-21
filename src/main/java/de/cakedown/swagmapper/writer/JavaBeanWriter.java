package de.cakedown.swagmapper.writer;

import de.cakedown.swagmapper.SwagMapper;
import de.cakedown.swagmapper.mappable.Mapable;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class JavaBeanWriter implements Writer {
    Logger logger = LoggerFactory.getLogger(JavaBeanWriter.class);
    @Override
    public void write(Object target, Mapable mapable, SwagMapper mapper) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(target.getClass());
            Optional<PropertyDescriptor> targetProperty = findProperty(mapable.getName(), beanInfo);
            if (!targetProperty.isPresent()) {
                return;
            }
            Class sourceType = mapable.getType();
            if (String.class.isAssignableFrom(sourceType) || ClassUtils.isPrimitiveOrWrapper(sourceType) || mapable.getValue() == null || targetProperty.get().getPropertyType().isAssignableFrom(sourceType)) {
                BeanUtils.setProperty(target, mapable.getName(), mapable.getValue());
            } else {
                logger.debug("Mapping recursively: " + mapable.getName());
                PropertyDescriptor targetPropertyDescriptor = targetProperty.get();
                // create object and map
                Object destinationObject = null;
                Object targetValue = targetPropertyDescriptor.getReadMethod().invoke(target);
                destinationObject = targetValue == null ? mapper.provideNew(targetPropertyDescriptor.getPropertyType()).get() : targetValue;
                mapper.map(mapable.getValue(), destinationObject, null);
                BeanUtils.setProperty(target, mapable.getName(), destinationObject);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private Optional<PropertyDescriptor> findProperty(String name, BeanInfo beanInfo) {
        return Arrays.stream(beanInfo.getPropertyDescriptors()).filter(info -> info.getName().equals(name)).findFirst();
    }
}
