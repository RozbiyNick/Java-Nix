<!DOCTYPE html>
<html>
	<body>
		<h2>Hello World!</h2>
		<p>Header</p>
		<p>Host: ${header.host}</p>
		<ul>
			<c:forEach var="cook" items="${cookie}">
			    <li>
			        <p><c:out value="${cook.value.name}" /></p>
			        <p><c:out value="${cook.value.value}" /></p>
			    </li>
			</c:forEach>
		</ul>
	</body>
</html>