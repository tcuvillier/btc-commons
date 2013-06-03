package com.btc.juow;

import java.util.Stack;

public interface WorkingVisitor {
	void start(					WorkingBean root);
	boolean beforeBean(			WorkingBean bean, LinkFieldValue<?> link, Stack<WorkingBean> stack);
	void afterBean(				WorkingBean bean, LinkFieldValue<?> link, Stack<WorkingBean> stack);
	boolean visitFieldValue(	WorkingBean bean, FieldValue<?> fieldValue, Stack<WorkingBean> stack);
	boolean beforeToOneValue(	WorkingBean bean, ToOneFieldValue<?> fieldValue, Stack<WorkingBean> stack);
	void afterToOneValue(		WorkingBean bean, ToOneFieldValue<?> fieldValue, Stack<WorkingBean> stack);
	boolean beforeToManyValue(	WorkingBean bean, ToManyFieldValue<?,?> fieldValue, Stack<WorkingBean> stack);
	void afterToManyValue(		WorkingBean bean, ToManyFieldValue<?,?> fieldValue, Stack<WorkingBean> stack);
	void stop();
	void backtrack(WorkingBean workingBean, Stack<WorkingBean> stack);
}
