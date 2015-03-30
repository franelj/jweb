<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Ju'Lire &raquo; &Eacute;diter une news</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    </head>

    <body>
    	<%@include file="../../includes/header.jsp" %>
    	<%@include file="../menu.jsp" %>
    	
	   	<section id="subContent">
    		<h2>Gérer les utilisateurs</h2>
    		
			<c:if test="${!empty handler && handler.success != null}"><p class="success">${handler.success}</p></c:if>
			<c:if test="${!empty handler && handler.fail != null}"><p class="warning">${handler.fail}</p></c:if>
			<c:if test="${!empty handler && handler.errors['db'] != null}"><p class="warning">${handler.errors['db']}</p></c:if>
    		
    		<table class="newsList">
    			<tr>
    				<th class="id">Identifiant</th>
    				<th class="title">Nom</th>
    				<th class="postdate">Email</th>
    				<th class="updatedate">Inscrit le</th>
    				<th class="delete">Supprimer</th>
    			</tr>
    			<c:forEach items="${users}" var="element"> 
				<tr>
    				<td>${element.id}</td>
    				<td>${element.name}</td>
    				<td>${element.email}</td>
    				<td>${element.creationDate}</td>
    				<c:choose>
						<c:when test="${element.isAdmin == false}"><td><a href="${pageContext.request.contextPath}/admin/users?delete=${element.id}"><img src="${pageContext.request.contextPath}/img/delete.png" alt="delete" /></a></td></c:when>
						<c:when test="${element.isAdmin == true}"><td></td></c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
  				</tr>
				</c:forEach>
    		</table>
    	</section>
    	<%@include file="../../includes/footer.jsp" %>
    </body>
</html>