package de.cakedown.swagmapper.resolve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ClassHierarchyResolver {
    Logger logger = LoggerFactory.getLogger(ClassHierarchyResolver.class);
    private Collection<Class<?>> classList;

    public ClassHierarchyResolver(Collection<Class<?>> classList){
        this.classList = classList;
    }

    public Optional<Class<?>> findMatchFor(Class<?> subject) {
        logger.debug("Searching match for: {}", subject.getName());
        List<Class<?>> matching = new ArrayList<>();
        for (Class<?> clazz : classList) {
            logger.debug("Comparing with: {}", clazz.getName());
            if (clazz.isAssignableFrom(subject)) {
                logger.debug("Matched: {} and: {}", subject.getName(), clazz.getName());
                matching.add(clazz);
            }
        }
        Optional<Class<?>> key = matching.stream().sorted(new ClassHierarchyComparator()).findFirst();
        return key;
    }
}
