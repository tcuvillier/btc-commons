package com.btc.juow;

import java.util.Stack;

@Descriptor(FieldDescriptor.class)
public abstract class BaseValue<E> {
	private FieldDescriptor descriptor;
	private E value = null;
	private boolean loaded = false;

	public BaseValue() {
	}

	void setDescriptor(FieldDescriptor descriptor) {
		this.descriptor  = descriptor;
	}

	public FieldDescriptor getDescriptor() {
		return descriptor;
	}

	public E getValue() {
		return getValue(false);
	}

	public E getValue(boolean load) {
		if( !loaded ) {
			if( load )
				load();
			else
				mustBeLoaded();
		}
		return value;
	}

	public void setValue(E value) {
		this.value = value;

		if( ! loaded ) {
			this.loaded = true;
		}
	}

	public void load() {
		if( ! loaded ) {
			loaded = true;	// Initialized with an empty value
		}
	}

	public boolean isLoaded() {
		return loaded;
	}

	protected void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	public abstract boolean isModified();

	public void mustBeLoaded() {
		if( ! loaded ) throw new IllegalStateException("Attribute " + getDescriptor().getFieldName() + " is not loaded");
	}

	public void unload() {
		loaded = false;
	}

//	public abstract void reset();
	
	public String getName() {
		return descriptor.getFieldName();
	}

	public String toString() {
		return "Field " + getName() + " " + (isLoaded() ? value: "not loaded");
	}

	public abstract void work(WorkingVisitor visitor, WorkingBean wbean, Stack<WorkingBean> stack);
}
