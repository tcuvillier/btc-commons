package com.btc.juow;

@Descriptor(FieldDescriptor.class)
public class BaseValue<E, D extends FieldDescriptor> {
	private D descriptor;
	private E original = null;
	private E value = null;
	private boolean loaded = false;

	public BaseValue(D descriptor) {
		this.descriptor = descriptor;
	}

	public BaseValue() {
	}

	void setDescriptor(FieldDescriptor descriptor) {

		@SuppressWarnings("unchecked")
		D d = (D)descriptor;
		this.descriptor  = d;
	}

	public D getDescriptor() {
		return descriptor;
	}

	public E getOriginal() {
		return original;
	}

	public E getValue() {
		mustBeLoaded();
		return value;
	}

	public void setValue(E value) {
		this.value = value;

		if( ! loaded ) {
			this.original = value;
			this.loaded = true;
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
}
