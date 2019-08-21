package de.cakedown.swagmapper.reader;

import de.cakedown.swagmapper.mappable.Mapable;

import java.util.function.Consumer;

public interface Reader {
    public void read(Object subject, Consumer<Mapable> consumer);
}
