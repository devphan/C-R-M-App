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
    <title>Thành viên</title>
</head>

<div class="container-fluid">
    <div class="row bg-title">
        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
            <h4 class="page-title">Danh sách thành viên</h4>
        </div>
        <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
            <a href="#" data-toggle="modal" data-target="#addUserModal" class="btn btn-sm btn-success"
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
                            <th>Họ và tên</th>
                            <th>Email</th>
                            <th>Quyền</th>
                            <th>Hành động</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${userDTOs}" var="users">
                            <tr>
                                <td>${users.id}</td>
                                <td>${users.fullname}</td>
                                <td>${users.email}</td>
                                <td>${users.roleName}</td>
                                <td>

                                        <%--  Model delete--%>
                                    <div id="deleteUserModal${users.id}" class="modal fade">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <form action="/user-delete?id=${users.id}" method="post">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title">Delete User</h4>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-hidden="true">&times;
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <p>Are you sure you want to delete these Records?</p>
                                                        <h3>${users.fullname}</h3>
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

                                    <div id="editUserModal${users.id}" class="modal fade">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <form action="/user-edit?id=${users.id}" method="POST">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title">Update User</h4>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-hidden="true">&times;
                                                        </button>
                                                    </div>
                                                    <input type="hidden" name="id" value="${users.id}"/>

                                                    <div class="modal-body">
                                                        <div class="form-group">
                                                            <label>Full Name</label>
                                                            <input type="text" value="${users.fullname}" name="fullname" class="form-control"
                                                                   required>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Email</label>
                                                            <input type="email" value="${users.email}" name="email" class="form-control"

                                                                   required>
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Password</label>
                                                            <input type="password" name="password" class="form-control"

                                                                   required>
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Avatar</label>
                                                            <input type="file"  name="avatar" class="form-control"

                                                                   required>
                                                        </div>

                                                        <div class="form-group">
                                                            <label class="col-sm-12">Select Role</label>
                                                            <div class="col-sm-12">
                                                                <select name="roleIdEdit"
                                                                        class="form-control form-control-line">

                                                                    <c:forEach items="${roleDTOs}" var="roles">

                                                                        <option value="${roles.id}">${roles.name}</option>

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


                                    <a href="#" data-toggle="modal" data-target="#editUserModal${users.id}"
                                       class="btn btn-sm btn-primary">Sửa</a>
                                    <a href="#" data-toggle="modal" data-target="#deleteUserModal${users.id}"
                                       class="btn btn-sm btn-danger">Xóa</a>
                                    <a href="/user-details?id=${users.id}" class="btn btn-sm btn-info">Xem</a>
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
<div id="addUserModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="/user-add" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Add User</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Full Name</label>
                        <input type="text" name="fullname" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" name="email" class="form-control" required>
                    </div>

                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" name="password" class="form-control" required>
                    </div>

                    <div class="form-group">
                        <label>Avatar</label>
                        <input type="file" name="avatar" class="form-control" required>
<%--                        <form action = "UploadServlet" method = "post" enctype = "multipart/form-data">--%>
<%--                            <input type = "file" name = "file" size = "50" />--%>
<%--                            <br />--%>
<%--                            <input type = "submit" value = "Upload File" />--%>
<%--                        </form>--%>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-12">Select Role</label>
                        <div class="col-sm-12">
                            <select name="roleId" class="form-control form-control-line">

                                <c:forEach items="${roleDTOs}" var="roles">

                                    <option value="${roles.id}">${roles.name}</option>

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


