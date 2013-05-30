package com.btc.juow;

import junit.framework.Assert;

import org.junit.Test;

public class FieldValueTest {

	private static class WorkingBeanUnderTest extends WorkingBean {
		private FieldValue<String> name;
		
		public FieldValue<String> name() {
			return name;
		}
	}

	@Test
	public void test() throws Exception {
		WorkingBeanUnderTest cut = new WorkingBeanUnderTest();
		FieldValue<String> name = cut.name();

		Assert.assertFalse(name.isLoaded());
		try {
			name.getValue();
			Assert.fail("IllegalStateException not thrown");
		} catch(IllegalStateException e) {
			// OK, normal
		}

		name.setValue("a");
		Assert.assertTrue(name.isLoaded());
		Assert.assertFalse(name.isModified());
		Assert.assertEquals("a", name.getValue());

		name.unload();
		Assert.assertFalse(name.isLoaded());

		name.setValue("a");
		Assert.assertTrue(name.isLoaded());
		Assert.assertFalse(name.isModified());
		Assert.assertEquals("a", name.getValue());

		name.setValue("b");
		Assert.assertTrue(name.isLoaded());
		Assert.assertTrue(name.isModified());
		Assert.assertEquals("b", name.getValue());

		name.reset();
		Assert.assertTrue(name.isLoaded());
		Assert.assertFalse(name.isModified());
		Assert.assertEquals("a", name.getValue());
	}
}
