<%--
  Created by IntelliJ IDEA.
  User: dotie
  Date: 2/15/2025
  Time: 9:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/common/admin/menu.jsp"%>
</head>
<body>
<%@include file="/common/admin/menu.jsp"%>
<h2>Edit Your Information</h2>

<form id="editForm">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="John Doe" required>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="johndoe@example.com" required>

    <label for="message">Message:</label>
    <textarea id="message" name="message" rows="4">Hello, this is a sample message.</textarea>

    <button type="submit">Save Changes</button>
</form>
<%@include file="/common/admin/footer.jsp"%>
<script>
    document.getElementById("editForm").addEventListener("submit", function (event) {
        event.preventDefault();
        alert("Form submitted successfully!");
    });
</script>
</body>
</html>
