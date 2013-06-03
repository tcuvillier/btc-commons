package com.btc.juow;

import java.util.Stack;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class JSONVisitor implements WorkingVisitor {
	private JSONObject jsonDocument;
	private Stack<JSONObject> jsonStack = new Stack<>();

	@Override
	public void start(WorkingBean root) {
	}

	@Override
	public boolean beforeBean(WorkingBean bean, LinkFieldValue<?> link, Stack<WorkingBean> stack) {

		JSONObject json = new JSONObject();
		json.put("class", bean.getClass().getName());
		if( link != null ) {
			if( link instanceof ToOneFieldValue) {
				jsonStack.peek().put(link.getDescriptor().getFieldName(), json);
			} else {
				JSONArray array = (JSONArray)jsonStack.peek().get(link.getDescriptor().getFieldName());
				if( array == null ) {
					array = new JSONArray();
					jsonStack.peek().put(link.getDescriptor().getFieldName(), array);
				}

				array.add(json);
			}
		}

		jsonStack.push(json);

		if( jsonDocument == null ) jsonDocument = json;

		return true;
	}

	@Override
	public void afterBean(WorkingBean bean, LinkFieldValue<?> link, Stack<WorkingBean> stack) {
		jsonStack.pop();
	}

	@Override
	public boolean visitFieldValue(WorkingBean bean, FieldValue<?> fieldValue, Stack<WorkingBean> stack) {
		jsonStack.peek().put(fieldValue.getName(), fieldValue.getValue());
		return true;
	}

	@Override
	public boolean beforeToOneValue(WorkingBean bean,ToOneFieldValue<?> fieldValue, Stack<WorkingBean> stack) {
		return true;
	}

	@Override
	public void afterToOneValue(WorkingBean bean, ToOneFieldValue<?> fieldValue, Stack<WorkingBean> stack) {
	}

	@Override
	public boolean beforeToManyValue(WorkingBean bean, ToManyFieldValue<?, ?> fieldValue, Stack<WorkingBean> stack) {
		return true;
	}

	@Override
	public void afterToManyValue(WorkingBean bean, ToManyFieldValue<?, ?> fieldValue, Stack<WorkingBean> stack) {
	}

	@Override
	public void stop() {
	}

	@Override
	public void backtrack(WorkingBean workingBean, Stack<WorkingBean> stack) {
	}
	
	public JSONObject getDocument() {
		return jsonDocument;
	}
}
