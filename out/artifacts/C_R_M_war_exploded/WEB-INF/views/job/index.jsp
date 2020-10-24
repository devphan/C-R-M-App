<%--
  Created by IntelliJ IDEA.
  User: longp
  Date: 9/13/2020
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>Công việc</title>
</head>


<div class="container-fluid">
    <div class="row bg-title">
        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
            <h4 class="page-title">Danh sách công việc</h4>
        </div>
        <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
            <a href="#" data-toggle="modal" data-target="#addJobModal" class="btn btn-sm btn-success"
               data-toggle="modal">Thêm mới</a>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /row -->
    <div class="row">
        <div class="col-sm-12">
            <div class="white-box">
                <div class="table-responsive">
                    <table class="table" id="example">
                        <thead>
                        <tr>
                            <th>STT</th>
                            <th>Tên công việc</th>
                            <th>Ngày bắt đầu</th>
                            <th>Ngày kết thúc</th>
                            <th>Họ và tên</th>
                            <th>Name Task</th>
                            <th>Trạng thái</th>
                            <th>Hành động</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${jobDTOs}" var="jobs">
                            <tr>
                                <td>${jobs.id}</td>
                                <td>${jobs.nameJob}</td>
                                <td>${jobs.startDate}</td>
                                <td>${jobs.endDate}</td>
                                <td>${jobs.userFullName}</td>
                                <td>${jobs.taskName}</td>
                                <td>${jobs.statusName}</td>
                                <td>

                                        <%--  Model delete--%>
                                    <div id="deleteJobModal${jobs.id}" class="modal fade">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <form action="/job-delete?id=${jobs.id}" method="post">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title">Delete Job</h4>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-hidden="true">&times;
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <p>Are you sure you want to delete these Records?</p>
                                                        <h3>${jobs.nameJob}</h3>
                                                        <p class="text-warning"><small>This action cannot be
                                                            undone.</small></p>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <input type="button" class="btn btn-default"
                                                               data-dismiss="modal" value="Cancel">
                                                        <input type="submit" class="btn btn-danger" value="Delete">
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>

                                    <!--Modal Edit -->
                                    <div id="editJobModal${jobs.id}" class="modal fade">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <form action="/job-edit?id=${jobs.id}" method="POST">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title">Update Job</h4>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-hidden="true">&times;
                                                        </button>
                                                    </div>
                                                    <input type="hidden" name="id" value="${jobs.id}"/>

                                                    <div class="modal-body">
                                                        <div class="form-group">
                                                            <label>Name Job</label>
                                                            <input type="text" value="${jobs.nameJob}" name="nameJob"
                                                                   class="form-control"
                                                                   required>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Start Date</label>
                                                            <input type="Date" value="${jobs.startDate}"
                                                                   name="startDate" class="form-control"
                                                                   required>
                                                        </div>

                                                        <div class="form-group">
                                                            <label>End Date</label>
                                                            <input type="Date" value="${jobs.endDate}" name="endDate"
                                                                   class="form-control"

                                                                   required>
                                                        </div>

                                                        <div class="form-group">
                                                            <label class="col-sm-12">Select Status</label>
                                                            <div class="col-sm-12">
                                                                <select name="statusId"
                                                                        class="form-control form-control-line">

                                                                    <c:forEach items="${statusDTOs}" var="status">

                                                                        <option value="${status.id}">${status.name}</option>

                                                                    </c:forEach>

                                                                </select>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label class="col-sm-12">Select User</label>
                                                            <div class="col-sm-12">
                                                                <select name="userId"
                                                                        class="form-control form-control-line">

                                                                    <c:forEach items="${userDTOs}" var="user">

                                                                        <option value="${user.id}">${user.fullname}</option>

                                                                    </c:forEach>

                                                                </select>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label class="col-sm-12">Select Task</label>
                                                            <div class="col-sm-12">
                                                                <select name="taskId"
                                                                        class="form-control form-control-line">

                                                                    <c:forEach items="${taskDTOs}" var="task">

                                                                        <option value="${task.id}">${task.name}</option>

                                                                    </c:forEach>

                                                                </select>
                                                            </div>
                                                        </div>


                                                    </div>

                                                    <div class="modal-footer">
                                                        <input type="button" class="btn btn-default"
                                                               data-dismiss="modal" value="Cancel">
                                                        <input type="submit" class="btn btn-success" value="Update">
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>


                                    <a href="#" data-toggle="modal" data-target="#editJobModal${jobs.id}"
                                       class="btn btn-sm btn-primary">Sửa</a>
                                    <a href="#" data-toggle="modal" data-target="#deleteJobModal${jobs.id}"
                                       class="btn btn-sm btn-danger">Xóa</a>
                                    <a href="/job-details" class="btn btn-sm btn-info">Xem</a>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- /.row -->

    <!-- /.container-fluid -->
</div>

<!--MODAL ADD -->
<div id="addJobModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="/job-add" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Add Job</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Name Job</label>
                        <input type="text" name="nameJob" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Start Date</label>
                        <input type="Date" name="startDate" class="form-control" required>
                    </div>

                    <div class="form-group">
                        <label>End Date</label>
                        <input type="Date" name="endDate" class="form-control" required>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-12">Select Status</label>
                        <div class="col-sm-12">
                            <select name="statusId" class="form-control form-control-line">

                                <c:forEach items="${statusDTOs}" var="status">

                                    <option value="${status.id}">${status.name}</option>

                                </c:forEach>

                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-12">Select User</label>
                        <div class="col-sm-12">
                            <select name="userId" class="form-control form-control-line">

                                <c:forEach items="${userDTOs}" var="user">

                                    <option value="${user.id}">${user.fullname}</option>

                                </c:forEach>

                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-12">Select Tast</label>
                        <div class="col-sm-12">
                            <select name="taskId" class="form-control form-control-line">

                                <c:forEach items="${taskDTOs}" var="task">

                                    <option value="${task.id}">${task.name}</option>

                                </c:forEach>

                            </select>
                        </div>
                    </div>


                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-success" value="Add">
                </div>
            </form>
        </div>
    </div>
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


