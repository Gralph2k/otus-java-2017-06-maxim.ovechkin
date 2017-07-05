package TestFramework;

import sun.plugin2.message.Message;

import java.lang.reflect.Method;

/**
 * Created by maxim.ovechkin on 05.07.2017.
 */
class SingleTest {
    String packageName;
    Class class_;
    Method method;
    Boolean succeseful;
    String message;

    SingleTest(String packageName, Class class_, Method method) {
        this.packageName=packageName;
        this.class_=class_;
        this.method = method;
        this.message="";
        this.succeseful=false;
    }

    public Method getMethod() {
        return method;
    }

    public Boolean getSucceseful() {
        return succeseful;
    }

    void setSucceseful(Boolean succeseful) {
        this.succeseful = succeseful;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }

    public Class getClass_() {
        return class_;
    }


}
