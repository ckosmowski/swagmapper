package de.cakedown.swagmapper.testmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeCollection {
    private List<String> elements = new ArrayList<>();

    public void add(String element) {
        elements.add(element);
    }

    public List<String> getElements() {
        return elements;
    }

    public void setElements(List<String> elements) {
        this.elements = elements;
    }
}
