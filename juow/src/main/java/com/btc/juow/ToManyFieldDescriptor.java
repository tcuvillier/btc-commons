package com.btc.juow;

import java.lang.reflect.Field;

public abstract class ToManyFieldDescriptor<C> extends FieldDescriptor {

	public ToManyFieldDescriptor(Field field) {
		super(field);
	}
}
