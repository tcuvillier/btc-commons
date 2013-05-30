package com.btc.juow;

import java.lang.reflect.Field;

public class FieldDescriptor {
	private Field field;

	public FieldDescriptor(Field field) {
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

	public BaseValue<?,?> newValue() {

		try {
			BaseValue<?,?> result = (BaseValue<?,?>)field.getType().getConstructor().newInstance();
			result.setDescriptor(this);
			return result;
		} catch(RuntimeException e) {
			throw e;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
