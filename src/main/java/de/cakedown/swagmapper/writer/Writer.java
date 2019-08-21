package de.cakedown.swagmapper.writer;

import de.cakedown.swagmapper.SwagMapper;
import de.cakedown.swagmapper.mappable.Mapable;

public interface Writer {
    public void write(Object target, Mapable mapable, SwagMapper mapper);
}
