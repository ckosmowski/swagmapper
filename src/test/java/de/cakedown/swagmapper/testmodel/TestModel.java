package de.cakedown.swagmapper.testmodel;

import de.cakedown.swagmapper.TestInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestModel implements TestInterface, Serializable {
    private String name;
    private int intvalue = 12;
    private Integer anInt;
    private Sub sub;

    public Sub getSub() {
        return sub;
    }

    public void setSub(Sub sub) {
        this.sub = sub;
    }

    private String[] arr;

    public String[] getArr() {
        return arr;
    }

    public void setArr(String[] arr) {
        this.arr = arr;
    }

    public String getArr(int index) {
        return arr[index];
    }

    public void setArr(int index, String value) {
        arr[index] = value;
    }

    private List<String> list = new ArrayList<>();
    public TestModel() {
        list.add("A");
        list.add("B");
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIntvalue() {
        return intvalue;
    }

    public void setIntvalue(int intvalue) {
        this.intvalue = intvalue;
    }

    public Integer getAnInt() {
        return anInt;
    }

    public void setAnInt(Integer anInt) {
        this.anInt = anInt;
    }

}
