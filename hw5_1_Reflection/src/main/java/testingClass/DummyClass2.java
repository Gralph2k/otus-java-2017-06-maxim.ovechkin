package testingClass;
import annotations.*;
/**
 * Created by maxim.ovechkin on 04.07.2017.
 */
public class DummyClass2 {
    @Before
    public void before2(){
        System.out.println(this.getClass().getName()+".before()");
    }

    @After
    public void after2(){
        System.out.println(this.getClass().getName()+".after()");
    }

    @Test
    public void test2_NoParameter() {
        System.out.println(this.getClass().getName()+".test2_NoParameter()");
    }

    @Test
    public void test2_NoParameter2_FailedTest(){
        System.out.println(this.getClass().getName()+".test2_NoParameter2()");
        throw new ArithmeticException("Failed test");
    }

    @Test
    public void test2_OneParameter(Integer arg){
        System.out.println(this.getClass().getName()+".test2_OneParameter():"+arg);
    }

    @Test
    public void test2_TwoParameters(Integer arg1, String arg2){
        System.out.println(this.getClass().getName()+".test2_TwoParameters():"+arg1+" "+arg2);
    }

    @Test
    public void test2_VarParameters(Integer... args){
        System.out.println(this.getClass().getName()+".test2_VarParameters()"+args.toString());
    }

}
