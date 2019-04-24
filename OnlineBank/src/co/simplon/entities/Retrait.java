package co.simplon.entities;

import java.util.Date;

public class Retrait extends Operation {
	
	public Retrait(int numero, double montant, Date dateOperation, int NumCpte) {
		super(numero,montant,dateOperation,NumCpte);
	}
	
	@Override
	public String toString() {
		return "Retrait numéro : " + this.getNumOperation() + " / montant : " + this.getAmount() + " / le " + this.getDateOperation() + 
				" / sur le compte :" + this.getNumAccount();
	}
}
