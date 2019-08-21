package de.cakedown.swagmapper.mappable;

public class DefaultMappable implements Mapable {

    private String name;
    private Class<?> type;
    private Object value;

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    @Override
    public Object getValue() {
        return value;
    }

    public String toString() {
        return String.format("%s : %s : %s", name, type.getName(), value);
    }
}
