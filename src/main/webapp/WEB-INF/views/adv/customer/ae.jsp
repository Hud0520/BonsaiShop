
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../template/header.jsp" flush="true"/>
<c:set var="name" value=""/>
<c:set var="email" value=""/>
<c:set var="tel" value=""/>
<c:set var="add" value=""/>
<c:set var="role" value="Quản trị viên"/>
<c:set var="title" value="Thêm người dùng"/>
<c:if test="${acc != null}">
    <c:set var="name" value="${acc.fullName}"/>
    <c:set var="email" value="${acc.email}"/>
    <c:set var="tel" value="${acc.tel}"/>
    <c:set var="add" value="${acc.address}"/>
    <c:set var="role" value="${acc.admin ? 'Quản trị viên' : 'Khách hàng' }"/>
    <c:set var="title" value="Sửa thông tin người dùng"/>
</c:if>
<c:if test="${err}">
    <script>
        window.alert("Bạn chưa dữ liệu bạn nhập không hợp lệ");
        location.href="/adv/customer";
    </script>
</c:if>
<div class="col-md-10 ">
    <div class="view text-dark bg-light rounded p-2">
        <div class="row">
            <div class="title">
                <nav class="navbar border px-2 mb-2">
                    <h2 class="fw-bold">${title}</h2>
                    <div>
                        <c:if test="${acc == null}">
                            <button class="btn btn-success" type="button" onclick="check('/adv/customer/ae/add?typ=1');void(0);">Lưu</button>
                            <button class="btn btn-success " type="button" onclick="heck('/adv/customer/ae/add?typ=2');void(0);">Lưu Và thêm</button>
                        </c:if>
                        <c:if test="${acc !=null}">
                            <button class="btn btn-success" type="button" onclick="document.info.action='/adv/customer/ae/edit';document.info.submit();void(0);">Lưu</button>
                        </c:if>
                    </div>
                </nav>
                <a href="/adv/customer" class="my-2"><button class="btn btn-secondary btn-sm"><i class="fas fa-arrow-left"></i> Trở về</button></a>
            </div>
        </div>
        <div class="row mt-2">
            <div class="content">
                <form class="" method="POST" name="info">
                    <div class="border rounded bg-white">
                        <h3 class="bg-info text-white p-1">Thông tin người dùng</h3>
                        <div class=" row p-3">
                            <label class="col-md-2 text-end col-form-label" for="txtName">Họ và tên</label>
                            <div class="col-md-4"><input type="text" id="txtName" class="form-control" name="txtName" value="${name}"/></div>
                            <div class="col-md-4 "><div id="errName"></div></div>
                            <input type="hidden" class="form-control" name="txtId" value="${acc.id}"/>
                        </div>
                        <div class=" row p-3">
                            <label class="col-md-2 text-end col-form-label" for="txtEmail">Email</label>
                            <div class="col-md-4"><input type="email" id="txtEmail" name="txtEmail" class="form-control" value="${email}"/></div>
                            <div class="col-md-4"><div id="errEmail"></div></div>
                        </div>
                        <div class=" row p-3">
                            <label class="col-md-2 text-end col-form-label" for="txtPass">Password</label>
                            <div class="col-md-4"><input type="password" id="txtPass" name="txtPass" class="form-control" onkeyup="checkPass()"/>
                                <div id="errPass"></div>
                            </div>
                            <label class="col-md-2 text-end col-form-label" for="txtPass2">Confim password</label>
                            <div class="col-md-4"><input type="password" id="txtPass2" name="txtPass2" class="form-control" onkeyup="checkPass()"/>
                                <div id="errPass2"></div>
                            </div>
                        </div>



                        <div class=" row p-3">
                            <label class="col-md-2 text-end col-form-label" for="txtRol">Quyền quản trị</label>
                            <div class="col-md-4"><input type="text" id="txtRol" name="txtRol"class="form-control" disabled value="${role}"/></div>
                        </div>

                        <div class=" row p-3">
                            <label class="col-md-2 text-end col-form-label" for="txtTel">Số điện thoại</label>
                            <div class="col-md-4"><input type="text" id="txtTel" name="txtTel" class="form-control" value="${tel}"/></div>
                        </div>

                        <div class=" row p-3">
                            <label class="col-md-2 text-end col-form-label" for="txtAdd">Địa chỉ</label>
                            <div class="col-md-4"><input type="text" id="txtAdd" name="txtAdd"class="form-control" value="${add}"/></div>
                        </div>

                        <div class=" row p-3">
                            <label class="col-md-2 text-end col-form-label" for="txtAvt">Avata</label>
                            <div class="col-md-4"><input type="file" id="txtAvt"  name="fAvt"class="form-control" /></div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
<jsp:include page="../template/footer.jsp" flush="true"/>
