package com.btc.juow;

public abstract class ToManyFieldValue<C, E extends WorkingBean, D extends FieldDescriptor> extends LinkFieldValue<C, D> {

	public ToManyFieldValue() {
		super();
	}

	public ToManyFieldValue(D descriptor) {
		super(descriptor);
	}
}
