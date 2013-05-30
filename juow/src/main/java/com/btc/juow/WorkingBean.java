package com.btc.juow;

import java.util.Collection;
import java.util.List;

public class WorkingBean {
	private boolean existsInDatabase = false;
	private boolean deleted = false;

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

	public List<BaseValue<?,?>> getModifiedFieldValues() {
		return WorkingBeanHelper.getModifiedFieldValues(this);
	}

	public void reset() {
		for(BaseValue<?,?> fieldValue:  getModifiedFieldValues()) {
			if( fieldValue.isLoaded() && fieldValue.isModified() ) 
				fieldValue.reset();
		}
	}

	public void unload() {
		for(BaseValue<?,?> fieldValue:  getModifiedFieldValues()) {
			if( fieldValue.isLoaded() ) 
				fieldValue.unload();
		}
	}

	public boolean isDeleted() {
		return deleted;
	}

	protected <S extends WorkingBean, T extends WorkingBean, C extends Collection<S>> void setLink(S source, T target, ToOneValue<T> toOne, CollectionFieldValue<C, S, ?> oldToMany, CollectionFieldValue<C, S, ?> newToMany) {

		if( toOne.isLoaded() ) {
			T oldTarget = toOne.getValue();

			if( target == oldTarget ) return;	// Nothing to do

			if( oldTarget != null && oldToMany != null && oldToMany.isLoaded() ) {
				oldToMany.getValue().remove(this);
			}
		}

		toOne.setValue(target);

		if( target != null && newToMany != null && newToMany.isLoaded() ) {
			newToMany.getValue().add(source);
		}
	}
}
