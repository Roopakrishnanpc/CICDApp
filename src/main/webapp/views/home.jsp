<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.CICD.entity.Users" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Management</title>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        form {
            margin-bottom: 20px;
        }
        .message {
            color: green;
            font-weight: bold;
        }
        .error {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>User Management System</h1>

    <!-- Message Display -->
    <%
        String message = (String) request.getAttribute("message");
        if (message != null && !message.isEmpty()) {
    %>
        <p class="message"><%= message %></p>
    <%
        }
    %>

    <!-- Add User Form -->
<%--     <form:form action="${pageContext.request.contextPath}/home/addUsers" method="post" modelAttribute="user"> --%>
    <form:form action="${pageContext.request.contextPath}/addUsers" method="post" modelAttribute="user">
        <label for="name">Name:</label>
        <form:input path="name" id="name" required="true" />
        <label for="jobName">Job Name:</label>
        <form:input path="jobName" id="jobName" required="true" />
        <label for="email">Email:</label>
        <form:input path="email" id="email" type="email" required="true" />
        <label for="phonenNo">Phone Number:</label>
        <form:input path="phonenNo" id="phonenNo" type="number" required="true" />
        <button type="submit">Add User</button>
    </form:form>

    <!-- Update User Form -->
 <!-- Update User Form -->
    <h2>Update User</h2>
<%--     <form action="${pageContext.request.contextPath}/home/getUsers" method="get"> --%>
  <form action="${pageContext.request.contextPath}/getUsers" method="get"> --%>
        <label for="updateId">User ID:</label>
        <input type="number" id="updateId" name="id" required="true" />
        <button type="submit">Get User Details</button>
    </form>
<%-- <c:if test="${not empty userForUpdate}"> --%>
<%--     <form:form action="${pageContext.request.contextPath}/home/updateUsers" method="post" modelAttribute="userForUpdate"> --%>
<%--         <form:hidden path="id" /> --%>
<!--         <label for="updateName">Name:</label> -->
<%--         <form:input path="name" id="updateName" required="true" /> --%>
<!--         <label for="updateJobName">Job Name:</label> -->
<%--         <form:input path="jobName" id="updateJobName" required="true" /> --%>
<!--         <label for="updateEmail">Email:</label> -->
<%--         <form:input path="email" id="updateEmail" type="email" required="true" /> --%>
<!--         <label for="updatePhonenNo">Phone Number:</label> -->
<%--         <form:input path="phonenNo" id="updatePhonenNo" type="number" required="true" /> --%>
<!--         <button type="submit">Update User</button> -->
<%--     </form:form> --%>
<%-- </c:if> --%>


<p>All Users: ${users}</p>
 
<%-- <form action="${pageContext.request.contextPath}/home/getUsers/${updateId}" method="get"> --%>
<!--     <label for="updateId">User ID:</label> -->
<!--     <input type="number" id="updateId" name="updateId" required="true" /> -->
<!--     <button type="submit">Get User Details</button> -->
<%-- </form> --%>


<!--     Update Form Handling -->
<%--     <% --%>
<%-- 
//         Users userForUpdate = (Users) request.getAttribute("userForUpdate");
//         if (userForUpdate != null) {--%>
<%--     %> --%>
<%--         <form:form action="${pageContext.request.contextPath}/home/updateUsers" method="post" modelAttribute="userForUpdate"> --%>
<%--             <form:hidden path="id" /> --%>
<!--             <label for="updateName">Name:</label> -->
<%--             <form:input path="name" id="updateName" required="true" /> --%>
<!--             <label for="updateJobName">Job Name:</label> -->
<%--             <form:input path="jobName" id="updateJobName" required="true" /> --%>
<!--             <label for="updateEmail">Email:</label> -->
<%--             <form:input path="email" id="updateEmail" type="email" required="true" /> --%>
<!--             <label for="updatePhonenNo">Phone Number:</label> -->
<%--             <form:input path="phonenNo" id="updatePhonenNo" type="number" required="true" /> --%>
<!--             <button type="submit">Update User</button> -->
<%--         </form:form> --%>
<%--     <% --%>
<%--     } --%>
       
<%--     %> --%>

    <!-- Delete User Form -->
    <h2>Delete User</h2>
    <form action="${pageContext.request.contextPath}/deleteUsers" method="post">
<%--     <form action="${pageContext.request.contextPath}/home/deleteUsers" method="post"> --%>
        <label for="deleteId">User ID:</label>
        <input type="number" id="deleteId" name="id" required="true" />
        <button type="submit">Delete User</button>
    </form>

    <!-- Delete All Users Form -->
    <h2>Delete All Users</h2>
    <form action="${pageContext.request.contextPath}/deleteAllUsers" method="post">
<%--     <form action="${pageContext.request.contextPath}/home/deleteAllUsers" method="post"> --%>
        <button type="submit">Delete All Users</button>
    </form>

    <!-- List All Users -->
    <h2>All Users</h2>
    <%
        List<Users> users = (List<Users>) request.getAttribute("users");
        if (users != null && !users.isEmpty()) {
    %>
        <ul>
            <%
                for (Users user : users) {
            %>
                <li>ID: <%= user.getId() %>, Name: <%= user.getName() %>, Job: <%= user.getJobName() %>, Email: <%= user.getEmail() %>, Phone: <%= user.getPhonenNo() %></li>
            <%
                }
            %>
        </ul>
    <%
        } else {
    %>
        <p>No users found.</p>
    <%
        }
    %>
    
        <c:choose>
        <c:when test="${not empty users}">
            <ul>
                <c:forEach var="user" items="${users}">
                    <li>ID: ${user.id}, Name: ${user.name}, Job: ${user.jobName}, Email: ${user.email}, Phone: ${user.phonenNo}</li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <p>No users found.</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
