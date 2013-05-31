package com.btc.juow;

import java.util.Collections;
import java.util.List;

public class InvoiceWB extends WorkingBean {

	private ListFieldValue<InvoiceLineWB> lines;
	private FieldValue<String> number;
	private FieldValue<Double> amountExclVAT;
	private FieldValue<Double> amountInclVAT;
	private FieldValue<Double> amountVAT;
	private FieldValue<Double> vatRate;

	public InvoiceWB(String number,Double amountExclVAT, Double amountInclVAT, Double amountVAT, Double vatRate) {
		super();

		setNumber(number);
		setAmountExclVAT(amountExclVAT);
		setAmountInclVAT(amountInclVAT);
		setAmountVAT(amountVAT);
		setVatRate(vatRate);
	}

	public InvoiceWB(boolean existInTheDatabase) {
		super(existInTheDatabase);
	}

	public InvoiceWB() {
		super();
	}

	public void setDefault() {
		number.unload();
		setNumber(null);

		amountExclVAT.unload();
		setAmountExclVAT(0d);

		amountInclVAT.unload();
		setAmountInclVAT(0d);

		amountVAT.unload();
		setAmountVAT(0d);

		vatRate.unload();
		setVatRate(0d);
	}

	public String getNumber() {
		return number.getValue();
	}

	public void setNumber(String number) {
		this.number.setValue(number);
	}

	public FieldValue<String> number() {
		return number;
	}

	public Double getAmountInclVAT() {
		return amountInclVAT.getValue();
	}

	public void setAmountInclVAT(Double amountInclVAT) {
		this.amountInclVAT.setValue(amountInclVAT);
	}

	public FieldValue<Double> amountInclVAT() {
		return amountInclVAT;
	}

	public Double getAmountExclVAT() {
		return amountExclVAT.getValue();
	}

	public void setAmountExclVAT(Double amountExclVAT) {
		this.amountExclVAT.setValue(amountExclVAT);
	}

	public FieldValue<Double> amountExclVAT() {
		return amountExclVAT;
	}

	public Double getAmountVAT() {
		return amountVAT.getValue();
	}

	public void setAmountVAT(Double amountVAT) {
		this.amountVAT.setValue(amountVAT);
	}

	public FieldValue<Double> amountVAT() {
		return amountVAT;
	}

	public Double getVatRate() {
		return vatRate.getValue();
	}

	public void setVatRate(Double vatRate) {
		this.vatRate.setValue(vatRate);
	}

	public FieldValue<Double> vatRate() {
		return vatRate;
	}

	public ListFieldValue<InvoiceLineWB> lines() {
		return lines;
	}

	public List<InvoiceLineWB> getLines() {
		return Collections.unmodifiableList(lines.getValue());
	}

	public void setLines(List<InvoiceLineWB> newLines) {
		lines.setValue(newLines);
	}
	
	public String toString() {
		if( !number.isLoaded() ) return super.toString();
		return getNumber();
	}
}
