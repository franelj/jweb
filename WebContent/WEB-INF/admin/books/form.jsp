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
    		<h2>Ajouter / éditer un livre</h2>
    		
			<p style="text-align: center;">Les champs suivis d'une <span class="required">*</span> sont obligatoires.</p>
    		
    		<c:choose>
				<c:when test="${!empty bookHandler && bookHandler.fail != null}"><p class="warning">${bookHandler.fail}</p></c:when>
				<c:when test="${!empty bookHandler && bookHandler.success != null}"><p class="success">${bookHandler.success}</p></c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
			<c:if test="${!empty bookHandler && bookHandler.errors['db'] != null}"><p class="warning">${bookHandler.errors['db']}</p></c:if>
			<c:if test="${!empty authorHandler && authorHandler.errors['db'] != null}"><p class="warning">${authorHandler.errors['db']}</p></c:if>
    	
	    	<form method="post" action="${path}" enctype="multipart/form-data"><!--  -->
    			<table class="form news">
    				<tr>
    					<th><label for="postTitle">Titre <span class="required">*</span></label></th>
	    				<td><input type="text" id="postTitle" name="postTitle" value="${!empty book ? book.title : ''}" /><br />
	    				<c:if test="${!empty bookHandler && bookHandler.errors['title'] != null}"><span class="warning">${bookHandler.errors['title']}</span></c:if></td>
    				</tr>
    				<tr>
    					<th><label for="postSerie">Série</label></th>
	    				<td><input type="text" id="postSerie" name="postSerie" value="${!empty book ? book.serie : ''}" /></td>
    				</tr>
    				<tr>
    					<th><label for="postAuthor">Auteur <span class="required">*</span></label></th>
	    				<td><select id="postAuthor" name="postAuthor">
	    					<c:forEach items="${authors}" var="item">
	    						<option value="${item.id}" ${!empty book && item.id == book.author ? 'selected' : ''}>${item.firstname} ${item.lastname}</option>
							</c:forEach>
	    					</select><br />
	    					<c:if test="${!empty bookHandler && bookHandler.errors['author'] != null}"><span class="warning">${bookHandler.errors['author']}</span></c:if>
	    				</td>
    				</tr>
    				<tr>
    					<th><label for="postDate">Date de sortie <span class="required">*</span></label></th>
	    				<td><input class="date" type="date" id="postDate" name="postDate" value="${!empty book ? book.date : ''}" /><br />
	    				<c:if test="${!empty bookHandler && bookHandler.errors['date'] != null}"><span class="warning">${bookHandler.errors['date']}</span></c:if></td>
    				</tr>
	   				<tr>
    					<th><label for="postFile">Couverture <span class="required">*</span></label></th>
	    				<td>
	    					<input type="file" id="postFile" name="postFile" value="" /><br />
	    					<span class="formDetail">Seuls les fichiers en .png, .jpg et .gif de 3Mo ou moins sont autorisés.</span><br />
    						<c:choose>
								<c:when test="${!empty book && book.file != null}"><img src="${pageContext.request.contextPath}/img/uploads/${book.file}" alt="" class="upImg" /></c:when>
								<c:otherwise></c:otherwise>
							</c:choose><br />
	    					<c:if test="${!empty bookHandler && bookHandler.errors['file'] != null}"><span class="warning">${bookHandler.errors['file']}</span></c:if>
	    				</td>
    				</tr>
    				<tr>
    					<th><label for="postSummary">Résumé <span class="required">*</span></label></th>
    					<td><textarea class="longtext" id="postSummary" name="postSummary">${!empty book ? book.summary : ''}</textarea>
    					<script type="text/javascript">CKEDITOR.replace('postSummary');</script><br />
	    				<c:if test="${!empty bookHandler && bookHandler.errors['summary'] != null}"><span class="warning">${bookHandler.errors['summary']}</span></c:if></td>
    				</tr>
    				<tr>
    					<th><label for="postIsbn">ISBN <span class="required">*</span></label></th>
	    				<td><input type="text" id="postIsbn" name="postIsbn" value="${!empty book ? book.isbn : ''}" /><br />
	    				<c:if test="${!empty bookHandler && bookHandler.errors['isbn'] != null}"><span class="warning">${bookHandler.errors['isbn']}</span></c:if></td>
    				</tr>
    				<tr>
    					<th><label for="postPrice">Prix <span class="required">*</span></label></th>
	    				<td><input class="price" type="text" id="postPrice" name="postPrice" value="${!empty book ? book.price : ''}" /> &euro;<br />
	    				<c:if test="${!empty bookHandler && bookHandler.errors['price'] != null}"><span class="warning">${bookHandler.errors['price']}</span></c:if></td>
    				</tr>
    				<tr>
    					<th><label for="postEditor">&Eacute;diteur <span class="required">*</span></label></th>
	    				<td><input type="text" id="postEditor" name="postEditor" value="${!empty book ? book.editor : ''}" /><br />
	    				<c:if test="${!empty bookHandler && bookHandler.errors['editor'] != null}"><span class="warning">${bookHandler.errors['editor']}</span></c:if></td>
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