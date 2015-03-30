<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Ju'Lire &raquo; Page inexistante</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    </head>

    <body>
    	<%@include file="includes/header.jsp" %>
    	<h1>Page inexistante</h1>
    	
    	<p>La page que vous cherchiez n'existe pas.</p>
    	    	
    	<%@include file="includes/footer.jsp" %>
    </body>
</html>