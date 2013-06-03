package com.btc.juow;

public class InvoiceMapper {

	public InvoiceWB detach(InvoiceBean invoice) {
		InvoiceWB invoiceWB = new InvoiceWB(invoice.getNumber(), invoice.getAmountExclVAT(), invoice.getAmountInclVAT(), invoice.getAmountVAT(), invoice.getVatRate());

		for(InvoiceLineBean line: invoice.getLines()) {
			new InvoiceLineWB(invoiceWB, line.getProductName(), line.getQuantity(), line.getPricePerUnit());
		}

		return invoiceWB;
	}

	public void attach(InvoiceWB invoiceWB, InvoiceBean invoice) {
		invoice.update(invoiceWB.getNumber(), invoiceWB.getAmountExclVAT(), invoiceWB.getAmountInclVAT(), invoiceWB.getAmountVAT(), invoiceWB.getVatRate());
	}
}
