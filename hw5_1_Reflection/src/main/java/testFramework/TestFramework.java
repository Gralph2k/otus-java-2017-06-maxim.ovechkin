package testFramework;

import common.ReflectionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by maxim.ovechkin on 04.07.2017.
 */
public class TestFramework {
    static Logger logger = LoggerFactory.getLogger(TestFramework.class);
    static Tests tests = new Tests();

    private TestFramework() {
    }

    public static void run(String packageName) {
        collectTests(packageName);
        runTests();
        showResult();
    }

    private static void showResult() {
        int successCnt = 0;
        int failCnt = 0;
        int totalCnt = 0;
        String succesList = "";
        String failedList = "";
        for (SingleTest test : tests.tests) {
            totalCnt++;
            if (test.succeseful) {
                successCnt++;
                succesList += String.format("\t%s.%s\n", test.getClass_().getName(), test.method.getName());
            } else {
                failCnt++;
                failedList += String.format("\t%s.%s\n", test.getClass_().getName(), test.method.getName(), test.getMessage());
            }
        }
        String result = String.format("\nTests runned %d, successeful %d, failed %d", totalCnt, successCnt, failCnt);
        if (successCnt > 0) {
            result += String.format("\nSuccesseful tests:\n%s", succesList);
        }
        if (failCnt > 0) {
            result += String.format("\nFailed tests:\n%s", failedList);
        }
        logger.info(result);
    }

    protected static void runTests() {
        for (SingleTest test : tests.tests) {
            Object classObject = ReflectionHelper.instantiate(test.class_);
            runTestsSet(classObject, tests.beforeTests);
            runTest(classObject, test);
            runTestsSet(classObject, tests.afterTests);
        }
    }

    protected static void runTestsSet(Object classObject, Set<SingleTest> testsSet) {
        for (SingleTest singleTest : testsSet) {
            if (singleTest.getClass_() == classObject.getClass()) {
                runTest(classObject, singleTest);
            }
        }
    }

    protected static boolean runTest(Object classObject, SingleTest test) {
        Method method = test.method;
        int paramCount = method.getParameterCount();
        if (paramCount == 0) {
            logger.info("  run {}", method.getName());
            try {
                ReflectionHelper.callMethod(classObject, method.getName());
                test.setSucceseful(true);
            } catch (Exception e) {
                test.setSucceseful(false);
                test.setMessage(e.getMessage() + "\n" + e.getStackTrace());
            }
        }
        return test.getSucceseful();
    }

    protected static int fillMethodsAnnotatedWith(String packageName, Class class_, Class<? extends Annotation> annotation) {
        Set<Method> methods;
        methods = ReflectionHelper.getMethodsAnnotatedWith(class_, annotation);
        int methodsCnt=0;
        for (Method method : methods) {
            if (method.getParameterCount() == 0) {
                methodsCnt++;
                if (annotation == annotations.Before.class) {
                    tests.beforeTests.add(new SingleTest(packageName, class_, method));
                } else if (annotation == annotations.Test.class) {
                    tests.tests.add(new SingleTest(packageName, class_, method));
                } else if (annotation == annotations.After.class) {
                    tests.afterTests.add(new SingleTest(packageName, class_, method));
                }
            } else {
                logger.debug("  ignore method {}.{} with non empty parameters", class_.getName(), method.getName());
            }
        }
        return methodsCnt;
    }

    protected static void collectTests(String packageName) {
        Set<Class<? extends Object>> classesSet = ReflectionHelper.getClasses(packageName);
        for (Class class_ : classesSet) {
            fillMethodsAnnotatedWith(packageName, class_, annotations.Before.class);
            fillMethodsAnnotatedWith(packageName, class_, annotations.Test.class);
            fillMethodsAnnotatedWith(packageName, class_, annotations.After.class);

        }
    }
}
