package org.k11techlab.framework.selenium.webuitestengine.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author interface.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Author {

    /**
     * Gets value of the author.
     *
     * @return string array of the author
     */
    String[] value() default "";
}
