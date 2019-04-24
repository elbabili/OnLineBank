
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="th"%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="utf-8" />
		<link rel="stylesheet" type="text/css" href="styles.css" />
		<title>Banque en Ligne</title>
	</head>

<body>

	<%
        	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        	response.setDateHeader("Expires", 0); // Proxies.
    %>

	<header>
			<div class="navbar navbar-inverse ">
				<div class="container-fluid">
					<ul class="nav navbar-nav">
						<li> <a> Welcome ${connectedUser.login} </a> </li>
								
					</ul>
					<ul class="nav navbar-nav navbar-right" >
						<li> <a href="logout.jsp">Logout</a> </li>	
					</ul>
				</div>
			</div>
	</header>


	<div>			
		<div class="col-md-6">
			<div class="panel panel-primary">
				<div class="panel panel-heading">Consultation d'un compte</div>
				<div class="panel-body">
					<form method="post" action="viewAccount">
						<div>
							<label>Code Compte : </label> 
							<input type="text" name="codeCompte" value="${codeCompte}" />
							<button type="submit" class="btn btn-primary">ok</button>
							<div class="errorMessage">${errorMessage}</div>
						</div>
					</form>
				</div>
			</div>
			
			<th:if test="${not empty compte}">	
				<div class="panel panel-primary">
					<div class="panel panel-heading">Informations sur le compte</div>
					<div class="panel-body">
						<div>	<label>Client :</label> ${compte.client.name}		</div>
						<div>	<label>Code compte : </label> ${compte.numAccount}		</div>
						<div>	<label>Solde : </label> ${compte.balance}		</div>
						<div>	<label>Date création : </label> ${compte.dateCreation}	</div>						
						<div>	<label>Type : </label> ${compte['class'].simpleName}	</div>
					
						<div>					
							<th:if test="${compte['class'].simpleName eq 'CompteCourant'}">
									<label>Découvert : </label> ${compte.decouvert}
							</th:if> 
						</div>								
						<div>
							<th:if test="${compte['class'].simpleName eq 'CompteEpargne'}">
									<label>Taux : </label> ${compte.taux}
							</th:if>
						</div>
												
					</div>
				</div>
			</th:if>
	
		</div>
	
		<th:if test="${not empty compte}">
			<div class="col-md-6">
				<th:if test="${connectedUser['class'].simpleName eq 'Admin'}">
					<div class="panel panel-primary">
						<div class="panel panel-heading">Opérations sur le compte</div>
																										 
							<div class="panel-body">
								<form method="post" action="saveOperation">
									<div>
										<label> Compte </label>
										<input type="hidden" name="codeCompte" value="${codeCompte}">
										<label> ${codeCompte} </label> 												
									</div>											
									
									<div>
										<input type = "radio" name="typeOperation" value="versement" checked="checked" 
												onchange="document.getElementById('forVirement').style.display='none'" />
										<label>Versement</label>
										
										<input type = "radio" name="typeOperation" value="retrait" 
												onchange="document.getElementById('forVirement').style.display='none'" />
										<label>Retrait</label>
										
										<input type = "radio" name="typeOperation" value="virement" 
												onchange="document.getElementById('forVirement').style.display='block'" />
										<label>Virement</label>
									</div>
									
									<div id="forVirement" style="display:none">
										<label >Vers : </label>
										<input type="text" name="codeCompteDest">
									</div>
									
									<div>
										<label>Montant : </label>
										<input type="text" name="montant">
									</div>
									
									<div class="errorMsg">${errorMsg}</div>
									
									<div>
										<button type="submit" class="btn btn-primary"> Save </button>
									</div>
									
								</form>
							</div>													
					</div>
				</th:if>
				
				<div class="panel panel-primary">
					<div class="panel panel-heading">Liste des opérations</div>
					<div class="panel-body">
						<table class="table table-striped">
							<tr>
								<th>Numéro</th>
						 		<th>Type</th>
								<th>Date</th>
								<th>Montant</th>
							</tr>
							<th:forEach items="#{listOperations}" var="line">							
								<tr>
									<td> ${line.numOperation} </td>
									<td> ${line['class'].simpleName} </td>
									<td> ${line.dateOperation} </td>
									<td> ${line.amount} </td>
								</tr>
							</th:forEach>
						</table>
					</div>
				</div>
			</div>
		</th:if>
	</div>
</body>

</html>