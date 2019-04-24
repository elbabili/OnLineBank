package co.simplon.entities;

import java.util.Date;

public class CompteCourant extends Account {
	private double decouvert;
	
	public CompteCourant(int codeCompte, Date dateCreation, double solde, Customer client, double decouvert) {
		super(codeCompte, dateCreation, solde, client);
		this.decouvert = decouvert;
	}
	
/*	public CompteCourant(int codeCompte, Date dateCreation, double solde, double decouvert, int codeCli) {
		super(codeCompte, dateCreation, solde, codeCli);
		this.decouvert = decouvert;
	}
*/
	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

	@Override
	public String toString() {
		return "CompteCourant [decouvert=" + decouvert + ", Solde=" + getBalance() + ", NumCompte()="
				+ getNumAccount() + ", DateCreation()=" + getDateCreation() + ", Nom client()=" + getClient().getName() + "]";
	}	
}
