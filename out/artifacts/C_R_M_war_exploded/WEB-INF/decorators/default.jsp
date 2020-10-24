
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="dec" %>

<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="/assets/plugins/images/favicon.png">

    <title> <dec:title/> </title>

    <jsp:include page="/WEB-INF/views/layout/header.jsp" />

    <dec:head/>

</head>

<body>
<!-- Preloader -->
<div class="preloader">
    <div class="cssload-speeding-wheel"></div>
</div>
<div id="wrapper">
    <!-- Navigation -->
    <jsp:include page="/WEB-INF/views/layout/navbar.jsp"></jsp:include>
    <!-- Left navbar-header -->
    <jsp:include page="/WEB-INF/views/layout/sidebar.jsp"></jsp:include>
    <!-- Left navbar-header end -->

    <!-- Page Content -->
    <div id="page-wrapper">
        <dec:body/>
    </div>
    <!-- /.container-fluid -->
    <!-- /#page-wrapper -->

    <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>

    <dec:getProperty property="page.scripts"/>
</div>
<!-- /#wrapper -->


</body>

</html>
