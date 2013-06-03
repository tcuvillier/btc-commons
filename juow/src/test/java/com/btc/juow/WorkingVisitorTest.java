package com.btc.juow;

import net.minidev.json.JSONStyle;

import org.junit.Test;

public class WorkingVisitorTest {

	@Test
	public void testVisitor() {
		InvoiceWB invoice = new InvoiceWB("1", 100d, 120d, 20d, 20d);

		for(int i = 0; i < 10; i++) {
			new InvoiceLineWB(invoice, "pencil " + i, i + 1, i * 2d);
		}

		JSONVisitor visitor = new JSONVisitor();
		invoice.visit(visitor);

		String s = visitor.getDocument().toString(JSONStyle.NO_COMPRESS);
		System.out.println(s);
	}
}
