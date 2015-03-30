<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Ju'Lire &raquo; Mon compte</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    </head>

    <body>
    	<%@include file="includes/header.jsp" %>
    	<h1>Me connecter</h1>
    	
    	<fieldset class="loginAccount">
    		<legend>Vous avez déjà un compte ?</legend>
    		
			<c:if test="${!empty handler && handler.errors['db'] != null}"><p class="warning">${handler.errors['db']}</p></c:if>
    		
			<form method="post" action="${pageContext.request.contextPath}/login?action=login">
				<table class="form">
					<tr>
						<th><label for="logId">Identifiant</label></th>
						<td><input type="text" id="logId" name="logId" value="${!empty user ? user.name : ''}" /><br />
						<c:if test="${!empty handler && handler.errors['logId'] != null}"><span class="warning">${handler.errors['logId']}</span></c:if>
						</td>
					</tr>
					<tr>
						<th><label for="logPwd">Mot de passe</label></th>
						<td><input type="password" id="logPwd" name="logPwd" value="" /><br />
						<c:if test="${!empty handler && handler.errors['logPwd'] != null}"><span class="warning">${handler.errors['logPwd']}</span></c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="submit"><input type="submit" id="logSubmit" name="logSubmit" value="Se connecter" /></td>
					</tr>
				</table>
			</form>
		</fieldset>
		
		
    	<fieldset class="createAccount">
    		<legend>Vous n'avez pas de compte ?</legend>

    		<p style="text-align: center;">Les champs suivis d'une <span class="required">*</span> sont obligatoires.</p>

			<c:if test="${!empty handler && handler.success != null}"><p class="success">${handler.success}</p></c:if>
			<c:if test="${!empty handler && handler.fail != null}"><p class="warning">${handler.fail}</p></c:if>


			<form method="post" action="${pageContext.request.contextPath}/login?action=create">
				<table class="form">
					<tr>
						<th><label for="createId">Identifiant <span class="required">*</span></label></th>
						<td><input type="text" id="createId" name="createId" value="${!empty cr ? cr.name : ''}" /><br />
						<c:if test="${!empty handler && handler.errors['createId'] != null}"><span class="warning">${handler.errors['createId']}</span></c:if>
						</td>
					</tr>
					<tr>
						<th><label for="createEmail">Adresse email <span class="required">*</span></label></th>
						<td><input type="text" id="createEmail" name="createEmail" value="${!empty cr ? cr.email : ''}" /><br />
						<c:if test="${!empty handler && handler.errors['createEmail'] != null}"><span class="warning">${handler.errors['createEmail']}</span></c:if>
						</td>
					</tr>
					<tr>
						<th><label for="createPwd">Mot de passe <span class="required">*</span></label></th>
						<td><input type="password" id="createPwd" name="createPwd" value="" /><br />
						<c:if test="${!empty handler && handler.errors['createPwd'] != null}"><span class="warning">${handler.errors['createPwd']}</span></c:if>
						</td>
					</tr>
					<tr>
						<th><label for="createConfPwd">Confirmation du mot de passe <span class="required">*</span></label></th>
						<td><input type="password" id="createConfPwd" name="createConfPwd" value="" /><br />
						<c:if test="${!empty handler && handler.errors['createConfPwd'] != null}"><span class="warning">${handler.errors['createConfPwd']}</span></c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="submit"><input type="submit" id="createSubmit" name="createSubmit" value="Créer un compte" /></td>
					</tr>
				</table>
			</form>
		</fieldset>
    	<%@include file="includes/footer.jsp" %>
    </body>
</html>