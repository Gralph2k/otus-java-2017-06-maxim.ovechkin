package testingClass;
import annotations.*;
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
    }

    @Test
    public void test11(){
        System.out.println(this.getClass().getName()+".test11()");
    }

    @Test
    public void test12(){
        System.out.println(this.getClass().getName()+".test12()");
    }

    @Test
    public void test13(){
        System.out.println(this.getClass().getName()+".test13()");
    }


}
