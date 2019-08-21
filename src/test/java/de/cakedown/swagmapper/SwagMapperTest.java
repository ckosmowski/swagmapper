package de.cakedown.swagmapper;


import de.cakedown.swagmapper.testmodel.FakeCollection;
import de.cakedown.swagmapper.testmodel.FakeCollectionWriter;
import de.cakedown.swagmapper.testmodel.Sub;
import de.cakedown.swagmapper.testmodel.Sub2;
import de.cakedown.swagmapper.testmodel.Test2;
import de.cakedown.swagmapper.testmodel.TestModel;
import de.cakedown.swagmapper.testmodel.TestModel3;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.junit.MatcherAssert.assertThat;


class SwagMapperTest {
    @Test
    void testSimpleMapping() {
        TestModel t = new TestModel();
        t.setName("aTestName");

        Test2 target = new Test2();
        SwagMapper swagMapper = new SwagMapper();
        swagMapper.map(t, target, null);
        assertThat(target.getName(), is(t.getName()));
    }

    @Test
    void testDeepMapping() {
        TestModel t = new TestModel();
        t.setName("aTestName");
        Sub sub = new Sub();
        sub.setSubName("aSubName");
        t.setSub(sub);

        Test2 target = new Test2();
        SwagMapper swagMapper = new SwagMapper();
        swagMapper.map(t, target, null);
        assertThat(target.getName(), is(t.getName()));
        assertThat(target.getSub(), is(not(nullValue())));
        assertThat(target.getSub().getSubName(), is("aSubName"));
    }

    @Test
    void testDeepMappingTargetPresent() {
        TestModel t = new TestModel();
        t.setName("aTestName");
        Sub sub = new Sub();
        sub.setSubName("aSubName");
        t.setSub(sub);

        Test2 target = new Test2();
        Sub2 sub2 = new Sub2();
        target.setSub(sub2);

        SwagMapper swagMapper = new SwagMapper();
        swagMapper.map(t, target, null);
        assertThat(target.getName(), is(t.getName()));
        assertThat(target.getSub(), is(not(nullValue())));
        assertThat(target.getSub(), is(sub2));
        assertThat(target.getSub().getSubName(), is("aSubName"));
    }

    @Test
    void testCollectionMappingCompatible() {
        TestModel t = new TestModel();
        t.setName("aTestName");

        Test2 target = new Test2();
        target.setList(null);

        SwagMapper swagMapper = new SwagMapper();
        long start = System.currentTimeMillis();
        swagMapper.map(t, target, null);
        long end = System.currentTimeMillis();

        assertThat(target.getList(), is(not(nullValue())));
        assertThat(target.getList().size(), is(2));
    }

    @Test
    void testCollectionMappingSpecial() {
        TestModel t = new TestModel();
        t.setName("aTestName");

        TestModel3 target = new TestModel3();
        target.setList(null);

        SwagMapper swagMapper = new SwagMapper();
        swagMapper.addWriter(FakeCollection.class, FakeCollectionWriter.class);

        swagMapper.map(t, target, null);

        assertThat(target.getList(), is(not(nullValue())));
        assertThat(target.getList().getElements().size(), is(2));
    }

    @Test
    void testPerformance() {
        TestModel t = new TestModel();
        t.setName("aTestName");

        Test2 target = new Test2();
        target.setList(null);

        SwagMapper swagMapper = new SwagMapper();
        long start = System.currentTimeMillis();
        swagMapper.map(t, target, null);
        long end = System.currentTimeMillis();
        assertThat((end-start), lessThan(300L));
        System.out.println("First duration:" + (end-start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++){
            t = new TestModel();
            target = new Test2();
            swagMapper.map(t, target, null);
        }
        end = System.currentTimeMillis();
        assertThat((end-start), lessThan(300L));
        System.out.println("Batch duration:" + (end-start));
    }
}