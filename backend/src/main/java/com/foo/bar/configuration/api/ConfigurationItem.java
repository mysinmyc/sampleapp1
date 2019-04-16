package com.foo.bar.configuration.api;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.foo.bar.configuration.ConfigurationItemCategory;

@Target(TYPE)
@Retention(RUNTIME)
@Inherited
public @interface ConfigurationItem {

	ConfigurationItemCategory category() default ConfigurationItemCategory.Generic;
	
	String name() default "";
}
