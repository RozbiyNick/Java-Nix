<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
	<head>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
		<link rel="preconnect" href="https://fonts.gstatic.com">
		<link href="https://fonts.googleapis.com/css2?family=Manrope:wght@400&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="resources/css/style.css">
	</head>
	<body>
		<jsp:include page="parts/header.jsp"></jsp:include>
		<div class="container-fluid">
			<div class="row">
				<jsp:include page="parts/left-nav.jsp"></jsp:include>
			</div>
		</div>
		
	</body>
</html>