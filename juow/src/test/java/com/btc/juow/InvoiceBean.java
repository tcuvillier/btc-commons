package com.btc.juow;

public class InvoiceBean {
	private String number;
	private Double amountExclVAT;
	private Double amountInclVAT;
	private Double amountVAT;
	private Double vatRate;

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Double getAmountExclVAT() {
		return amountExclVAT;
	}
	public void setAmountExclVAT(Double amountExclVAT) {
		this.amountExclVAT = amountExclVAT;
	}
	public Double getAmountInclVAT() {
		return amountInclVAT;
	}
	public void setAmountInclVAT(Double amountInclVAT) {
		this.amountInclVAT = amountInclVAT;
	}
	public Double getAmountVAT() {
		return amountVAT;
	}
	public void setAmountVAT(Double amountVAT) {
		this.amountVAT = amountVAT;
	}
	public Double getVatRate() {
		return vatRate;
	}
	public void setVatRate(Double vatRate) {
		this.vatRate = vatRate;
	}
}
