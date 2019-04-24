package co.simplon.test;

import java.util.ArrayList;
import java.util.Date;

import co.simplon.entities.*;
import co.simplon.metier.BanqueMetier;

public class MaBanqueApplication {
	
	public static void main(String[] args) {
		BanqueMetier banqueMetier = new BanqueMetier();
		
		System.out.println("------bienvenue dans la 1ère version de ma petite banque------\n");

		Customer c1 = new Customer(1,"Dupont","henry");
		Customer c2 = new Customer(2,"Durand","Robert");
		Customer c3 = new Customer(3,"Douglas","Michael");
		
		Account cp1 = new CompteCourant(100500,new Date(), 900 , c1 , 60);
		Account cp2 = new CompteEpargne(100600,new Date(), 600 , c2 , 5.5);
		Account cp3 = new CompteCourant(100700,new Date(), 1000 , c1 , 150);
		
		System.out.println(cp1);
		System.out.println(cp2);
		System.out.println(cp3);
		
		banqueMetier.ajouterClient(c1);
		banqueMetier.ajouterClient(c2);
		banqueMetier.ajouterClient(c3);
		
		banqueMetier.ajouterCompte(cp1);
		banqueMetier.ajouterCompte(cp2);
		banqueMetier.ajouterCompte(cp3);
		
		banqueMetier.verser(cp1.getNumAccount(), 90);
		banqueMetier.verser(cp1.getNumAccount(), 60);
		banqueMetier.retirer(cp1.getNumAccount(), 50);
		
		banqueMetier.verser(cp2.getNumAccount(), 100);
		banqueMetier.verser(cp2.getNumAccount(), 600);
		banqueMetier.retirer(cp2.getNumAccount(), 300);
		
		banqueMetier.virement(cp2.getNumAccount(), cp1.getNumAccount(), 100);
		
		System.out.println("----------------------Liste des clients-----------------");
		ArrayList<Customer> listClients = banqueMetier.listClient();
		for(Customer l : listClients)	System.out.println(l);
		
		System.out.println("\nsolde de " + cp1.getClient().getName()   + " : "   + banqueMetier.consulterCompte(cp1.getNumAccount()).getBalance());
		System.out.println("solde de "   + cp2.getClient().getName()   + " : "   + banqueMetier.consulterCompte(cp2.getNumAccount()).getBalance());
		
		System.out.println("\n----------------Historique des op�rations--------------------");
		
		System.out.println(cp1.getClient().getName());
		ArrayList<Operation> list = banqueMetier.listOperation(cp1.getNumAccount());
		for(Operation l : list)	System.out.println(l);
		
		System.out.println("-----------------------------------------");
		System.out.println(cp2.getClient().getName());
		list = banqueMetier.listOperation(cp2.getNumAccount());
		for(Operation l : list)	System.out.println(l);
		
		int som = 2000;
		try {			
			banqueMetier.retirer(cp1.getNumAccount(), som);
		}
		catch(RuntimeException e) {
			System.out.println("\nM." + cp1.getClient().getName() + " vous ne pouvez pas retirer " + som + "euros sur votre compte");
			//e.printStackTrace();
		}
	}
}
