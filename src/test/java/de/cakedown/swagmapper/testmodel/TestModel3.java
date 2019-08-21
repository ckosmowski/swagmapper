package de.cakedown.swagmapper.testmodel;

public class TestModel3 {
    private String name;
    private int intvalue = 12;
    private Integer anInt;
    private FakeCollection list;
    private Sub2 sub = new Sub2();

    public FakeCollection getList() {
        return list;
    }

    public void setList(FakeCollection list) {
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
