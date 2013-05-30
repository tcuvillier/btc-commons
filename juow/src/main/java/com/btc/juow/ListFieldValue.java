package com.btc.juow;

import java.util.Collection;
import java.util.List;

@Descriptor(ArrayListFieldDescriptor.class)
public class ListFieldValue<E extends WorkingBean> extends CollectionFieldValue<List<E>, E, CollectionFieldDescriptor<Collection<?>>> {

	public ListFieldValue() {
		super();
	}

	public ListFieldValue(CollectionFieldDescriptor<Collection<?>> descriptor) {
		super(descriptor);
	}
}
