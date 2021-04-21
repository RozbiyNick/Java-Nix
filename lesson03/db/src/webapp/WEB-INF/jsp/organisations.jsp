<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Debates Organisations</title>
</head>
<body>
<h1>Список организаций</h1>
<div>
	<ul>
		<c:if test="${orgs.size() == 0}">
			<p>Никому не интересны виртуальные дебаты :(</p>
		</c:if>
		<c:forEach var="org" items="${orgs}">
			<li><a href="${org.id}">${org.name}</a></li>
		</c:forEach>
	</ul>
</div>
</body>
</html>