<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Ju'Lire &raquo; ${!empty news ? news.title : ''}</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    </head>

    <body>
    	<%@include file="includes/header.jsp" %>
    	<h1>${!empty news ? news.title : ''}</h1>
    	
   		<c:choose>
			<c:when test="${!empty newsError}"><p class="warning">${newsError}</p></c:when>
			<c:otherwise></c:otherwise>
		</c:choose>   	

		<div class="newscontent">${!empty news ? news.content : ''}</div>
		
		<p class="metadata">Posté le ${!empty news ? news.createdAt : ''} par ${!empty news ? news.authorName : ''}</p>
		
    	<%@include file="includes/footer.jsp" %>
    </body>
</html>