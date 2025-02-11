<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="col-md-2 sidebar" id="sidebar">
    <h3>Admin Panel</h3>
    <ul class="nav flex-column">
        <li><a href="<c:url value='/admin-new?page=1&maxPageItem=5&sortName=id&sortBy=desc'/>"><i class="
    fas fa-home"></i> Dashboard</a></li>
        <li><a href="#"><i class="fas fa-users"></i> Users</a></li>
        <li><a href="#"><i class="fas fa-chart-line"></i> Analytics</a></li>
        <li><a href="#"><i class="fas fa-cog"></i> Settings</a></li>
        <li><a href="#"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
    </ul>
</div>
