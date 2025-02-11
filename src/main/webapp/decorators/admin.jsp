<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><dec:title default="Trang Chu"/></title>

    <link href="<c:url value='/template/css/bootstrap.min.css'/>"
          rel="stylesheet" type="text/css" meta="all"/>
    <link href="<c:url value='/template/font/css/all.min.css'/>"
          rel="stylesheet" type="text/css" meta="all"/>
    <link href="<c:url value='/template/css/styles.css'/>" rel="stylesheet"
          type="text/css" meta="all"/>
    <link href="<c:url value='/template/css/stylesAdmin.css'/>" rel="stylesheet"
          type="text/css" meta="all"/>

</head>
<body>
<%@include file="/common/admin/header.jsp" %>
<!-- header -->
<%@include file="/common/admin/menu.jsp" %>

<div class="container">
    <dec:body/>
</div>


<!-- footer -->
<%@include file="/common/admin/footer.jsp" %>

<script src="<c:url value='/template/js/scripts.js'/>"
        type="text/javascript"></script>
<%--<script src="<c:url value='/template/js/jquery.min.js'/>"--%>
<%--        type="text/javascript"></script>--%>
<script src="<c:url value='/template/js/bootstrap.min.js'/>"
        type="text/javascript"></script>
<script src="<c:url value='/template/js/popper.min.js'/>"
        type="text/javascript"></script>
<script src="<c:url value='/template/js/jquery-3.7.1.min.js'/>"
        type="text/javascript"></script>
<script src="<c:url value='/template/js/jquery.twbsPagination.js'/>"
        type="text/javascript"></script>

</body>

</html>
