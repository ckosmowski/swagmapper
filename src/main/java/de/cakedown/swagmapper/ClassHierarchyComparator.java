package de.cakedown.swagmapper;

import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.StreamSupport;

public class ClassHierarchyComparator implements Comparator<Class> {
    Logger logger = LoggerFactory.getLogger(ClassHierarchyComparator.class);
    private static Map<Class, Long> hierarchyDepth = new ConcurrentHashMap<>();

    Function<Class, Long> hierarchyDepthCalculator = c ->
            StreamSupport.stream(ClassUtils.hierarchy(c, ClassUtils.Interfaces.INCLUDE).spliterator(),false).count();

    @Override
    public int compare(Class o1, Class o2) {
        Long h2 = hierarchyDepth.computeIfAbsent(o1, hierarchyDepthCalculator);
        Long h1 = hierarchyDepth.computeIfAbsent(o1, hierarchyDepthCalculator);
        logger.debug("Hierarchy depth of {} is {}", o1.getName(), h1);
        logger.debug("Hierarchy depth of {} is {}", o2.getName(), h2);
        long value = h2 - h1;
        StreamSupport.stream(ClassUtils.hierarchy(o1, ClassUtils.Interfaces.INCLUDE).spliterator(),false).count();
        return value < 0 ? -1 : value > 0 ? 1 : 0;
    }
}
