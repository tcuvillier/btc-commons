package com.btc.juow;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListFieldValueTest {
	private final int N = 10;
	private ListFieldValue<InvoiceLineWB>.ListProxy<InvoiceLineWB> list;
	private InvoiceWB invoice;

	@Before
	public void beforeTest() {
		invoice = new InvoiceWB();
		Assert.assertFalse(invoice.lines().isLoaded());

		// Mark the lines as loaded and empty
		invoice.lines().load();
		Assert.assertTrue(invoice.lines().isLoaded());

		// Get the proxy for test purposes
		list = (ListFieldValue<InvoiceLineWB>.ListProxy<InvoiceLineWB>)invoice.lines().getValueProxy();
	}
	
	public void addLines() {
		for(int i = 0; i < N ; i++) {
			new InvoiceLineWB(invoice, "pencil " + i, i + 1, i * 2d);
		}
	}

	@Test
	public void testRemove() {
		invoice.startRecording();
		addLines();

		// Remove a line
		list.remove(list.get(0));

		Assert.assertTrue(list.isModified());
		Assert.assertEquals(N - 1, list.getAddedElements().size());
		Assert.assertEquals(0, list.getRemovedElements().size());
	}

	@Test
	public void testRemoveAll() {
		invoice.startRecording();
		addLines();

		List<InvoiceLineWB> toBeRemoved = new ArrayList<>();
		toBeRemoved.add(list.get(0));
		toBeRemoved.add(list.get(1));

		// Remove a line
		list.removeAll(toBeRemoved);

		Assert.assertTrue(list.isModified());
		Assert.assertEquals(N - 2, list.getAddedElements().size());
		Assert.assertEquals(0, list.getRemovedElements().size());
	}

	public void testClear() {
		invoice.startRecording();
		addLines();
		
		// Remove all the remaining lines
		list.clear();
		Assert.assertFalse(list.isModified());
		Assert.assertEquals(0, list.getAddedElements().size());
		Assert.assertEquals(0, list.getRemovedElements().size());
	}

	public void testAdd() {
		addLines();
		invoice.startRecording();

	}
}
