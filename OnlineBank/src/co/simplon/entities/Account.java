package co.simplon.entities;

import java.util.ArrayList;
import java.util.Date;

public abstract class Account {								//CC ou CE
	private int numAccount;
	private Date dateCreation;
	private double balance;
	
	private Customer client;	
	private ArrayList<Operation> listOperations;

	public Account(int codeCompte, Date dateCreation, double solde, Customer client) {
		this.numAccount = codeCompte;
		this.dateCreation = dateCreation;
		this.balance = solde;
		this.client = client;	
		listOperations = null;
	}
	
	public ArrayList<Operation> getListOperations() {
		return listOperations;
	}

	public void setListOperations(ArrayList<Operation> list) {
		this.listOperations = list;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}	
	
	public int getNumAccount() {
		return numAccount;
	}

	public void setNumAccount(int numAccount) {
		this.numAccount = numAccount;
	}

	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Customer getClient() {
		return client;
	}

	public void setClient(Customer client) {
		this.client = client;
	}
}
