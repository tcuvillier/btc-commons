package com.btc.juow;

import java.util.ArrayList;
import java.util.List;

public class InvoiceBean {
	private String number;
	private Double amountExclVAT;
	private Double amountInclVAT;
	private Double amountVAT;
	private Double vatRate;
	private List<InvoiceLineBean> lines = new ArrayList<>();

	public void update(String number,Double amountExclVAT, Double amountInclVAT, Double amountVAT, Double vatRate) {
		setNumber(number);
		setAmountExclVAT(amountExclVAT);
		setAmountInclVAT(amountInclVAT);
		setAmountVAT(amountVAT);
		setVatRate(vatRate);
	}

	public List<InvoiceLineBean> getLines() {
		return lines;
	}
	public void setLines(List<InvoiceLineBean> lines) {
		this.lines = lines;
	}
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
