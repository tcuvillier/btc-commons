package com.btc.juow;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;

public class DozerMappingTest {
	
	@Test
	public void map() throws Exception {
		BasicConfigurator.configure();

		InvoiceBean invoice = new InvoiceBean();
//		invoice.setNumber("INV1");
		invoice.update("INV 2013001", 100d, 119.6d, 19.6d, 19.6d);

		for(int i = 0; i < 10; i++) {
			new InvoiceLineBean(invoice, "product " + i, i + 1, i * 10d);
		}

		Mapper mapper = new DozerBeanMapper();

		InvoiceWB invoiceWB = mapper.map(invoice,  InvoiceWB.class);

		Assert.assertEquals(invoice.getNumber(), invoiceWB.getNumber());
		Assert.assertEquals(invoice.getAmountExclVAT(), invoiceWB.getAmountExclVAT());
		Assert.assertEquals(invoice.getAmountInclVAT(), invoiceWB.getAmountInclVAT());
		Assert.assertEquals(invoice.getAmountVAT(), invoiceWB.getAmountVAT());
		Assert.assertEquals(invoice.getVatRate(), invoiceWB.getVatRate());
		Assert.assertEquals(invoice.getLines().size(), invoiceWB.getLines().size());

		for(int i = 0; i < 10; i++) {
			InvoiceLineBean line = invoice.getLines().get(i);
			InvoiceLineWB lineWB = invoiceWB.getLines().get(i);

			Assert.assertFalse(invoiceWB.isModified());

			Assert.assertEquals(invoice, line.getInvoice());
			Assert.assertEquals(invoiceWB, lineWB.getInvoice());
			Assert.assertEquals(lineWB.getProductName(), line.getProductName());
			Assert.assertEquals(lineWB.getPricePerUnit(), line.getPricePerUnit());
			Assert.assertEquals(lineWB.getQuantity(), line.getQuantity());
		}

//		invoiceWB.amountExclVAT().unload();
		InvoiceBean invoice2 = mapper.map(invoiceWB, InvoiceBean.class);
		Assert.assertEquals(invoice.getNumber(), invoice2.getNumber());
		Assert.assertEquals(invoice.getAmountExclVAT(), invoice2.getAmountExclVAT());
		Assert.assertEquals(invoice.getAmountInclVAT(), invoice2.getAmountInclVAT());
		Assert.assertEquals(invoice.getAmountVAT(), invoice2.getAmountVAT());
		Assert.assertEquals(invoice.getVatRate(), invoice2.getVatRate());
		Assert.assertEquals(invoice.getLines().size(), invoice2.getLines().size());

		for(int i = 0; i < 10; i++) {
			InvoiceLineBean line = invoice.getLines().get(i);
			InvoiceLineBean line2 = invoice2.getLines().get(i);

			Assert.assertEquals(line.getProductName(), line2.getProductName());
			Assert.assertEquals(line.getPricePerUnit(), line2.getPricePerUnit());
			Assert.assertEquals(line.getQuantity(), line2.getQuantity());
		}
	}
}
