package com.btc.juow;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkingBeanHelper {
	public static Map<Class<? extends WorkingBean>, List<FieldDescriptor>> cache = new HashMap<>();

	public static List<BaseValue<?,?>> getModifiedFieldValues(WorkingBean wBean) {
		List<BaseValue<?,?>> fieldValues = new ArrayList<>();

		try {
			for(FieldDescriptor descriptor: getFieldDescriptors(wBean, wBean.getClass()) ) {
				BaseValue<?,?> fieldValue = (BaseValue<?,?>)descriptor.getField().get(wBean);
				if( fieldValue.isLoaded() && fieldValue.isModified() )
					fieldValues.add(fieldValue);
			}
		} catch(RuntimeException e) {
			throw e;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}

		return fieldValues;
	}

	public static List<FieldDescriptor> getFieldDescriptors(WorkingBean workingBean, Class<? extends WorkingBean> wBeanClass) {
		try {
			List<FieldDescriptor> descriptors = cache.get(wBeanClass);
	
			if( descriptors == null ) {
				descriptors = new ArrayList<>();
	
				// Add the superclass fields
				Class<?> superClass = wBeanClass.getSuperclass();
				if( wBeanClass != WorkingBean.class && superClass != WorkingBean.class ) {
					descriptors.addAll(getFieldDescriptors(workingBean, (Class<? extends WorkingBean>)superClass));
				}

				// Add the class specific fields
				for(Field field: wBeanClass.getDeclaredFields()) {
					if( !Modifier.isStatic(field.getModifiers()) ) {
						Class<?> type = field.getType();
						if( BaseValue.class.isAssignableFrom(type)) {
							field.setAccessible(true);

							BaseValue<?,?> fieldValue = (BaseValue<?,?>)field.get(workingBean);

							// Get the descriptor
							if(fieldValue != null && fieldValue.getDescriptor() != null )
								descriptors.add(fieldValue.getDescriptor());
							else
								descriptors.add(new FieldDescriptor(field));
						}
					}
				}
	
				cache.put(wBeanClass, descriptors);
			}
	
			return descriptors;

		} catch(RuntimeException e) {
			throw e;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void initialize(WorkingBean workingBean) {
		try {
			for(FieldDescriptor descriptor: getFieldDescriptors(workingBean, workingBean.getClass())) {
				Field field = descriptor.getField();
				BaseValue<?,?> fieldValue = (BaseValue<?,?>)field.get(workingBean);
				if( fieldValue == null ) {
					BaseValue<?,?> value = descriptor.newValue();
					field.set(workingBean, value);
				} else if( fieldValue.getDescriptor() == null ) {
					fieldValue.setDescriptor(descriptor);
				}
			}
		} catch(RuntimeException e) {
			throw e;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
