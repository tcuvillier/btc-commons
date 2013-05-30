package com.btc.juow;

import java.lang.reflect.Field;

public class FieldDescriptor {
	private Field field;

	public FieldDescriptor(Class<? extends WorkingBean> theClass, Field field) {
		this.field = field;
	}

	public String getFieldName() {
		return field.getName();
	}

	public String getFieldFullName() {
		return field.getDeclaringClass().getName() + ":" + field.getName();
	}

	public Field getField() {
		return field;
	}

	public FieldValue<?> newValue() {

		try {
			return (FieldValue<?>)field.getType().getConstructor(FieldDescriptor.class).newInstance(this);
		} catch(RuntimeException e) {
			throw e;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
