package de.cakedown.swagmapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static class Sub {
        private String subName = "Blubs";

        public String getSubName() {
            return subName;
        }

        public void setSubName(String subName) {
            this.subName = subName;
        }
    }

    static class Test implements TestInterface, Serializable {
        private String name;
        private int intvalue = 12;
        private Integer anInt;
        private Sub sub = new Sub();

        private List<String> list = new ArrayList<>();
        public Test() {
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

        public Sub getSub() {
            return sub;
        }

        public void setSub(Sub sub) {
            this.sub = sub;
        }
    }

    static class TestDerived extends Test {

    }

    static class Test2 {
        private String name;
        private int intvalue = 12;
        private Integer anInt;
        private Sub sub = new Sub();

        private List<String> list = new ArrayList<>();
        public Test2() {
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

        public Sub getSub() {
            return sub;
        }

        public void setSub(Sub sub) {
            this.sub = sub;
        }
    }

    public static void main(String args[]) {
        SwagMapper mapper = new SwagMapper();
        Test t = new TestDerived();
        Test2 t2 = new Test2();
        mapper.map(t, t2);
    }
}
