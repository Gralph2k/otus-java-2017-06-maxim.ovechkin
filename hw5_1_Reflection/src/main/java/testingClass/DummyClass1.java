package testingClass;
import annotations.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by maxim.ovechkin on 04.07.2017.
 */
public class DummyClass1 {
    @Before
    public void before11(){
        System.out.println(this.getClass().getName()+".before11()");
    }

    @After
    public void after11(){
        System.out.println(this.getClass().getName()+".after11()");
    }

    @After
    public void after12(){
        System.out.println(this.getClass().getName()+".after12()");
        assertEquals(1,1);
    }

    @Test
    public void test11_FailAsset(){
        System.out.println(this.getClass().getName()+".test11_FailAssert()");
        assertEquals(1,2);
    }

    @Test
    public void test12_successTest(){
        System.out.println(this.getClass().getName()+".test12_successTest()");
    }

    @Test
    public void test13(){
        System.out.println(this.getClass().getName()+".test13()");
    }


}
