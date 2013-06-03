package com.btc.juow;

import java.util.Stack;

public class ToOneFieldValue<C extends WorkingBean> extends FieldValue<C> implements LinkFieldValue<C> {

	public ToOneFieldValue() {
		super();
	}

	public void work(WorkingVisitor visitor, WorkingBean wbean, Stack<WorkingBean> stack) {
		if( !visitor.beforeToOneValue(wbean, this, stack) ) return;

		try {
			if( isLoaded() ) {
				WorkingBean linked = getValue();
				if( linked != null ) 
					linked.work(visitor, this, stack);
			}
		} finally {
			visitor.afterToOneValue(wbean, this, stack);
		}
	}
}
