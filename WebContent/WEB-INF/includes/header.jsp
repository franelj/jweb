<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav id="menu">
	<ul>
		<li><img src="${pageContext.request.contextPath}/img/logo.png" alt="Ju'Lire" /></li>
		<li><a href="${pageContext.request.contextPath}/home">Accueil</a></li>
		<li><a href="${pageContext.request.contextPath}/user/account">Mon compte</a></li>
		<c:choose>
			<c:when test="${!empty sessionScope && sessionScope.user != null && sessionScope.user.isAdmin}"><li><a href="${pageContext.request.contextPath}/admin/news/add">Administration</a></li></c:when>
			<c:otherwise></c:otherwise>
		</c:choose>   		
		<c:choose>
			<c:when test="${!empty sessionScope && sessionScope.user != null }"><li><a href="${pageContext.request.contextPath}/user/logout">Déconnexion</a></li></c:when>
			<c:otherwise></c:otherwise>
		</c:choose>   		
	</ul>
</nav>
<section id="content">
