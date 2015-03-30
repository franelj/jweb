<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Ju'Lire &raquo; ${!empty book ? book.title : ''} - ${!empty book ? book.authorName : ''}</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/js/fancybox/jquery.fancybox-1.3.4.css" />
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/fancybox/jquery.easing-1.4.pack.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$("a.fancybox").fancybox();
			});
		</script>
    </head>

    <body>
    	<%@include file="includes/header.jsp" %>
    	<h1>${!empty book ? book.title : ''}</h1>
    	
   		<c:choose>
			<c:when test="${!empty book && book.serie != null}"><h3>${book.serie}</h3></c:when>
			<c:when test="${!empty bookError}"><p class="warning">${bookError}</p></c:when>
			<c:otherwise></c:otherwise>
		</c:choose>   	

		<a class="fancybox" href="${pageContext.request.contextPath}/img/uploads/${book.file}">
		<img src="${pageContext.request.contextPath}/img/uploads/${book.file}" alt="" class="floatImg" /></a>

		<p><b>Auteur :</b> ${!empty book ? book.authorName : ''}<br />
		<b>&Eacute;diteur :</b> ${!empty book ? book.editor : ''}<br />
		<b>Date de parution :</b> ${!empty book ? book.date : ''}</p>
		
		<p><b>Résumé :</b></p>
		<div class="summary">${!empty book ? book.summary : ''}</div>


		<p><b>Prix :</b> ${!empty book ? book.price : ''} &euro;<br />
		<b>ISBN :</b> ${!empty book ? book.isbn : ''}</p>
		
		<br class="clear" />
		</section>
		
		<section id="comments">
			<h1>Commentaires des lecteurs</h1>
			
			<c:if test="${!empty commentHandler && commentHandler.errors['count'] != null}"><p class="warning">${commentHandler.errors['count']}</p></c:if>
			
			<c:forEach items="${comments}" var="element">
			<div class="comment">
				<h4>Posté par ${element.authorName} le ${element.date}</h4>
				
				<p style="text-align: justify;">${element.content}</p>
			</div>
			</c:forEach>
		</section>
		
		<section id="commentForm">
			<h1>Ajouter un commentaire</h1>

			<c:if test="${!empty commentHandler && commentHandler.success != null}"><p class="success">${commentHandler.success}</p></c:if>
			<c:if test="${!empty commentHandler && commentHandler.fail != null}"><p class="warning">${commentHandler.fail}</p></c:if>
			<c:if test="${!empty commentHandler && commentHandler.errors['db'] != null}"><p class="warning">${commentHandler.errors['db']}</p></c:if>
			
			<form method="post" action="${pageContext.request.contextPath}/book/${book.id}">
				<table class="form commentform">
					<tr>
						<c:choose>
							<c:when test="${sessionScope.user != null}">
								<td colspan="2">Vous êtes connecté en tant que <b>${sessionScope.user.name}</b>.</td>
							</c:when>
							<c:otherwise>
								<th><label for="commentName">Votre nom :</label></th>
								<td><input type="text" id="commentName" name="commentName" value="${comment.authorName}"><br />
								<c:if test="${!empty commentHandler && commentHandler.errors['name'] != null}"><span class="warning">${commentHandler.errors['name']}</span></c:if></td>
							</c:otherwise>
						</c:choose>
					</tr>	
					<tr>
						<th><label for="commentContent">Votre commentaire :</label></th>
						<td><textarea id="commentContent" name="commentContent" class="longtext">${comment.content}</textarea><br />
								<c:if test="${!empty commentHandler && commentHandler.errors['content'] != null}"><span class="warning">${commentHandler.errors['content']}</span></c:if></td>
					</tr>
					<tr>
						<td colspan="2" class="submit">
							<input type="hidden" id="commentBook" name="commentBook" value="${!empty book ? book.id : ''}" />
							<input type="submit" id="postSubmit" name="postSumibt" value="Envoyer" />
						</td>
					</tr>
				</table>
			</form>
		</section>
    	<%@include file="includes/footer.jsp" %>
    </body>
</html>