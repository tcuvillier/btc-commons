package com.btc.juow;

import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class WorkingBean {
	private boolean existsInDatabase = false;
	private boolean deleted = false;
	private boolean recording = false;

	public WorkingBean() {
		this(false);
	}

	public WorkingBean(boolean existsInDatabase) {
		super();

		this.existsInDatabase = existsInDatabase;
		WorkingBeanHelper.initialize(this);
	}

	public boolean isExistInDatabase() {
		return existsInDatabase;
	}

	public boolean isModified() {
		if( !existsInDatabase ) return false;
		if( deleted ) return false;
		return getModifiedFieldValues().size() != 0;
	}

	public List<BaseValue<?>> getModifiedFieldValues() {
		return WorkingBeanHelper.getFieldValues(this, Boolean.TRUE, Boolean.TRUE);
	}

	public List<BaseValue<?>> getFieldValues() {
		return WorkingBeanHelper.getFieldValues(this, Boolean.TRUE, null);
	}

	public void reset() {
		for(BaseValue<?> value:  getModifiedFieldValues()) {
			if( value instanceof FieldValue) {
				FieldValue<?> fv = (FieldValue<?>)value;
				if( value.isLoaded() && value.isModified() ) 
					fv.reset();
			}
		}
	}

	public void unload() {
		for(BaseValue<?> fieldValue:  getModifiedFieldValues()) {
			if( fieldValue.isLoaded() ) 
				fieldValue.unload();
		}
	}

	public boolean isDeleted() {
		return deleted;
	}

	protected <S extends WorkingBean, T extends WorkingBean, C extends Collection<S>> void setLink(S source, T target, ToOneFieldValue<T> toOne, CollectionFieldValue<C, S> oldToMany, CollectionFieldValue<C, S> newToMany) {

		if( toOne.isLoaded() ) {
			T oldTarget = toOne.getValue();

			if( target == oldTarget ) return;	// Nothing to do

			if( oldTarget != null && oldToMany != null && oldToMany.isLoaded() ) {
				oldToMany.getValue().remove(this);
			}
		}

		toOne.setValue(target);

		if( target != null && newToMany != null ) {
			newToMany.getValue(true).add(source);
		}
	}
	
	public void startRecording() {
		recording = true;
	}
	
	public void stopRecording() {
		recording = false;
	}

	public void visit(WorkingVisitor visitor) {
		visitor.start(this);

		Stack<WorkingBean> stack = new Stack<>();
		work(visitor, null, stack);

		visitor.stop();
	}

	public void work(WorkingVisitor visitor, LinkFieldValue<?> link, Stack<WorkingBean> stack) {

		if( stack.contains(this) ) {
			visitor.backtrack(this, stack);
			return;
		}

		if( !visitor.beforeBean(this, link, stack) ) return;
		try {
			stack.push(this);
			for(BaseValue<?> value: getFieldValues()) {
				value.work(visitor, this, stack);
			}
		} finally {
			stack.pop();
		}
		visitor.afterBean(this, link, stack);
	}
}
