<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
			<div class="row justify-content-center">
				<div class="col-3">
					<form:form modelAttribute="signupForm" method="post">
					  <div class="form-group">
					    <label for="exampleInputEmail1">Login</label>
					    <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Enter login" name="username" value="${requestScope.signupForm.getUsername()}">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputPassword1">Password</label>
					    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Enter password" name="password">
					  </div>
					  	<div class="form-group">
					    <label for="exampleInputPassword2">Password</label>
					    <input type="password" class="form-control" id="exampleInputPassword2" placeholder="Repeat password" name="password2">
					  </div>
					  <button type="submit" class="btn btn-primary">Submit</button>
					  
					</form:form>
						<ul class="error">
							<c:forEach var="error" items="${requestScope.errors}">
								<li>${error}</li>
							</c:forEach>
						</ul>
				</div>
			</div>
		</div>
	</body>
</html>