package co.simplon.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.simplon.dao.UserDao;
import co.simplon.entities.Account;
import co.simplon.entities.Operation;
import co.simplon.entities.User;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns="/controller", loadOnStartup=1)
public class Controller extends HttpServlet {
    
    private static final long serialVersionUID = -4319076288258537575L;

    @Override
    public void init() throws ServletException {
        
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	session.setAttribute( "connectedUser", null );	//correspond à l'utilisateur
        session.setAttribute( "compte", null);			//le compte en cours  
        session.setAttribute( "listOperations", null);	//liste des opérations sur ce compte
        session.setAttribute( "codeCompte", "");		//le code compte 
        //La première fois que le controleur est sollicité, je dois initialiser tous les champs à vide ou null
        
    	//avant de renvoyer vers la fenêtre de log, j'initialise les champs que je transmet via la requete à login.jsp
       /* request.setAttribute( "login", "" );
        request.setAttribute( "password", "" );
        request.setAttribute( "errorMessage", "" );*/
        request.getRequestDispatcher( "/login.jsp" ).forward( request, response );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter( "txtLogin" );
        String password = request.getParameter( "txtPassword" );
        //lorsque l'utilisateur a saisi les données de log, je les réinjecte pour éviter la resaisi en cas d'erreur
        request.setAttribute( "login", login );
        request.setAttribute( "password", password );
        //puis je consulte ma base pour vérifier si l'utilisateur est en base ou pas
        //j'initialise ces attributs de sorte que s'ils sont vide, je n'afficherai pas les champs correspondant dans ma jsp
        UserDao userDao = new UserDao(this.getServletContext());
        
        User connectedUser = userDao.isValidLogin( login, password );    
        if ( connectedUser != null ) {            			// si l'utilisateur existe, je crée une session si elle n'existe pas
            HttpSession session = request.getSession( true );	
            session.setAttribute( "connectedUser", connectedUser ); // j'y injecte tous les attributs pour les exploiter
            session.setAttribute( "compte", null);
            session.setAttribute( "listOperations", null);
            request.getRequestDispatcher( "/vue.jsp" ).forward( request, response );	//je renvois vers la vue
        } else {     
        	//message d'erreur et surtout, pas d'accès à notre appli, on renvoi vers login
            request.setAttribute( "errorMessage", "Vous n'avez pas accès à cette espace privé !" );         
            request.getRequestDispatcher( "/login.jsp" ).forward( request, response );           
        }        
    }
}
