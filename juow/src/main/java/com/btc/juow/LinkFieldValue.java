package com.btc.juow;

public class LinkFieldValue<E, D extends FieldDescriptor> extends BaseValue<E,D> {

	public LinkFieldValue() {
		super();
	}

	public LinkFieldValue(D descriptor) {
		super(descriptor);
	}
}
