package com.btc.juow;

import java.util.Stack;

public interface LinkFieldValue<E> {
	E getValue();
	E getValue(boolean load);
	void setValue(E value);
	void load();
	boolean isLoaded();
	boolean isModified();
	void mustBeLoaded();
	void unload();
	String getName();
	void work(WorkingVisitor visitor, WorkingBean wbean, Stack<WorkingBean> stack);
	FieldDescriptor getDescriptor();
}
