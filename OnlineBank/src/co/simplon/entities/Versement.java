package co.simplon.entities;
import java.util.Date;

public class Versement extends Operation {
	
	public Versement(int numero, double montant, Date dateOperation, int NumCpte) {
		super(numero,montant,dateOperation,NumCpte);
	}

	@Override
	public String toString() {
		return "Versement numéro : " + this.getNumOperation() + " / montant : " + this.getAmount() + " / le " + this.getDateOperation() +
				" / sur le compte :" + this.getNumAccount();
	}
}
