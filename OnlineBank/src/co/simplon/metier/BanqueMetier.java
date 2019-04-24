package co.simplon.metier;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;

import co.simplon.dao.AccountDao;
import co.simplon.dao.CustomerDao;
import co.simplon.dao.OperationDao;
import co.simplon.entities.*;

public class BanqueMetier implements IBanqueMetier {
	private AccountDao accountDao;
	private OperationDao operationDao;
	private CustomerDao customerDao;
		
	public BanqueMetier(ServletContext context) {			
		accountDao = new AccountDao(context);
		operationDao = new OperationDao(context);
		customerDao = new CustomerDao(context);	
	}
	
	public BanqueMetier() {			
		/*
		 * accountDao = new AccountDao(); operationDao = new OperationDao(); customerDao
		 * = new CustomerDao();
		 */
	}
	
	public void ajouterCompte(Account cp) {
		accountDao.create(cp);
	}

	public Account consulterCompte(int codeCpte) {
		Account account = accountDao.find(codeCpte);
		if(account != null)		account.setClient(customerDao.find(account.getClient().getIdCust()));		
		if(account != null)	return account;		
		else return null;
	}

	@Override
	public void verser(int codeCpte, double montant) {
		Account cp = consulterCompte(codeCpte);		// est-ce que le compte existe ?	
		cp.setBalance(cp.getBalance() + montant);	// si oui, créditer ce compte 
		accountDao.update(cp);						// puis mise à jour en base		
		operationDao.create(new Versement(0,montant,new Date(),cp.getNumAccount()));	// enfin, ajouter à la table des opérations
	}

	@Override
	public void retirer(int codeCpte, double montant) {
		Account cp = consulterCompte(codeCpte);
		//tester si retrait possible selon decouvert autorisé(courant) ou pas(epargne)
		double facilitesCaisses = 0;
		if(cp instanceof CompteCourant)	facilitesCaisses = ((CompteCourant)cp).getDecouvert();
		
		if((cp.getBalance() + facilitesCaisses) < montant)	throw new RuntimeException("Solde insuffisant");
		
		cp.setBalance(cp.getBalance() - montant);				
		accountDao.update(cp);									// mettre à jour le compte 	
		operationDao.create(new Retrait(0,montant,new Date(),cp.getNumAccount()));// ajouter à la table des opérations
	}

	@Override
	public void virement(int codeCpte1, int codeCpte2, double montant) {
		if(codeCpte1 == codeCpte2)	throw new RuntimeException("Vous ne pouvez pas effectuer un virement sur votre compte !");
		if(consulterCompte(codeCpte2) == null)	throw new RuntimeException("Compte destinataire inconnu !");
		retirer(codeCpte1,montant);
		verser(codeCpte2,montant);		
	}

	@Override
	public ArrayList<Operation> listOperation(int codeCpte) {
		Account cp = consulterCompte(codeCpte);
		if(cp != null) {
			cp.setListOperations(operationDao.listOperation(cp));
			return cp.getListOperations();
		}
		else return null;
	}

	@Override
	public void ajouterClient(Customer customer) {
		customerDao.create(customer);		
	}

	@Override
	public ArrayList<Customer> listClient() {
		return customerDao.listCustomer();		
	}
}
