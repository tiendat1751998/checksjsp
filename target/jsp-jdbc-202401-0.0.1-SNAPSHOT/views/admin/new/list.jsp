    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8" %>
    <%@include file="../../../common/taglib.jsp" %>
    <!DOCTYPE html>
    <html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Trang Admin</title>
        <!-- Bootstrap CSS -->
        <%--    <link href="<c:url value='css/bootstrap.min.css'/>" rel="stylesheet" type="text/css"/>--%>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <%--    <link href="<c:url value='css/all.min.css'/>" rel="stylesheet" type="text/css"/>--%>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <!-- Custom CSS -->

        <style>
            body {
                background-color: #f8f9fa;
                font-family: 'Arial', sans-serif;
            }

            .sidebar {
                height: 100vh;
                background-color: #343a40;
                color: #fff;
                padding: 20px;
                position: fixed;
                width: 250px;
                transition: transform 0.3s ease;
            }

            .sidebar a {
                color: #fff;
                text-decoration: none;
                display: block;
                padding: 10px;
                margin: 5px 0;
                border-radius: 5px;
                transition: background-color 0.3s, transform 0.2s;
            }

            .sidebar a:hover {
                background-color: #495057;
                transform: translateX(5px);
            }

            .main-content {
                margin-left: 250px;
                padding: 20px;
                transition: margin-left 0.3s ease;
            }

            .navbar-custom {
                background-color: #007bff;
                color: #fff;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }

            .navbar-custom .navbar-brand {
                color: #fff;
                font-weight: bold;
            }

            .navbar-custom .nav-link {
                color: #fff;
            }

            .card {
                margin-bottom: 20px;
                border: none;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                transition: transform 0.3s ease, box-shadow 0.3s ease;
            }

            .card:hover {
                transform: translateY(-5px);
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            }

            .card-header {
                background-color: #007bff;
                color: #fff;
                border-radius: 10px 10px 0 0;
                padding: 15px;
            }

            .card-body {
                padding: 20px;
            }

            .badge {
                padding: 8px 12px;
                font-size: 14px;
            }

            .table {
                margin-top: 20px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            .table th, .table td {
                vertical-align: middle;
            }

            .table-hover tbody tr:hover {
                background-color: rgba(0, 123, 255, 0.1);
            }

            .btn-primary {
                background-color: #007bff;
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }

            .btn-primary:hover {
                background-color: #0056b3;
            }

            .toggle-sidebar {
                display: none;
                background-color: #007bff;
                color: #fff;
                border: none;
                padding: 10px;
                border-radius: 5px;
                cursor: pointer;
                position: fixed;
                top: 10px;
                left: 10px;
                z-index: 1000;
            }

            @media (max-width: 768px) {
                .sidebar {
                    transform: translateX(-250px);
                }

                .main-content {
                    margin-left: 0;
                }

                .sidebar.active {
                    transform: translateX(0);
                }

                .toggle-sidebar {
                    display: block;
                }
            }
        </style>
    </head>
    <body>
    <!-- Toggle Sidebar Button -->
    <button class="toggle-sidebar" id="toggleSidebar">
        <i class="fas fa-bars"></i>
    </button>

    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar -->
            <div class="col-md-2 sidebar" id="sidebar">
                <h3>Admin Panel</h3>
                <ul class="nav flex-column">
                    <li><a href="<c:url value="/admin-new?page=1&maxPageItem=5&sortName=title&sortBy=desc"/>"><i class="
    fas fa-home"></i> Dashboard</a></li>
                    <li><a href="<c:url value="/login?action=login"/> "><i class="fas fa-users"></i>Login</a></li>
                    <li><a href="#"><i class="fas fa-chart-line"></i> Analytics</a></li>
                    <li><a href="#"><i class="fas fa-cog"></i> Settings</a></li>
                    <li><a href="#"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
                </ul>
            </div>
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
                                            <th>Status</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${model.listResult}">
                                            <tr>
                                                <td>${item.title}</td>
                                                <td>${item.shortDescription}</td>
                                                <td>${item.thumbNail}</td>
                                                <td>2023-10-01</td>
                                                <td><span class="badge bg-success">Completed</span></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <nav aria-label="Page navigation" class="mt-4">
                                        <ul class="pagination justify-content-center" id="pagination"></ul>
                                    </nav>
                                    <input type="hidden" name="page" value="${model.page}" />
                                    <input type="hidden" name="maxPageItem" value="${model.maxPageItem}" />
                                    <input type="hidden" name="sortName" value="${model.sortName}" />
                                    <input type="hidden" name="sortBy" value="${model.sortBy}" />
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.min.js"></script>
    <!-- Custom JS -->

    <script>
        $(document).ready(function () {
            var totalPages = ${model.totalPage};
            var startPage = ${model.page};
            var maxPageItem = ${model.maxPageItem};
            var limit=5;

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
