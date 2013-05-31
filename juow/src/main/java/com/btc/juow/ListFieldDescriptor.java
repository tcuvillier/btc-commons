package com.btc.juow;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListFieldDescriptor extends CollectionFieldDescriptor<List<?>> {
	private Class<? extends List> listClass;

	public ListFieldDescriptor(Field field) {
		super(field);

		ListValue annotation = field.getAnnotation(ListValue.class);
		listClass = annotation == null ? null: annotation.implementation();
		if( listClass == null ) listClass = ArrayList.class;
	}

	@Override
	public List<?> createCollection(List<?> content) {
		try {
			if( content != null ) 
				return listClass.getConstructor(Collection.class).newInstance(content);
			else
				return listClass.newInstance();
		} catch(RuntimeException e) {
			throw e;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
