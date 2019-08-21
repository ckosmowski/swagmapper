package de.cakedown.swagmapper.mappable;

public interface Mapable {
    public String getName();
    public Class<?> getType();
    public Object getValue();
    public void setName(String name);
    public void setType(Class<?> type);
    public void setValue(Object value);
}
