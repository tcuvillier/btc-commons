package com.btc.juow;

import java.util.Collection;
import java.util.Stack;

public abstract class ToManyFieldValue<C, E extends WorkingBean> extends FieldValue<C> implements LinkFieldValue<C> {

	public ToManyFieldValue() {
		super();
	}

	public void work(WorkingVisitor visitor, WorkingBean wbean, Stack<WorkingBean> stack) {
		if( !visitor.beforeToManyValue(wbean, this, stack) ) return;

		try {
			if( isLoaded() ) {
				for(WorkingBean linked: getLinkedBeans() ) {
					linked.work(visitor, this, stack);
				}
			}
		} finally {
			visitor.afterToManyValue(wbean, this, stack);
		}
	}

	public abstract Collection<E> getLinkedBeans();
}
