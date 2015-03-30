<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Ju'Lire &raquo; Administration</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>
    </head>

    <body>
    	<%@include file="../../includes/header.jsp" %>
    	<%@include file="../menu.jsp" %>
    	
	   	<section id="subContent">
    		<h2>Ajouter / éditer un auteur</h2>
    		
    		<p style="text-align: justify;">Tous les champs sont obligatoires.</p>
    		
			<c:if test="${!empty handler && handler.success != null}"><p class="success">${handler.success}</p></c:if>
			<c:if test="${!empty handler && handler.fail != null}"><p class="warning">${handler.fail}</p></c:if>
			<c:if test="${!empty handler && handler.errors['db'] != null}"><p class="warning">${handler.errors['db']}</p></c:if>
			<c:if test="${!empty handler && handler.errors['firstname'] != null}"><p class="warning">${handler.errors['firstname']}</p></c:if>
			<c:if test="${!empty handler && handler.errors['lastname'] != null}"><p class="warning">${handler.errors['lastname']}</p></c:if>
			<c:if test="${!empty handler && handler.errors['resume'] != null}"><p class="warning">${handler.errors['resume']}</p></c:if>
    	
    		<p></p>
    	
	    	<form method="post" action="${!empty path ? path : ''}">
    			<table class="form news">
    				<tr>
    					<th><label for="authorLastname">Nom</label></th>
	    				<td><input type="text" id="authorLastname" name="authorLastname" value="${!empty author ? author.lastname : ''}" /></td>
    				</tr>
    				<tr>
    					<th><label for="authorFirstname">Prénom</label></th>
	    				<td><input type="text" id="authorFirstname" name="authorFirstname" value="${!empty author ? author.firstname : ''}" /></td>
    				</tr>
    				<tr>
    					<th><label for="authorResume">Biographie</label></th>
    					<td><textarea class="longtext" id="authorResume" name="authorResume">${!empty author ? author.resume : ''}</textarea>
    					<script type="text/javascript">CKEDITOR.replace('authorResume');</script></td>
    				</tr>
	    			<tr>
    					<td class="submit" colspan="2"><input type="submit" id="authorSubmit" name="authorSubmit" value="Envoyer" /></td>
    				</tr>
	    		</table>
    		</form>
    	</section>
    	<%@include file="../../includes/footer.jsp" %>
    </body>
</html>