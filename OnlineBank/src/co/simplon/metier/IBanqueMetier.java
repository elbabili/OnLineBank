package co.simplon.metier;

import java.util.ArrayList;
import co.simplon.entities.*;

public interface IBanqueMetier {
	public void ajouterClient(Customer customer);			//ajouter client en base
	public ArrayList<Customer> listClient();				//liste des clients en base	
	
	public void ajouterCompte(Account account);				//ajouter un compte 
	public Account consulterCompte(int codeCpte);			//consulter un compte
	public void verser(int codeCpte,double montant);		//verser sur un compte un montant
	public void retirer(int codeCpte, double montant);		//retirer d'un compte un montant
	public void virement(int codeCpte1,int codeCpte2, double montant); // retirer d'un compte puis virer sur un autre
	public ArrayList<Operation> listOperation(int codeCpte);	// liste des opérations sur un compte
}
