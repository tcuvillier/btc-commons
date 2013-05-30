package com.btc.juow;

import java.util.Set;

public class SetFieldValue<E> extends ToManyValue<Set<E>, E> {

	public SetFieldValue() {
		super();
	}

	public SetFieldValue(FieldDescriptor descriptor) {
		super(descriptor);
	}

}
