<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../template/header.jsp" flush="true"/>
<c:if test="${param.deler}">
    <script>
        window.alert("Xóa người dùng không thành công");
        location.href="/adv/customer";
    </script>
</c:if>
<div class="col-md-10">
    <div class="view text-dark bg-light rounded p-2">
        <div class="row">
            <div class="title">
                <nav class="navbar border px-2">
                    <h2 class="fw-bold">Khách hàng</h2>
                    <div>
                        <a href="/adv/" class="my-2"><button class="btn btn-secondary "><i class="fas fa-arrow-left"></i> Trở về</button></a>&nbsp;
                        <button class="btn btn-success" type="button" onclick="location.href='/adv/customer/ae'"><i class="fas fa-plus"></i> Thêm</button>
                        <button class="btn btn-danger" type="button"><i class="far fa-trash-alt"></i> Xóa</button>
                    </div>
                </nav>
                <form name="search" class="my-3" >
                    <div class="row">
                        <div class="col-md-8 offset-md-2 bg-white rounded p-2">
                            <form action="">
                                <div class="row mb-3">
                                    <label for="txtName" class="col-sm-3 col-form-label text-end fw-bold">Tên khách hàng</label>
                                    <div class="col-sm-3">
                                        <input type="text" name="txtName" class="form-control" id="txtName" value="${search[0]}">
                                    </div>
                                    <label for="txtrole" class="col-sm-2 col-form-label text-end fw-bold">Vai Trò</label>
                                    <div class="col-sm-3">
                                        <select type="text" name="txtrole" class="form-control form-select" id="txtrole">
                                            <option value=""></option>
                                            <option value="1" >Quản trị viên</option>
                                            <option value="2" >Khách hàng</option>
                                        </select>
                                    </div>

                                </div>

                                <div class="row mb-3">
                                    <label for="txtEmail" class="col-sm-3 col-form-label text-end fw-bold">Email</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="txtEmail" class="form-control"
                                               id="txtEmail" value="${search[2]}">
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <label for="txtAddress" class="col-sm-3 col-form-label text-end fw-bold">Địa chỉ</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="txtAddress" class="form-control"
                                               id="txtAddress" value="${search[3]}">
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="text-center">
                                        <button class="btn btn-success" onclick="document.search.action='/adv/customer';document.search.method='POST';document.search.submit();"><i class="fas fa-search"></i> Tìm kiếm</button>
                                        <a href="/adv/customer"></a> <button class="btn btn-success" ><i class="fas fa-sync-alt"></i> Làm mới</button></a>
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="content">
                <table class="tblview col-md-12">
                    <tr>
                        <th><input type="checkbox" id=""></th>
                        <th>Họ tên</th>
                        <th>Email</th>
                        <th>Số điện thoại</th>
                        <th>Địa chỉ</th>
                        <th>Vai trò</th>
                        <th colspan="2">Chỉnh Sửa</th>
                    </tr>

                    <% int i=0;%>
                    <c:forEach items="${accs}" var="acc">
                        <% if(++i%2==0){
                            out.print("<tr class=\"EVEN\">");
                        }else {
                            out.print("<tr>");
                        }%>
                            <td class="NO"><input type="checkbox" id="chk1"></td>
                            <td class="NAME"><label for="chk1">${acc.fullName}</label></td>
                            <td class="DATE">${acc.email}</td>
                            <td class="DATE">${acc.tel}</td>
                            <td class="ADDRESS">${acc.address}</td>
                            <td class="NAME"><c:choose><c:when test="${acc.admin}">Quản trị viên</c:when><c:otherwise>Khách hàng</c:otherwise></c:choose></td>
                            <td class="ED"><button type="button" onclick="location.href='/adv/customer/ae?id=${acc.id}';" class="btn btn-link"><i class="far fa-edit fs-4"></i></button></td>
                            <td class="ED"><button type="button" class="btn btn-link" onclick="window.confirm('Bạn có chắc muốn xóa người dùng ${acc.fullName}')? location.href='/adv/customer/ae/del?id=${acc.id}&conf=true' : location.href='/adv/customer/';"><i class="far fa-trash-alt fs-4"></i></button></td>
                        </tr>
                        </c:forEach>
                </table>
                <div class="row mt-3">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<jsp:include page="../template/footer.jsp" flush="true"/>


