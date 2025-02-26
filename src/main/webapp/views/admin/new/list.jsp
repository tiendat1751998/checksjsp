<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <%@include file="/common/admin/header.jsp" %>
</head>
<body>
<!-- Toggle Sidebar Button -->
<button class="toggle-sidebar" id="toggleSidebar">
    <i class="fas fa-bars"></i>
</button>

<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <%@include file="/common/admin/menu.jsp" %>
        <!-- Main Content -->
        <div class="col-md-10 main-content" id="mainContent">
            <!-- Navbar -->
            <form action="<c:url value='/admin-new'/>" id="formSubmit" method="get">
                <nav class="navbar navbar-expand-lg navbar-custom">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#">Admin Dashboard</a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarNav"
                                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNav">
                            <ul class="navbar-nav ms-auto">
                                <li class="nav-item">
                                    <a class="nav-link" href="#"><i class="fas fa-bell"></i></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#"><i class="fas fa-user"></i> Admin</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
                <!-- Content -->
                <div class="row mt-4">
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-header">
                                <h5 class="card-title">Users</h5>
                            </div>
                            <div class="card-body">
                                <p class="card-text">Total Users: 1,234</p>
                                <a href="#" class="btn btn-primary">View Details</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-header">
                                <h5 class="card-title">Orders</h5>
                            </div>
                            <div class="card-body">
                                <p class="card-text">Total Orders: 567</p>
                                <a href="#" class="btn btn-primary">View Details</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-header">
                                <h5 class="card-title">Revenue</h5>
                            </div>
                            <div class="card-body">
                                <p class="card-text">Total Revenue: $12,345</p>
                                <a href="#" class="btn btn-primary">View Details</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Table -->
                <div class="row mt-4">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h5 class="card-title">Recent Orders</h5>
                            </div>
                            <div class="card-body">
                                <table class="table table-striped table-hover">
                                    <thead>
                                    <tr>
                                        <th>Tên Bài Viết</th>
                                        <th>mô tả ngắn</th>
                                        <th>Product</th>
                                        <th>Date</th>
                                        <th>CRUD</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="item" items="${model.listResult}">
                                        <tr>
                                            <td>${item.title}</td>
                                            <td>${item.shortDescription}</td>
                                            <td>${item.thumbNail}</td>
                                            <td>2023-10-01</td>
                                            <td>
                                                <a class="btn btn btn-success" href='<c:url value="/admin-new?type=EDIT"/>' role="button"
                                                   title="add"><i class="fas fa-add"></i></a>
                                                <c:url var="editURL" value="/admin-new">
                                                    <c:param name="type" value="EDIT"/>
                                                    <c:param name="id" value="${item.id}"/>
                                                </c:url>
                                                <a class="btn btn-warning" href="${editURL}" role="button" title="edit">
                                                    <i class="fas fa-edit"></i>
                                                </a>
                                                <c:url var="deleteURL" value="/admin-new">
                                                    <c:param name="type" value="delete"/>
                                                    <c:param name="id" value="${item.id}"/>
                                                </c:url>
                                                <a class="btn btn-danger" href="${deleteURL}" role="button"
                                                   title="remove"><i class="fas fa-remove"></i></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <nav aria-label="Page navigation" class="mt-4">
                                    <ul class="pagination justify-content-center" id="pagination"></ul>
                                </nav>
                                <input type="hidden" name="page" value="${model.page}"/>
                                <input type="hidden" name="maxPageItem" value="${model.maxPageItem}"/>
                                <input type="hidden" name="sortName" value="${model.sortName}"/>
                                <input type="hidden" name="sortBy" value="${model.sortBy}"/>
                                <input type="hidden" name="type" value="" id="type"/>
                                <input type="hidden" name="id" value="" id="id"/>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<%@include file="/common/admin/footer.jsp" %>

<script>
    $(document).ready(function () {
        var totalPages = ${model.totalPage};
        var startPage = ${model.page};
        var maxPageItem = ${model.maxPageItem};
        <%--var id = ${model.id};--%>
        var limit = 5;

        $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: maxPageItem,
            startPage: startPage,
            first: '«',
            prev: '‹',
            next: '›',
            last: '»',
            onPageClick: function (event, page) {
                if (startPage !== page) {
                    // Cập nhật giá trị vào form trước khi submit
                    $('#formSubmit').find('input[name="page"]').val(page);
                    $('#formSubmit').find('input[name="maxPageItem"]').val(maxPageItem);
                    $('#formSubmit').find('input[name="sortName"]').val("title");
                    $('#formSubmit').find('input[name="sortBy"]').val("desc");
                    $('#formSubmit').find('input[name="type"]').val("LIST");
                    // $('#formSubmit').find('input[name="id"]').val(id);
                    $('#formSubmit').submit();
                }
            }
        });
    });

    // Toggle Sidebar on Mobile
    const toggleSidebar = document.getElementById('toggleSidebar');
    const sidebar = document.getElementById('sidebar');
    const mainContent = document.getElementById('mainContent');

    toggleSidebar.addEventListener('click', () => {
        sidebar.classList.toggle('active');
        mainContent.classList.toggle('active');
    });


    // Smooth Scroll for Anchor Links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();
            document.querySelector(this.getAttribute('href')).scrollIntoView({
                behavior: 'smooth'
            });
        });
    });


</script>
</body>

</html>
