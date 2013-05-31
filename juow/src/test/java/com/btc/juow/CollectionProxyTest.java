package com.btc.juow;

import org.junit.Assert;
import org.junit.Test;

public class CollectionProxyTest {
	public final int N = 10;

	@Test
	public void testCollectionProxy() {
		InvoiceWB invoice = new InvoiceWB();

		Assert.assertFalse(invoice.lines().isLoaded());
		invoice.lines().load();
		Assert.assertTrue(invoice.lines().isLoaded());

		ListFieldValue<InvoiceLineWB>.ListProxy<InvoiceLineWB> list = (ListFieldValue<InvoiceLineWB>.ListProxy<InvoiceLineWB>)invoice.lines().getValueProxy();


		// Add lines in the invoice
		for(int i = 0; i < N ; i++) {
			new InvoiceLineWB(invoice, "pencil " + i, i + 1, i * 2d);
		}

		Assert.assertTrue(list.isModified());
		Assert.assertEquals(N, list.getAddedElements().size());
		Assert.assertEquals(0, list.getRemovedElements().size());

		list.remove(list.get(0));

		Assert.assertTrue(list.isModified());
		Assert.assertEquals(N - 1, list.getAddedElements().size());
		Assert.assertEquals(0, list.getRemovedElements().size());

		list.removeAll(list.getAddedElements());
		Assert.assertFalse(list.isModified());
		Assert.assertEquals(0, list.getAddedElements().size());
		Assert.assertEquals(0, list.getRemovedElements().size());
}
}
