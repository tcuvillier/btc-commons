package com.btc.juow;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

public class WorkingBeanTest {

	@Test
	public void testWorkingBean() throws Exception {

		InvoiceWB invoice = new InvoiceWB(true);
		Assert.assertFalse(invoice.isModified());
		invoice.setNumber("1");
		Assert.assertFalse(invoice.isModified());
		invoice.setNumber("2");
		Assert.assertTrue(invoice.isModified());
		invoice.number().reset();
		Assert.assertFalse(invoice.isModified());
		invoice.setLines(new ArrayList<InvoiceLineWB>());

		// Test the global reset
		invoice.setNumber("1");
		invoice.setNumber("2");
		Assert.assertTrue(invoice.isModified());
		invoice.reset();
		Assert.assertFalse(invoice.isModified());

		InvoiceLineWB line1 = new InvoiceLineWB(invoice, "nordic bath", 1, 2000.0);
		Assert.assertEquals(invoice, line1.getInvoice());
		Assert.assertEquals(invoice.lines().size(), 1);
		Assert.assertEquals(invoice.getLines().get(0), line1);
	}
	
	final int N = 1000;

	@Test
	public void testPerformanceWorkingBean() throws Exception {

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
