<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <%@include file="/common/admin/header.jsp" %>
    <style>
        input, textarea, select {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 2px solid #ddd;
            border-radius: 6px;
            font-size: 16px;
            transition: all 0.3s;
        }

        input:focus, textarea:focus, select:focus {
            border-color: #667eea;
            outline: none;
            box-shadow: 0 0 8px rgba(102, 126, 234, 0.5);
        }

        textarea {
            resize: none;
            height: 80px;
        }

        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: 0.3s;
        }

        .btn-edit {
            background: #667eea;
            color: white;
        }

        .btn-save {
            background: #48bb78;
            color: white;
        }

        .btn:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <%@include file="/common/admin/menu.jsp" %>
        <div class="col-md-10 main-content">
            <form id="editForm">
                <h2>✏️ Edit Post</h2>
                <select id="categoryCode" name="categoryCode">
                    <option value="">Select Category</option>
                    <c:forEach var="item" items="${categories}">
                        <option value="${item.code}"
                                <c:if test="${item.code == model.categoryCode}">selected</c:if>>${item.name}</option>
                    </c:forEach>
                </select>
                <input type="hidden" name="id" value="${model.id}">
                <input type="text" id="title" name="title" value="${model != null ? model.title : ''}">

                <input type="text" id="shortDescription" name="shortDescription" value="${model.shortDescription}">
                <input type="text" id="content" name="content" value="${model.content}">
                <input type="text" id="thumbNail" name="thumbNail" value="${model.thumbNail}">

                <button type="submit" class="btn btn-save">Save</button>
            </form>
        </div>
    </div>
</div>

<script>
    document.getElementById("editForm").addEventListener("submit", function (event) {
        event.preventDefault(); // Prevent the default form submission

        // Gather the form data
        var formData = new FormData(this);

        // Validate form inputs (Example: check if any required field is empty)
        for (var [key, value] of formData.entries()) {
            if (!value) {
                alert('Please fill in all fields');
                return; // Stop submission if any field is empty
            }
        }

        // Send data via AJAX
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "<c:url value='/admin-new'/>", true); // Replace with correct URL for edit action

        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        // Add type=EDIT to specify the type of request for the backend
        formData.append("type", "EDIT");
        formData.append("id", document.getElementById("id").value);

        // Handle the response from the server
        xhr.onload = function () {
            if (xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                if (response.status === "success") {
                    alert("✅ Post updated successfully!");
                    window.location.href = "<c:url value='/admin-new'/>"; // Redirect to the list or another page
                } else {
                    alert("❌ Error updating post: " + response.message);
                }
            } else {
                alert("❌ Something went wrong. Please try again later.");
            }
        };

        // Send the data to the server
        xhr.send(new URLSearchParams(formData).toString());
    });
</script>

<%@include file="/common/admin/footer.jsp" %>
</body>
</html>
