package com.btc.juow;

import java.util.Stack;

public class FieldValue<E> extends BaseValue<E> {

	public FieldValue() {
		super();
	}

	public FieldValue(E defaultValue) {
		super();
	}

	public void work(WorkingVisitor visitor, WorkingBean wbean, Stack<WorkingBean> stack) {
		visitor.visitFieldValue(wbean, this, stack);
	}
}
