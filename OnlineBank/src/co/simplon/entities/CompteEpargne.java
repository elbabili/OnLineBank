package co.simplon.entities;

import java.util.Date;

public class CompteEpargne extends Account {
	private double taux;

	public CompteEpargne(int codeCompte, Date dateCreation, double solde, Customer client, double taux) {
		super(codeCompte, dateCreation, solde, client);
		this.taux = taux;
	}
	
/*	public CompteEpargne(int codeCompte, Date dateCreation, double solde, double taux, int codeCli) {
		super(codeCompte, dateCreation, solde,codeCli);
		this.taux = taux;
	}
*/
	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	@Override
	public String toString() {
		return "CompteEpargne [taux=" + taux + ", Solde=" + getBalance() + ", NumCompte()=" + getNumAccount()
				+ ", DateCreation()=" + getDateCreation() + ", Nom Client()=" + getClient().getName() + "]";
	}	
}
