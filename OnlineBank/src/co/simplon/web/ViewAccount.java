package co.simplon.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.simplon.entities.Account;
import co.simplon.entities.Operation;
import co.simplon.entities.User;
import co.simplon.metier.BanqueMetier;

/**
 * Servlet implementation class ViewAccount
 */
@WebServlet(urlPatterns="/viewAccount", loadOnStartup=1)
public class ViewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BanqueMetier banqueMetier ;
	
	@Override
	public void init() throws ServletException {
		banqueMetier = new BanqueMetier(this.getServletContext());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("connectedUSer");
		if(user == null) {
			request.getRequestDispatcher( "/login.jsp" ).forward( request, response );
		}
		else {		
			request.setAttribute( "codeCompte", "" );
	        request.setAttribute( "errorMessage", "" );
	        request.setAttribute( "errorMsg", "" );
	        session.setAttribute("connectedUSer", user);
	        request.getRequestDispatcher( "/vue.jsp" ).forward( request, response );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User connectedUser = (User)request.getSession().getAttribute("connectedUser");
		String codeCompte = request.getParameter( "codeCompte" );		//on souhaite visualiser un compte à partir du code compte
		Account compte = null;
		ArrayList<Operation>	listOperations = null;
		if(codeCompte.equalsIgnoreCase(""))	{
			
		}
		else {
	        int codeCpte = Integer.parseInt(codeCompte);        
	        compte = banqueMetier.consulterCompte(codeCpte);		//l'objet banqueMetier instancié dans init sert d'interface avec notre bdd
	        listOperations = banqueMetier.listOperation(codeCpte);
		}
        if(compte != null)	{    
        	HttpSession session = request.getSession();		// à partir de la session en cours, j'injecte les données en vue de l'affichage
        	session.setAttribute( "connectedUser", connectedUser);
        	session.setAttribute( "codeCompte", codeCompte );
            session.setAttribute( "compte", compte );
            session.setAttribute( "listOperations", listOperations );
            session.setAttribute( "banqueMetier", banqueMetier);
            request.setAttribute( "errorMsg", "" );
        	request.getRequestDispatcher( "/vue.jsp" ).forward( request, response );
        }
        else {        
            request.setAttribute( "errorMessage", "Compte introuvable" );   
            HttpSession session = request.getSession(); 
            session.setAttribute( "connectedUSer", connectedUser);
            session.setAttribute( "codeCompte", codeCompte );
            session.setAttribute( "compte", null );
            session.setAttribute( "listOperations", null );
            request.getRequestDispatcher( "/vue.jsp" ).forward( request, response );           
        }      
	}
}
