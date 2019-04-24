package co.simplon.entities;


public class Customer {     
	private int idCust;
	private String name;
	private String firstName;
	private String email;
	
	public Customer(String nom, String firstName, String email) {
		this.name = nom;
		this.firstName = firstName;
		this.email = email;
	}
	
	public Customer(int codeCust, String nom, String firstName) {
		this.idCust = codeCust;
		this.name = nom;
		this.firstName = firstName;
		email = "";
	}

	public int getIdCust() {
		return idCust;
	}

	public void setIdCust(int code) {
		this.idCust = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String nom) {
		this.name = nom;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String prenom) {
		this.firstName = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Client [codeClient=" + idCust + ", nom=" + name + ", prenom=" + firstName + "]";
	}
}
