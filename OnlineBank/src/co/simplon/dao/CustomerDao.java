package co.simplon.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import co.simplon.entities.Customer;

public class CustomerDao extends Dao<Customer> {
	
	public CustomerDao(ServletContext context) {
		super(context);
	}

	@Override
	public Customer find(int id) {
		String str = "select * from T_Customers where IdCust=?";
		PreparedStatement ps;
		Customer client = null;
		try {
			ps = connection.prepareStatement(str);
			ps.setInt(1,id);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()){				
				client = new Customer(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return client;
	}

	@Override
	public boolean create(Customer obj) {
		String str = "INSERT INTO T_Customers (IdCust, Name, firstName) VALUES (?, ? , ? );";
		PreparedStatement ps;
		boolean ok = false;
		try {
			ps = connection.prepareStatement(str);
			ps.setInt(1, obj.getIdCust());
			ps.setString(2,obj.getName());
			ps.setString(3,obj.getFirstName());
			ps.executeQuery();
			ok = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	public boolean update(Customer obj) {		
		String str = " update T_Customers set Name=?,FirstName=? where IdCust=?;";		
		PreparedStatement ps;
		boolean ok = false;
		try {
			ps = connection.prepareStatement(str);
			ps.setString(1,obj.getName());
			ps.setString(2,obj.getFirstName());
			ps.setInt(3,obj.getIdCust());
			int row = ps.executeUpdate();
			if(row > 0)	ok = true;			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return ok;
	}

	@Override
	public boolean delete(Customer obj) {
		String str = "delete from T_Customers where IdCust=?;";	
		PreparedStatement ps;
		boolean ok = false;
		try {
			ps = connection.prepareStatement(str);
			ps.setInt(1,obj.getIdCust());
			int row = ps.executeUpdate();
			if(row > 0)	ok = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}
	
	public ArrayList<Customer> listCustomer(){
		String sql = "select * from T_Customers;";
		PreparedStatement ps;
		ArrayList<Customer> list = new ArrayList<Customer>();
		
		try {
			ps = connection.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){				
				Customer customer = new Customer(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
				list.add(customer);
			}		
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return list;
	}
}
