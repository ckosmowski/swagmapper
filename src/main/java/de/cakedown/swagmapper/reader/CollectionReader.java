package de.cakedown.swagmapper.reader;

import de.cakedown.swagmapper.mappable.CollectionMapable;
import de.cakedown.swagmapper.mappable.Mapable;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

public class CollectionReader implements Reader {
    @Override
    public void read(Object subject, Consumer<Mapable> consumer) {
        Collection sourceCollection = (Collection) subject;
        int i = 0;
        Iterator it = sourceCollection.iterator();
        while (it.hasNext()){
            Object element = it.next();
            CollectionMapable mapable = new CollectionMapable();
            mapable.setName(i + "");
            mapable.setType(subject.getClass());
            mapable.setValue(element);
            consumer.accept(mapable);
            i++;
        }
    }
}
