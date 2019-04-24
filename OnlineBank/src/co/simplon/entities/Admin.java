package co.simplon.entities;

public class Admin extends User {
	private String rights;

	public Admin(int id, String login, String password, int connectionNumber, String rights) {
		super(id, login, password, connectionNumber);
		this.rights = rights;
	}	

	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
}
