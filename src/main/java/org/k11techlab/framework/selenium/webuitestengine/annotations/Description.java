package org.k11techlab.framework.selenium.webuitestengine.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description annotations.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Description {

    /**
     * Gets value of the Description.
     *
     * @return string of the Description
     */
    String value() default "";
}
