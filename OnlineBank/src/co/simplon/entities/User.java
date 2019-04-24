package co.simplon.entities;

public class User {
	private int idUser;
	private String login;
	private String password;
	private int connectionNumber;
	
	public User(int id, String login, String password, int connectionNumber) {
		this.idUser = id;
		this.login = login;
		this.password = password;
		this.connectionNumber = connectionNumber;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getConnectionNumber() {
		return connectionNumber;
	}

	public void setConnectionNumber(int connectionNumber) {
		this.connectionNumber = connectionNumber;
	}	
}
