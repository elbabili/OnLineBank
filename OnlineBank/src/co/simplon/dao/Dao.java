package co.simplon.dao;

import java.sql.Connection;

import javax.servlet.ServletContext;

public abstract class Dao<T> {

	public static Connection connection = null;
	
	public Dao(ServletContext context) {
		connection = BankConnection.getConnection(context);
	}
	
	public abstract T find(int idC);	
	public abstract boolean create(T obj);	
	public abstract boolean update(T obj);	
	public abstract boolean delete(T obj);
}

