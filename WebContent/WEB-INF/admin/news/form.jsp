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
    		<h2>&Eacute;crire / éditer une news</h2>
    		
    		<p style="text-align: justify;">Tous les champs sont obligatoires.</p>
    		
			<c:if test="${!empty handler && handler.success != null}"><p class="success">${handler.success}</p></c:if>
			<c:if test="${!empty handler && handler.fail != null}"><p class="warning">${handler.fail}</p></c:if>
			<c:if test="${!empty handler && handler.errors['db'] != null}"><p class="warning">${handler.errors['db']}</p></c:if>
    	
    		<p></p>
    	
	    	<form method="post" action="${path}">
    			<table class="form news">
    				<tr>
    					<th><label for="postTitle">Titre</label></th>
	    				<td><input type="text" id="postTitle" name="postTitle" value="${!empty news ? news.title : ''}" /><br />
	    					<c:if test="${!empty handler && handler.errors['title'] != null}"><span class="warning">${handler.errors['title']}</span></c:if>
	    				</td>
    				</tr>
    				<tr>
    					<th><label for="postContent">Contenu</label></th>
    					<td><textarea class="longtext" id="postContent" name="postContent">${!empty news ? news.content : ''}</textarea>
    						<script type="text/javascript">CKEDITOR.replace('postContent');</script>
							<c:if test="${!empty handler && handler.errors['content'] != null}"><span class="warning">${handler.errors['content']}</span></c:if>
    					</td>
    				</tr>
	    			<tr>
    					<td class="submit" colspan="2"><input type="submit" id="postSubmit" name="postSubmit" value="Envoyer" /></td>
    				</tr>
	    		</table>
    		</form>
    	</section>
    	<%@include file="../../includes/footer.jsp" %>
    </body>
</html>