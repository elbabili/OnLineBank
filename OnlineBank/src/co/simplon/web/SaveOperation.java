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
 * Servlet implementation class SaveOperation
 */
@WebServlet("/saveOperation")
public class SaveOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveOperation() {
       
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
		else session.setAttribute("connectedUser", user);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("connectedUser");
		if(user == null) {
			request.getRequestDispatcher( "/login.jsp" ).forward( request, response );
		}
		else session.setAttribute("connectedUser", user);
		
		String codeCpte = request.getParameter("codeCompte");		
		int codeCompte = Integer.parseInt(codeCpte);		
		String typeOperation = request.getParameter( "typeOperation" );				
        double montant = Double.parseDouble(request.getParameter( "montant" ));
        BanqueMetier banqueMetier = (BanqueMetier) request.getSession().getAttribute("banqueMetier");
        
        String errorMsg = "";        
        try {
	        switch(typeOperation) {
	        	case "versement" : banqueMetier.verser(codeCompte, montant);
	        		break;
	        		
	        	case "retrait"   : banqueMetier.retirer(codeCompte, montant);
	        		break;
	        		
	        	case "virement"  : String compteDest = request.getParameter( "codeCompteDest");
	    							int codeCompteDest = 0;
	    							if(compteDest != "")  			codeCompteDest = Integer.parseInt(compteDest);
	        						banqueMetier.virement(codeCompte, codeCompteDest, montant);
	        						break;
	        		
	        	default : throw new RuntimeException("Operation impossible");
	        }
        }
        catch(RuntimeException e) {
        	errorMsg = e.getMessage();
        }
        
        ArrayList<Operation>	listOperations = banqueMetier.listOperation(codeCompte);
        Account compte = banqueMetier.consulterCompte(codeCompte);
         
        session.setAttribute("codeCompte", codeCompte);
        session.setAttribute( "compte", compte );
        session.setAttribute( "listOperations", listOperations);
        session.setAttribute( "errorMsg", errorMsg);
        request.getRequestDispatcher( "/vue.jsp" ).forward( request, response );        
	}
}
