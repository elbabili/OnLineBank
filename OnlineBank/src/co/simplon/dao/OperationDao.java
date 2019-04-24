package co.simplon.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import co.simplon.entities.Account;
import co.simplon.entities.CompteCourant;
import co.simplon.entities.CompteEpargne;
import co.simplon.entities.Operation;
import co.simplon.entities.Retrait;
import co.simplon.entities.Versement;

public class OperationDao extends Dao<Operation> {
	
	public OperationDao(ServletContext context) {
		super(context);
	}

	@Override
	public Operation find(int id) {
		return null;
	}

	@Override
	public boolean create(Operation obj) {
		String str = "INSERT INTO T_Operations (NumOp, Amount, TypeOperation, NumAct) VALUES (?,?,?,?);";
		PreparedStatement ps;
		boolean ok = false;		
		String type = "";
		
		if(obj instanceof Retrait)	type = "retrait";			
		else if (obj instanceof Versement) 	type = "versement";
		
		try {
			ps = connection.prepareStatement(str);
			ps.setInt(1, obj.getNumOperation());
			ps.setDouble(2,obj.getAmount());
			ps.setString(3, type);
			ps.setInt(4, obj.getNumAccount());
			ps.executeQuery();
			ok = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	public boolean update(Operation obj) {
		return false;
	}

	@Override
	public boolean delete(Operation obj) {
		return false;
	}
	
	public ArrayList<Operation> listOperation(Account cp){
		String str = "select * from T_Operations where NumAct=?;";
		PreparedStatement ps;
		ArrayList<Operation> list = new ArrayList<Operation>();
		try {
			ps = connection.prepareStatement(str);
			ps.setInt(1,cp.getNumAccount());
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){				
				String type = resultSet.getString("TypeOperation");
				Operation operation = null;
				if(type.equalsIgnoreCase("versement"))
					operation = new Versement(resultSet.getInt(1),resultSet.getDouble(2),resultSet.getDate(3),resultSet.getInt(5));
				else if(type.equalsIgnoreCase("retrait"))
					operation = new Retrait(resultSet.getInt(1),resultSet.getDouble(2),resultSet.getDate(3),resultSet.getInt(5));
				list.add(operation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		return list;
	}
}
