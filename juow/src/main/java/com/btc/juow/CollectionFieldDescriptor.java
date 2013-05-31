package com.btc.juow;

import java.lang.reflect.Field;
import java.util.Collection;

public abstract class CollectionFieldDescriptor<C extends Collection<?>> extends ToManyFieldDescriptor<C> {

	public CollectionFieldDescriptor(Field field) {
		super(field);
	}

	public abstract C createCollection(C content);
}
