package com.btc.juow;

@Descriptor(FieldDescriptor.class)
public class FieldValue<V> {
	private FieldDescriptor descriptor;
	private V original = null;
	private V value = null;
	private boolean loaded = false;

	public FieldValue(FieldDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	public FieldValue() {
	}

	void setDescriptor(FieldDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	public FieldDescriptor getDescriptor() {
		return descriptor;
	}

	public V getOriginal() {
		return original;
	}

	public V getValue() {
		mustBeLoaded();
		return value;
	}

	public void setValue(V value) {
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
