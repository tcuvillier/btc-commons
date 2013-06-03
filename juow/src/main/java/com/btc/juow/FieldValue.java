package com.btc.juow;

import java.util.Stack;

public class FieldValue<E> extends BaseValue<E> {
	private E original = null;

	public FieldValue() {
		super();
	}

	public FieldValue(E defaultValue) {
		super();
	}

	public void setValue(E value) {
		if( ! isLoaded() ) {
			this.original = value;
		}

		super.setValue(value);
	}

	public void reset() {
		if( isLoaded()) {
			setValue(original);
		}
	}

	public void unload() {
		original = null;
		super.unload();
	}

	public E getOriginal() {
		return original;
	}

	public void work(WorkingVisitor visitor, WorkingBean wbean, Stack<WorkingBean> stack) {
		visitor.visitFieldValue(wbean, this, stack);
	}

	@Override
	public boolean isModified() {
		mustBeLoaded();
		if( super.getValue() == null ) return original != null;
		return !super.getValue().equals(original);
	}
}
