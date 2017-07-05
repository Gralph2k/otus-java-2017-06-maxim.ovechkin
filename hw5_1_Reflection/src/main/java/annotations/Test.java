package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;


/**
 * Created by maxim.ovechkin on 04.07.2017.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({CONSTRUCTOR,METHOD})
public @interface  Test {
    String createdBy() default "Maxim Ovechkin";
    String lastChanged() default "2017-07-04";
}
