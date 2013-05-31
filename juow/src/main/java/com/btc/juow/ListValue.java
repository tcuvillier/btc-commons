package com.btc.juow;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
public @interface ListValue {
	Class<? extends List> implementation() default ArrayList.class;
}
