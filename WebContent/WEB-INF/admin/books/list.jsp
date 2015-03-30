<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Ju'Lire &raquo; &Eacute;diter une fiche livre</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    </head>

    <body>
    	<%@include file="../../includes/header.jsp" %>
    	<%@include file="../menu.jsp" %>
    	
	   	<section id="subContent">
    		<h2>&Eacute;diter une fiche livre</h2>
    		
    		<c:choose>
				<c:when test="${!empty bookHandler && bookHandler.success != null}"><p class="success">${bookHandler.success}</p></c:when>
				<c:when test="${!empty bookHandler && bookHandler.errors['db'] != null}"><p class="warning">${bookHandler.errors['db']}</p></c:when>
				<c:otherwise></c:otherwise>
			</c:choose>   	
    		
    		<table class="newsList">
    			<tr>
    				<th class="id">N°</th>
    				<th class="title">Titre</th>
    				<th class="">Série</th>
    				<th class="">Auteur</th>
    				<th class="">Date de sortie</th>
    				<th class="">&Eacute;diteur</th>
    				<th class="edit">&Eacute;diter</th>
    				<th class="delete">Supprimer</th>
    			</tr>
    			<c:forEach items="${books}" var="element"> 
				<tr>
    				<td>${element.id}</td>
    				<td>${element.title}</td>
    				<td>${element.serie}</td>
    				<td>${element.authorName}</td>
    				<td>${element.date}</td>
    				<td>${element.editor}</td>
    				<td><a href="${pageContext.request.contextPath}/admin/book/edit?id=${element.id}"><img src="${pageContext.request.contextPath}/img/edit.png" alt="edit" /></a></td>
    				<td><a href="${pageContext.request.contextPath}/admin/book/edit?delete=${element.id}"><img src="${pageContext.request.contextPath}/img/delete.png" alt="delete" /></a></td>
  				</tr>
				</c:forEach>
    		</table>
    	</section>
    	<%@include file="../../includes/footer.jsp" %>
    </body>
</html>