package de.cakedown.swagmapper.testmodel;

import de.cakedown.swagmapper.SwagMapper;
import de.cakedown.swagmapper.mappable.CollectionMapable;
import de.cakedown.swagmapper.mappable.Mapable;
import de.cakedown.swagmapper.writer.Writer;

public class FakeCollectionWriter implements Writer {
    @Override
    public void write(Object target, Mapable mapable, SwagMapper mapper) {
        if (mapable instanceof CollectionMapable){
            ((FakeCollection)target).add(mapable.getValue().toString());
        }
    }
}
