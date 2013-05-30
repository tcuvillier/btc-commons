package com.btc.juow;

import junit.framework.Assert;

import org.junit.Test;

public class WorkingBeanTest {

	@Test
	public void testWorkingBean() throws Exception {
		InvoiceWB cut = new InvoiceWB(true);
		Assert.assertFalse(cut.isModified());
		cut.setNumber("1");
		Assert.assertFalse(cut.isModified());
		cut.setNumber("2");
		Assert.assertTrue(cut.isModified());
		cut.number().reset();
		Assert.assertFalse(cut.isModified());

		// Test the global reset
		cut.setNumber("1");
		cut.setNumber("2");
		Assert.assertTrue(cut.isModified());
		cut.reset();
		Assert.assertFalse(cut.isModified());
	}
	
	@Test
	public void testPerformanceWorkingBean() throws Exception {
		final int N = 1000000;

		InvoiceWB [ ] wbs = new InvoiceWB[N];

		// Clean up mem, limit the risk to have the GC running....
		Runtime.getRuntime().runFinalization();
		Runtime.getRuntime().gc();
		long memBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long startAt = System.nanoTime();
		for(int i = 0; i < N; i++ ) {
			wbs[i] = new InvoiceWB();
		}
		long stopAt = System.nanoTime();
		Runtime.getRuntime().runFinalization();
		Runtime.getRuntime().gc();
		long memAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

		System.out.println("Creating " + N + " InvoiceWB took " + (stopAt - startAt) / 1e9 + " sec and use " + (memBefore - memAfter) / (1024) + " kb of memory");
	}

	@Test
	public void testPerformanceBean() throws Exception {
		final int N = 1000000;

		InvoiceBean [ ] clebeans = new InvoiceBean[N];

		// Clean up mem, limit the risk to have the GC running....
		Runtime.getRuntime().runFinalization();
		Runtime.getRuntime().gc();
		long memBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long startAt = System.nanoTime();
		for(int i = 0; i < N; i++ ) {
			clebeans[i] = new InvoiceBean();
		}
		long stopAt = System.nanoTime();
		Runtime.getRuntime().runFinalization();
		Runtime.getRuntime().gc();
		long memAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

		System.out.println("Creating " + N + " InvoiceBean took " + (stopAt - startAt) / 1e9 + " sec and use " + (memBefore - memAfter) / (1024) + " kb of memory");
	}
}
