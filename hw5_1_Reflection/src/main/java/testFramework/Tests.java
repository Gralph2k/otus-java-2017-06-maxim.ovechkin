package testFramework;



import java.util.HashSet;
import java.util.Set;

/**
 * Created by maxim.ovechkin on 05.07.2017.
 */
public class Tests {
    Set<SingleTest> beforeTests;
    Set<SingleTest> tests;
    Set<SingleTest> afterTests;

    public Tests() {
        beforeTests=new HashSet<SingleTest>();
        tests=new HashSet<SingleTest>();
        afterTests=new HashSet<SingleTest>();
    }

    public Set<SingleTest> getBeforeTest() {
        return beforeTests;
    }

    public  Set<SingleTest> getTests() {
        return tests;
    }

    public  Set<SingleTest> getAfterTest() {
        return afterTests;
    }



}
