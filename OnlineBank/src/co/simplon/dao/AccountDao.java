package co.simplon.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import co.simplon.entities.Account;
import co.simplon.entities.CompteCourant;
import co.simplon.entities.CompteEpargne;
import co.simplon.entities.Customer;

/* ajouter les commentaires pour la javadoc */
/* réaliser les tests avec junit */

public class AccountDao extends Dao<Account> {
	
	public AccountDao(ServletContext context) {
		super(context);
	}
	
	@Override
	public Account find(int id) {
		String str = "select * from T_Accounts where NumAct=?";
		PreparedStatement ps;
		Account account = null;
		try {
			ps = connection.prepareStatement(str);
			ps.setInt(1,id);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()){
				String type = resultSet.getString("TypeAccount");
				if(type.equalsIgnoreCase("Courant")) {
					account = new CompteCourant(resultSet.getInt(1),resultSet.getDate(2),
							resultSet.getFloat("Balance"),
							new Customer(resultSet.getInt("IdCust"),"",""),resultSet.getFloat("Decouvert"));
				}					
				else if(type.equalsIgnoreCase("Epargne")) {
					account = new CompteEpargne(resultSet.getInt(1),resultSet.getDate(2),
							resultSet.getFloat("Balance"),
							new Customer(resultSet.getInt("IdCust"),"",""),resultSet.getFloat("TauxEpargne"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return account;
	}

	@Override
	public boolean create(Account obj) {
		String str = "INSERT INTO T_Accounts (NumAct, Balance, TypeAccount, Decouvert, TauxEpargne, IdCust) VALUES (?,?,?,?,?,?);";
		PreparedStatement ps;
		boolean ok = false;		
		String type = "";
		double decouvert = 0,taux = 0;
		
		if(obj instanceof CompteCourant)	{
			type = "courant";
			decouvert = ((CompteCourant) obj).getDecouvert();			
		}
		else if (obj instanceof CompteEpargne) {
			type = "epargne";
			taux = ((CompteEpargne) obj).getTaux();
		}		
		try {
			ps = connection.prepareStatement(str);
			ps.setInt(1, obj.getNumAccount());
			ps.setDouble(2,obj.getBalance());
			ps.setString(3, type);
			ps.setDouble(4, decouvert);
			ps.setDouble(5, taux);
			ps.setInt(6, obj.getClient().getIdCust());			
			ps.executeQuery();
			ok = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	public boolean update(Account obj) {		
		String sql = "update T_Accounts set Balance=? where NumAct=?;";
		PreparedStatement ps;
		boolean ok = false;
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setDouble(1,obj.getBalance());
			ps.setInt(2,obj.getNumAccount());
			int row = ps.executeUpdate();
			if(row > 0)	ok = true;			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return ok;	
	}

	@Override
	public boolean delete(Account obj) {
/*		String str = "delete from T_Customers where IdCust=?;";	
		PreparedStatement ps;
		boolean ok = false;
		try {
			ps = connection.prepareStatement(str);
			ps.setInt(1,obj.getCode());
			int row = ps.executeUpdate();
			if(row > 0)	ok = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;*/
		return false;
	}		
}
