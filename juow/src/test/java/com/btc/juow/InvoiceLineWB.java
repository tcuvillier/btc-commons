package com.btc.juow;

public class InvoiceLineWB extends WorkingBean {
	private ToOneValue<InvoiceWB> invoice;
	private FieldValue<String> productName;
	private FieldValue<Integer> quantity;
	private FieldValue<Double> pricePerUnit;

	public ToOneValue<InvoiceWB> invoice() { return invoice;}

	public InvoiceLineWB(InvoiceWB invoice,
			String productName, Integer quantity,
			Double pricePerUnit) {
		super();

		setInvoice(invoice);
		setProductName(productName);
		setQuantity(quantity);
		setPricePerUnit(pricePerUnit);
	}

	public InvoiceLineWB() {
		super();
	}

	public InvoiceLineWB(boolean existsInDatabase) {
		super(existsInDatabase);
	}

	public InvoiceWB getInvoice() {
		return invoice.getValue();
	}

	public void setInvoice(InvoiceWB newInvoice) {
		setLink(
				/* source			*/ this, 
				/* link target		*/ newInvoice, 
				/* toOne link		*/ this.invoice, 
				/* old toMany		*/ this.invoice.isLoaded() && this.invoice.getValue() != null ? this.invoice.getValue().lines(): null, 
				/* new toMany		*/ newInvoice == null ? null: newInvoice.lines());
	}

	public FieldValue<String> productName() { return productName;}

	public String getProductName() {
		return productName.getValue();
	}
	public void setProductName(String productName) {
		this.productName.setValue(productName);
	}

	public FieldValue<Integer> quantity() { return quantity;}

	public Integer getQuantity() {
		return quantity.getValue();
	}
	public void setQuantity(Integer quantity) {
		this.quantity.setValue(quantity);
	}

	public FieldValue<Double> pricePerUnit() { return pricePerUnit;}

	public Double getPricePerUnit() {
		return pricePerUnit.getValue();
	}
	public void setPricePerUnit(Double pricePerUnit) {
		this.pricePerUnit.setValue(pricePerUnit);
	}
}
