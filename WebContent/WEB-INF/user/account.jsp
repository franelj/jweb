<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Ju'Lire &raquo; Mon compte</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    </head>

    <body>
    	<%@include file="../includes/header.jsp" %>
    	<%@include file="../includes/usermenu.jsp" %>
    	
    	
    	<section id="subContent">
    		<h2>Mes informations de connexion</h2>

    		<p style="text-align: center;">Les champs suivis d'une <span class="required">*</span> sont obligatoires.</p>

			<c:if test="${!empty userHandler && userHandler.success != null}"><p class="success">${userHandler.success}</p></c:if>
			<c:if test="${!empty userHandler && userHandler.fail != null}"><p class="warning">${userHandler.fail}</p></c:if>
			<c:if test="${!empty userHandler && userHandler.errors['db'] != null}"><p class="warning">${userHandler.errors['db']}</p></c:if>

			<form method="post" action="${pageContext.request.contextPath}/user/account?edit=profile">
				<table class="form">
					<tr>
						<th><label>Votre identifiant</label></th>
						<td>${!empty user ? user.name : ''}</td>
					</tr>
					<tr>
						<th><label for="editEmail">Votre adresse email <span class="required">*</span></label></th>
						<td><input type="text" name="editEmail" id="editEmail" value="${!empty user ? user.email : ''}" /><br />
						<c:if test="${!empty userHandler && userHandler.errors['email'] != null}"><span class="warning">${userHandler.errors['email']}</span></c:if>
						</td>
					</tr>
					<tr>
						<th><label for="editPwd">Votre mot de passe <span class="required">*</span></label></th>
						<td><input type="password" name="editPwd" id="editPwd" value="" /><br />
						<c:if test="${!empty userHandler && userHandler.errors['pwd'] != null}"><span class="warning">${userHandler.errors['editEmail']}</span></c:if>
						</td>
					</tr>
					<tr>
						<th><label for="editConfPwd">Confirmation de votre mot de passe <span class="required">*</span></label></th>
						<td><input type="password" name="editConfPwd" id="editConfPwd" value="" /><br />
						<c:if test="${!empty userHandler && userHandler.errors['confPwd'] != null}"><span class="warning">${userHandler.errors['confPwd']}</span></c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="submit"><input type="submit" name="connexionSubmit" id="connexionSubmit" value="Enregistrer" /></td>
					</tr>
				</table>
			</form>

			<h2>Mon adresse</h2>

    		<p style="text-align: center;">Les champs suivis d'une <span class="required">*</span> sont obligatoires.</p>

			<c:if test="${!empty addrHandler && addrHandler.success != null}"><p class="success">${addrHandler.success}</p></c:if>
			<c:if test="${!empty addrHandler && addrHandler.fail != null}"><p class="warning">${addrHandler.fail}</p></c:if>
			<c:if test="${!empty addrHandler && addrHandler.errors['db'] != null}"><p class="warning">${addrHandler.errors['db']}</p></c:if>

			<form method="post" action="${pageContext.request.contextPath}/user/account?edit=address">
				<table class="form">
					<tr>
						<th><label for="addrLastname">Nom <span class="required">*</span></label></th>
						<td><input type="text" name="addrLastname" id="addrLastname" value="${!empty addr ? addr.lastname : ''}" /><br />
						<c:if test="${!empty addrHandler && addrHandler.errors['lastname'] != null}"><span class="warning">${addrHandler.errors['lastname']}</span></c:if></td>
					</tr>
					<tr>
						<th><label for="addrFirstname">Prénom <span class="required">*</span></label></th>
						<td><input type="text" name="addrFirstname" id="addrFirstname" value="${!empty addr ? addr.firstname : ''}" /><br />
						<c:if test="${!empty addrHandler && addrHandler.errors['firstname'] != null}"><span class="warning">${addrHandler.errors['firstname']}</span></c:if></td>
					</tr>
					<tr>
						<th><label for="addrAddress">Adresse <span class="required">*</span></label></th>
						<td><input type="text" name="addrAddress" id="addrAddress" value="${!empty addr ? addr.address : ''}" /><br />
						<c:if test="${!empty addrHandler && addrHandler.errors['address'] != null}"><span class="warning">${addrHandler.errors['address']}</span></c:if></td>
					</tr>
					<tr>
						<th><label for="addrDetails">Complément d'adresse</label></th>
						<td><input type="text" name="addrDetails" id="addrDetails" value="${!empty addr ? addr.details : ''}" /></td>
					</tr>
					<tr>
						<th><label for="addrZipcode">Code postal <span class="required">*</span></label></th>
						<td><input type="text" name="addrZipcode" id="addrZipcode" value="${!empty addr ? addr.zipcode : ''}" /><br />
						<c:if test="${!empty addrHandler && addrHandler.errors['zipcode'] != null}"><span class="warning">${addrHandler.errors['zipcode']}</span></c:if></td>
					</tr>
					<tr>
						<th><label for="addrCity">Ville <span class="required">*</span></label></th>
						<td><input type="text" name="addrCity" id="addrCity" value="${!empty addr ? addr.city : ''}" /><br />
						<c:if test="${!empty addrHandler && addrHandler.errors['city'] != null}"><span class="warning">${addrHandler.errors['city']}</span></c:if></td>
					</tr>
					<tr>
						<td colspan="2" class="submit"><input type="submit" name="addressSubmit" id="addressSubmit" value="Enregistrer" /></td>
					</tr>
				</table>
			</form>
		</section>
    	  
    	<%@include file="../includes/footer.jsp" %>
    </body>
</html>
