package com.btc.juow;

import java.util.List;

public class ListFieldValue<E> extends ToManyValue<List<E>, E> {

	public ListFieldValue() {
		super();
	}

	public ListFieldValue(FieldDescriptor descriptor) {
		super(descriptor);
	}
}
