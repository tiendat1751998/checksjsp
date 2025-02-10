<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><dec:title default="Trang Chu" /></title>

<link href="<c:url value='/template/css/bootstrap.min.css'/>"
	rel="stylesheet" type="text/css" meta="all" />
<link href="<c:url value='/template/css/styles.css'/>" rel="stylesheet"
	type="text/css" meta="all" />
</head>
<body>
	<!-- header -->
	<%@include file="/common/web/header.jsp"%>

	<div class="container">
		<dec:body />
	</div>


	<!-- footer -->
	<%@include file="/common/web/footer.jsp"%>

	<link src="<c:url value='/template/js/jquery.min.js'/>"
		type="text/javascript" />
	<link src="<c:url value='/template/js/bootstrap.min.js'/>"
		type="text/javascript" />
</body>

</html>
