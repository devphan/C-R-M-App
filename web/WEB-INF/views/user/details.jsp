<%--
  Created by IntelliJ IDEA.
  User: longp
  Date: 10/24/2020
  Time: 12:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title>Thông tin thành viên</title>
</head>

<div class="container-fluid">
    <div class="row bg-title">
        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
            <h4 class="page-title">Chi tiết thành viên</h4>
        </div>
    </div>
    <!-- /.row -->
    <!-- .row -->
    <div class="row">
        <div class="col-md-4 col-xs-12">
            <div class="white-box">
                <div class="user"> <img width="100%" alt="user" src='/assets/plugins/images/avatar/HNB.png'>
                    <div class="overlay-box">
                        <div class="user-content">
                            <h4 class="text-black" style="text-align: center; font-size: 20px;">${user.fullname}</h4>
                            <h5 class="text-black" style="text-align: center; font-size: 20px;">${user.email}</h5>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div class="col-md-8 col-xs-12">
            <!-- BEGIN THỐNG KÊ -->
            <div class="row">
                <!--col -->
                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                    <div class="white-box">
                        <div class="col-in row">
                            <div class="col-xs-12">
                                <h3 class="counter text-right m-t-15 text-danger">20%</h3>
                            </div>
                            <div class="col-xs-12">
                                <i data-icon="E" class="linea-icon linea-basic"></i>
                                <h5 class="text-muted vb text-center">CHƯA BẮT ĐẦU</h5>
                            </div>
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="progress">
                                    <div class="progress-bar progress-bar-danger" role="progressbar"
                                         aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                         style="width: 20%"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.col -->
                <!--col -->
                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                    <div class="white-box">
                        <div class="col-in row">
                            <div class="col-xs-12">
                                <h3 class="counter text-right m-t-15 text-megna">50%</h3>
                            </div>
                            <div class="col-xs-12">
                                <i class="linea-icon linea-basic" data-icon="&#xe01b;"></i>
                                <h5 class="text-muted vb text-center">ĐANG THỰC HIỆN</h5>
                            </div>
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="progress">
                                    <div class="progress-bar progress-bar-megna" role="progressbar"
                                         aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                         style="width: 50%"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.col -->
                <!--col -->
                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                    <div class="white-box">
                        <div class="col-in row">
                            <div class="col-xs-12">
                                <h3 class="counter text-right m-t-15 text-primary">30%</h3>
                            </div>
                            <div class="col-xs-12">
                                <i class="linea-icon linea-basic" data-icon="&#xe00b;"></i>
                                <h5 class="text-muted vb text-center">HOÀN THÀNH</h5>
                            </div>
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="progress">
                                    <div class="progress-bar progress-bar-primary" role="progressbar"
                                         aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                         style="width: 30%"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.col -->
            </div>
            <!-- END THỐNG KÊ -->

        </div>
    </div><br />
    <!-- /.row -->
    <!-- BEGIN DANH SÁCH CÔNG VIỆC -->
    <h4>DANH SÁCH CÔNG VIỆC</h4>
    <div class="row">
        <div class="col-sm-12">
            <div class="white-box">
                <div class="table-responsive">
                    <table class="table" id="example">
                        <thead>
                        <tr>
                            <th>STT</th>
                            <th>Tên Công Việc</th>
                            <th>Dự Án</th>
                            <th>Ngày Bắt Đầu</th>
                            <th>Ngày Kết Thúc</th>
                            <th>Trạng Thái</th>
                            <th>Hành Động</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${profile}" var="profile">
                            <tr>
                                <td>${profile.id}</td>
                                <td>${profile.nameJob}</td>
                                <td>${profile.taskName}</td>
                                <td>${profile.startDate}</td>
                                <td>${profile.endDate}</td>
                                <td>${profile.statusName}</td>
                                <td>
                                    <a href="#" class="btn btn-sm btn-primary">Cập nhật</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <!-- END DANH SÁCH CÔNG VIỆC -->
</div>

<content tag="scripts">

    <!-- Custom Theme JavaScript -->
    <script src="/assets/js/custom.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#example').DataTable();
        });
    </script>


</content>


