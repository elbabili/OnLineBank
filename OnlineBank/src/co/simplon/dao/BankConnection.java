package co.simplon.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;


public class BankConnection { 
	private static Connection connection = null;	
	
	private BankConnection(ServletContext context) {		
		
		try {
			Class.forName( context.getInitParameter( "JDBC_DRIVER" ) );
            String url = context.getInitParameter( "JDBC_URL" );
            String log = context.getInitParameter( "JDBC_LOGIN" );
            String pwd = context.getInitParameter( "JDBC_PASSWORD" );
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(url, log, pwd);
			Statement statement = connection.createStatement();
			statement.executeQuery("create database if not exists Bank;");
			statement.executeQuery("use Bank;");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 				
		catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static Connection getConnection(ServletContext context) {
		if(connection == null) {
			new BankConnection(context);
		}
		return connection;
	}
}