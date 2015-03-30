<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Ju'Lire</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    </head>

    <body>
    	<%@include file="includes/header.jsp" %>
    	<h1>Les dernières news</h1>
    	    	
    	<div class="news">

   			<c:if test="${!empty newsHandler && newsHandler.fail != null}"><p class="warning">${newsHandler.fail}</p></c:if>
			<c:if test="${!empty newsHandler && newsHandler.errors['db'] != null}"><p class="warning">${newsHandler.errors['db']}</p></c:if>
    	
    	<c:forEach items="${news}" var="element" varStatus="status">
    		<c:choose>
    			<c:when test="${status.first}">
    				<a href="${pageContext.request.contextPath}/news/${element.id}"><div class="first">
    					<p class="title">${element.title}</p>
    					${element.content}
    					<p class="metadata">Posté le ${element.createdAt} par ${element.authorName}</p>
    				</div></a>
    			</c:when>
    			<c:otherwise>
    				<c:if test="${status.count == 2}">
    					<div class="second">
    						<h3>Les news précédentes</h3>
    						
    						<p>
    				</c:if>
    						&raquo; <a href="${pageContext.request.contextPath}/news/${element.id}">${element.title}</a> par ${element.authorName} le ${element.createdAt}<br />
    				<c:if test="${status.last}">
    						</p>
    					</div>
    				</c:if>
    			</c:otherwise>
    		</c:choose>
    	</c:forEach>
    	</div>
    	
    	<div class="newsletter">
    		<fieldset>
    			<legend>S'inscrire à la newsletter ?</legend>
    			<div style="text-align: justify;">Vous souhaitez recevoir les dernières nouveautés par email ? Inscrivez-vous !</div>
    			
				<c:if test="${!empty nHandler && nHandler.success != null}"><p class="success">${nHandler.success}</p></c:if>
				<c:if test="${!empty nHandler && nHandler.fail != null}"><p class="warning">${nHandler.fail}</p></c:if>
				<c:if test="${!empty nHandler && nHandler.errors['db'] != null}"><p class="warning">${nHandler.errors['db']}</p></c:if>
    			
    			<form method="post" action="${pageContext.request.contextPath}/home">
    			<label for="newsletterEmail">Votre adresse email :</label><br />
    				<input type="text" id="newsletterEmail" name="newsletterEmail" value="${!empty suscriber ? suscriber.email : ''}" /><br />
    				<c:if test="${!empty nHandler && nHandler.errors['email'] != null}"><span class="warning">${nHandler.errors['email'] != null}</span><br /></c:if>
    				<input type="radio" id="newsletterChoice1" name="newsletterChoice" value="suscribe" checked /> <label for="newsletterChoice1">S'inscrire</label><br />
    				<input type="radio" id="newsletterChoice2" name="newsletterChoice" value="unsuscribe" /> <label for="newsletterChoice2">Se désinscrire</label><br />
    				<c:if test="${!empty nHandler && nHandler.errors['choice'] != null}"><span class="warning">${nHandler.errors['choice'] != null}</span><br /></c:if>
    				<input type="submit" id="newsletterSubmit" name="newsletterSubmit" value="OK" />
    			</form>
    		</fieldset>
    	</div>
    	
    	<br class="clear" />
    	</section>

		<section class="content">
		<h1>Les derniers ajouts</h1>
		
		<c:if test="${!empty bookHandler && bookHandler.fail != null}"><p class="warning">${bookHandler.fail}</p></c:if>
		<c:if test="${!empty bookHandler && bookHandler.errors['db'] != null}"><p class="warning">${bookHandler.errors['db']}</p></c:if>
		
		<c:forEach items="${books}" var="element">
		<a href="${pageContext.request.contextPath}/book/${element.id}"><div class="book">
			<div class="titleContainer">
				<p class="title">${element.title}</p>
				<p class="serie">${element.serie}</p>
			</div>
			
			<div class="imgContainer"><img src="${pageContext.request.contextPath}/img/uploads/${element.file}" alt="" class="imgIndex" /></div>
			
			<p><b>Auteur :</b> ${element.authorName}<br />
			<b>&Eacute;diteur :</b> ${element.editor}<br />
			<b>Date de sortie :</b> ${element.date}<br />
			<b>Prix :</b> ${element.price} &euro;</p>
			
			<p class="link"><a href="${pageContext.request.contextPath}/book/${element.id}">Plus d'informations &raquo;</a></p>
		</div></a>
		</c:forEach>
    	
    	    	
    	<%@include file="includes/footer.jsp" %>
    </body>
</html>