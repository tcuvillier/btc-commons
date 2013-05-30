package com.btc.juow;

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

	public List<FieldValue<?>> getModifiedFieldValues() {
		return WorkingBeanHelper.getModifiedFieldValues(this);
	}

	public void reset() {
		for(FieldValue<?> fieldValue:  getModifiedFieldValues()) {
			if( fieldValue.isLoaded() && fieldValue.isModified() ) 
				fieldValue.reset();
		}
	}

	public void unload() {
		for(FieldValue<?> fieldValue:  getModifiedFieldValues()) {
			if( fieldValue.isLoaded() ) 
				fieldValue.unload();
		}
	}

	public boolean isDeleted() {
		return deleted;
	}
}
