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
    		<h2>&Eacute;diter une news</h2>
    		
			<c:if test="${!empty handler && handler.success != null}"><p class="success">${handler.success}</p></c:if>
			<c:if test="${!empty handler && handler.fail != null}"><p class="warning">${handler.fail}</p></c:if>
			<c:if test="${!empty handler && handler.errors['db'] != null}"><p class="warning">${handler.errors['db']}</p></c:if>
			<c:if test="${!empty nHandler && nHandler.success != null}"><p class="success">${nHandler.success}</p></c:if>
			<c:if test="${!empty nHandler && nHandler.fail != null}"><p class="warning">${nHandler.fail}</p></c:if>
			<c:if test="${!empty nHandler && nHandler.errors['db'] != null}"><p class="warning">${nHandler.errors['db']}</p></c:if>
    		
    		<table class="newsList">
    			<tr>
    				<th class="id">N°</th>
    				<th class="title">Titre</th>
    				<th class="postdate">Posté le</th>
    				<th class="updatedate">Mis à jour le</th>
    				<th class="author">Par</th>
    				<th class="edit">Envoyer</th>
    				<th class="edit">&Eacute;diter</th>
    				<th class="delete">Supprimer</th>
    			</tr>
    			<c:forEach items="${news}" var="element"> 
				<tr>
    				<td>${element.id}</td>
    				<td>${element.title}</td>
    				<td>${element.createdAt}</td>
    				<td>${element.updatedAt}</td>
    				<td>${element.authorName}</td>
    				<td><a href="${pageContext.request.contextPath}/admin/news/edit?send=${element.id}"><img src="${pageContext.request.contextPath}/img/send.png" alt="send" /></a></td>
    				<td><a href="${pageContext.request.contextPath}/admin/news/edit?id=${element.id}"><img src="${pageContext.request.contextPath}/img/edit.png" alt="edit" /></a></td>
    				<td><a href="${pageContext.request.contextPath}/admin/news/edit?delete=${element.id}"><img src="${pageContext.request.contextPath}/img/delete.png" alt="delete" /></a></td>
  				</tr>
				</c:forEach>
    		</table>
    	</section>
    	<%@include file="../../includes/footer.jsp" %>
    </body>
</html>