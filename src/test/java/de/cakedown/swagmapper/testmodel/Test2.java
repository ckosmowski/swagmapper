package de.cakedown.swagmapper.testmodel;

import java.util.ArrayList;
import java.util.List;

public class Test2 {
    private String name;
    private int intvalue = 12;
    private Integer anInt;
    private Sub2 sub = new Sub2();

    private List<String> list = null;

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

    public Sub2 getSub() {
        return sub;
    }

    public void setSub(Sub2 sub) {
        this.sub = sub;
    }
}
