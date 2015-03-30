<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Ju'Lire &raquo; &Eacute;diter une fiche auteur</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    </head>

    <body>
    	<%@include file="../../includes/header.jsp" %>
    	<%@include file="../menu.jsp" %>
    	
	   	<section id="subContent">
    		<h2>&Eacute;diter une fiche auteur</h2>
    		
    		<c:choose>
				<c:when test="${!empty handler && handler.success != null}"><p class="success">${handler.success}</p></c:when>
				<c:when test="${!empty handler && handler.errors['db'] != null}"><p class="warning">${handler.errors['db']}</p></c:when>
				<c:otherwise></c:otherwise>
			</c:choose>   	
    		
    		<table class="newsList">
    			<tr>
    				<th class="id">N°</th>
    				<th class="title">Nom</th>
    				<th class="postdate">Prénom</th>
    				<th class="edit">&Eacute;diter</th>
    				<th class="delete">Supprimer</th>
    			</tr>
    			<c:forEach items="${authors}" var="element"> 
				<tr>
    				<td>${element.id}</td>
    				<td>${element.lastname}</td>
    				<td>${element.firstname}</td>
    				<td><a href="${pageContext.request.contextPath}/admin/author/edit?id=${element.id}"><img src="${pageContext.request.contextPath}/img/edit.png" alt="edit" /></a></td>
    				<td><a href="${pageContext.request.contextPath}/admin/author/edit?delete=${element.id}"><img src="${pageContext.request.contextPath}/img/delete.png" alt="delete" /></a></td>
  				</tr>
				</c:forEach>
    		</table>
    	</section>
    	<%@include file="../../includes/footer.jsp" %>
    </body>
</html>