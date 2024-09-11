<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update User</title>
</head>
<body>
    <h1>Update User</h1>
	<form:form action="${pageContext.request.contextPath}/updateUser" method="post" modelAttribute="userForUpdate">
<%--     <form:form action="${pageContext.request.contextPath}/home/updateUser" method="post" modelAttribute="userForUpdate"> --%>
        <form:hidden path="id"/>
        <label for="name">Name:</label>
        <form:input path="name" id="name" required="true"/>
        <label for="jobName">Job Name:</label>
        <form:input path="jobName" id="jobName" required="true"/>
        <label for="email">Email:</label>
        <form:input path="email" id="email" type="email" required="true"/>
        <label for="phonenNo">Phone Number:</label>
        <form:input path="phonenNo" id="phonenNo" type="number" required="true"/>
        <button type="submit">Update User</button>
    </form:form>
	<a href="${pageContext.request.contextPath}/">Back to Home</a>
<%--     <a href="${pageContext.request.contextPath}/home">Back to Home</a> --%>
</body>
</html>
