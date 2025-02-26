<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="APIurl" value="/api-admin-new"/>
<!DOCTYPE html>
<html lang="vi">
<head>
    <%@include file="/common/admin/header.jsp" %>
    <script>
        var APIurl = "<c:url value='/api-admin-new'/>";
    </script>
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
                        <option value="${item.code}" ${item.code == model.categoryCode ? 'selected="selected"' : ''}>
                                ${item.name}
                        </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty model.id}">
                    <input type="hidden" id="id" name="id" value="${model.id}">
                </c:if>
                <input type="text" id="title" name="title" value="${model != null ? model.title : ''}">
                <input type="text" id="thumbNail" name="thumbNail" value="${model != null ? model.thumbNail : ''}">
                <input type="text" id="shortDescription" name="shortDescription" value="${model != null ? model.shortDescription : ''}">
                <input type="text" id="content" name="content" value="${model != null ? model.content : ''}">
                <button id="btnUpdateOrAdd" type="submit" class="btn btn-save">
                    ${not empty model.id ? 'Cập nhật' : 'Thêm mới'}
                </button>
            </form>
        </div>
    </div>
</div>

<%@include file="/common/admin/footer.jsp" %>
<script>
    $(document).ready(function () {
        $('#editForm').submit(function (event) {
            event.preventDefault();
            var data = {};
            var formData = $(this).serializeArray();
            $.each(formData, function (idx, vl) {
                if (vl.name !== "_method") {  // ⚠️ Loại bỏ trường `_method`
                    data[vl.name] = vl.value;
                }
            });

            var id = $('#id').val();
            if (id) {
                updatePost(data);
            } else {
                addPost(data);
            }
        });

        function addPost(data) {
            $.ajax({
                url: APIurl,
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(data),
                dataType: 'json',
                success: function (result) {
                    alert("✅ Thêm mới thành công!");
                    window.location.reload();
                },
                error: function (xhr) {
                    alert("❌ Lỗi: " + xhr.responseText);

                }
            });
        }

        function updatePost(data) {
            $.ajax({
                url: APIurl,
                type: 'PUT', // Nếu backend không hỗ trợ PUT, dùng POST và thêm _method=PUT
                contentType: 'application/json',
                data: JSON.stringify(data),
                dataType: 'json',
                success: function (result) {
                    alert("✅ Cập nhật thành công!");
                    window.location.reload();
                },
                error: function (xhr) {
                    alert("❌ Lỗi: " + xhr.responseText);
                    // console.log(" error " + xhr.responseText);
                }
            });
        }
    });
</script>


</body>
</html>
