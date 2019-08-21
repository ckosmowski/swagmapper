package de.cakedown.swagmapper.reader;

import de.cakedown.swagmapper.mappable.DefaultMappable;
import de.cakedown.swagmapper.mappable.Mapable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Consumer;

public class JavaBeanReader implements Reader {
    Logger logger = LoggerFactory.getLogger(JavaBeanReader.class);
    @Override
    public void read(Object subject, Consumer<Mapable> consumer) {
        outputMapable(subject, consumer);
    }

   public void outputMapable(Object subject, Consumer<Mapable> consumer) {
       try {
           BeanInfo beanInfo = Introspector.getBeanInfo(subject.getClass());
           PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

           for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
               Class<?> propertyType = propertyDescriptor.getPropertyType();
               Mapable mapable = new DefaultMappable();
               mapable.setName(propertyDescriptor.getName());
               mapable.setType(propertyType);
               Method reader = propertyDescriptor.getReadMethod();
               if (reader != null) {
                   try {
                    mapable.setValue(propertyDescriptor.getReadMethod().invoke(subject));
                   } catch (IllegalAccessException e){
                        logger.debug("Access Exception: " + mapable.getName(), e);
                   }
               }
               consumer.accept(mapable);
           }
       } catch (
               IntrospectionException | InvocationTargetException e) {
           e.printStackTrace();
       }
   }

}
