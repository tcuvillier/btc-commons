package com.btc.juow;
import com.btc.juow.InvoiceBean;

public class InvoiceLineBean {
	private InvoiceBean invoice;
	private String productName;
	private Integer quantity;
	private Double pricePerUnit;

	
	public InvoiceLineBean() {}

	public InvoiceLineBean(InvoiceBean invoce, String productName, Integer quantity, Double pricePerUnit) {
		super();

		this.invoice = invoce;
		invoce.getLines().add(this);
		this.productName = productName;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
	}

	public void update(String productName, Integer quantity, Double pricePerUnit) {
	}

	public InvoiceBean getInvoice() {
		return invoice;
	}
	public void setInvoice(InvoiceBean invoce) {
		this.invoice = invoce;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(Double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
}
