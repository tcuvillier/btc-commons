package com.btc.juow;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Descriptor {
	Class<? extends FieldDescriptor> value();
}
