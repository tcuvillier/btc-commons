package com.btc.juow;

import java.util.Stack;

@Descriptor(FieldDescriptor.class)
public abstract class BaseValue<E> {
	private FieldDescriptor descriptor;
	private E original = null;
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

	public E getOriginal() {
		return original;
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
			this.original = value;
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

	public boolean isModified() {
		mustBeLoaded();
		return valueChanged();
	}

	public void mustBeLoaded() {
		if( ! loaded ) throw new IllegalStateException("Attribute is not loaded");
	}

	private boolean valueChanged() {
		if( value == null ) return original != null;
		return !value.equals(original);
	}

	public void unload() {
		value = original = null;
		loaded = false;
	}

	public void reset() {
		if( loaded ) {
			value = original;
		}
	}
	
	public String getName() {
		return descriptor.getFieldName();
	}

	public abstract void work(WorkingVisitor visitor, WorkingBean wbean, Stack<WorkingBean> stack);
}
