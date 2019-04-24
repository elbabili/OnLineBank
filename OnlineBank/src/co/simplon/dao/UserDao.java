package co.simplon.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;

import co.simplon.entities.Admin;
import co.simplon.entities.User;

public class UserDao extends Dao<User> {
	
	public UserDao(ServletContext context) {
		super(context);
	}

    public User isValidLogin( String login, String password ) {      
         
        String strSql = "SELECT * FROM T_Users WHERE login=? AND password=?";
        try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
            statement.setString( 1, login );
            statement.setString( 2, password );
            try ( ResultSet resultSet = statement.executeQuery() ) {
                if ( resultSet.next() ) {
                    User user = new User(resultSet.getInt("idUser"),resultSet.getString("Login"),resultSet.getString("Password"),resultSet.getInt("ConnectionNumber"));
                    //incrementer connection number
                    
                    //est-ce un admin
                    String rights = isAdminUser(user.getIdUser());
                    if(rights != null)	{
                    	return new Admin(user.getIdUser(),user.getLogin(),user.getPassword(),user.getConnectionNumber(),rights);
                    }
                    else return user;
                } else	return null;                
            }
        
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
    }
    
    public String isAdminUser(int id) {
    	String sql = "select * from T_Users inner join T_Admins on T_Users.IdUser = T_Admins.IdUser where T_Users.IdUser=?;";    	
    	try ( PreparedStatement statement  = connection.prepareStatement( sql ) ) {
	             statement.setInt( 1, id );
	             try ( ResultSet resultSet = statement.executeQuery() ) {
	                 if ( resultSet.next() ) 	return resultSet.getString("Rights");
	             }
             
	    } catch ( Exception exception ) {
	        throw new RuntimeException( exception );
	    }
    	return null;
    }

	@Override
	public User find(int id) {		
		return null;
	}

	@Override
	public boolean create(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

}