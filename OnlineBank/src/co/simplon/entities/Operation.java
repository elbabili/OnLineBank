package co.simplon.entities;

import java.util.Date;

public abstract class Operation {	
	private int numOperation;
	private Date dateOperation;
	private double amount;
	private int numAccount;
	
	public Operation(int numero, double montant, Date dateOperation, int numCpte) {
		this.numOperation = numero; 
		this.dateOperation = dateOperation;
		this.amount = montant;			
		this.numAccount = numCpte;
	}
	
	public int getNumOperation() {
		return numOperation;
	}
	public void setNumOperation(int numero) {
		this.numOperation = numero;
	}
	public Date getDateOperation() {
		return dateOperation;
	}
	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double montant) {
		this.amount = montant;
	}
	public int getNumAccount() {
		return numAccount;
	}
	public void setNumAccount(int numCpte) {
		this.numAccount = numCpte;
	}
}
