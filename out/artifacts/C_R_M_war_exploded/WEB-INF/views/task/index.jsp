<%--
  Created by IntelliJ IDEA.
  User: longp
  Date: 9/13/2020
  Time: 5:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title>Dự án</title>
</head>

<div class="container-fluid">
    <div class="row bg-title">
        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
            <h4 class="page-title">Danh sách dự án</h4>
        </div>
        <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
            <a href="#" data-toggle="modal" data-target="#addTaskModal" class="btn btn-sm btn-success">Thêm mới</a>
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
                            <th>Dự án</th>
                            <th>Ngày bắt đầu</th>
                            <th>Ngày kết thúc</th>
                            <th>Hành động</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${taskDTOs}" var="tasks">

                            <tr>
                                <td>${tasks.id}</td>
                                <td>${tasks.name}</td>
                                <td>${tasks.startDate}</td>
                                <td>${tasks.endDate}</td>
                                <td>


                                        <%--  Model delete--%>
                                    <div id="deleteTaskModal${tasks.id}" class="modal fade">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <form action="/task-delete?id=${tasks.id}" method="post">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title">Delete Task</h4>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-hidden="true">&times;
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <p>Are you sure you want to delete these Records?</p>
                                                        <h3>${tasks.name}</h3>
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
                                    <div id="editTaskModal${tasks.id}" class="modal fade">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <form action="/task-edit?id=${tasks.id}" method="POST">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title">Update Task</h4>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-hidden="true">&times;
                                                        </button>
                                                    </div>
                                                    <input type="hidden" name="id" value="${tasks.id}"/>
                                                    <div class="modal-body">
                                                        <div class="form-group">
                                                            <label>Name Task</label>
                                                            <input type="text" value="${tasks.name}" name="name"
                                                                   class="form-control" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Start Date</label>
                                                            <input type="Date" value="${tasks.startDate}"
                                                                   name="startDate" class="form-control" required>

                                                        </div>
                                                        <div class="form-group">
                                                            <label>End Date</label>
                                                            <input type="Date" value="${tasks.endDate}"
                                                                   name="endDate" class="form-control" required>

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

                                    <a href="#" data-toggle="modal" data-target="#editTaskModal${tasks.id}"
                                       class="btn btn-sm btn-primary">Sửa</a>

                                    <a href="#" data-toggle="modal" data-target="#deleteTaskModal${tasks.id}"
                                       class="btn btn-sm btn-danger">Xóa</a>
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

<!--Modal add -->
    <div id="addTaskModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="/task-add" method="POST">
                <div class="modal-header">
                    <h4 class="modal-title">Add Task</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Name Task</label>
                        <input type="text" name="name" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Start Date</label>
                        <input type="Date" name="startDate" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>End Date</label>
                        <input type="Date" name="endDate" class="form-control" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button"  class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" id="addSuccess" class="btn btn-success" value="Add">
                </div>
            </form>
        </div>
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

