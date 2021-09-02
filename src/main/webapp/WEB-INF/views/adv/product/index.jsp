<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
<fmt:formatDate var="month" value="${now}" pattern="MM" />
<c:if test="${param.st}">
    <c:set value="${title} ${month}/${year}" var="title"></c:set>
</c:if>
<c:if test="${param.deler}">
    <script>
        window.alert("Xóa sản phẩm không thành công");
        location.href="/adv/product";
    </script>
</c:if>
<jsp:include page="../template/header.jsp" flush="true"/>

<div class="col-md-10">
    <div class="view text-dark bg-light rounded p-2">
        <div class="row">
            <div class="title">
                <nav class="navbar border px-2">
                    <h2 class="fw-bold">${title}</h2>
                    <div>
                    <c:choose>
                        <c:when test="${param.st}">
                            <a href="/adv/" class="my-2"><button class="btn btn-secondary"><i class="fas fa-arrow-left"></i> Trở về</button></a>
                        </c:when>
                        <c:otherwise>
                            <a href="/adv/" class="my-2"><button class="btn btn-secondary "><i class="fas fa-arrow-left"></i> Trở về</button></a>&nbsp;
                            <button class="btn btn-success" type="button" onclick="javascript:location.href='/adv/product/ae'"><i class="fas fa-plus"></i> Thêm</button>
                            <button class="btn btn-danger" type="button"><i class="far fa-trash-alt"></i> Xóa</button>

                        </c:otherwise>
                    </c:choose>
                    </div>
                </nav>
                <c:if test="${!param.st}">
                <form name="search" class="my-3" >
                    <div class="row">
                        <div class="col-md-8 offset-md-2 bg-white rounded p-2">
                                <div class="row mb-3">
                                    <label for="txtName" class="col-sm-3 col-form-label text-end fw-bold">Tên sản
                                        phẩm</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="txtName" class="form-control" id="txtName" value="${search[0]}">
                                    </div>
                                </div>
                                <div class="row mb-3 ">
                                    <label for="txtCatName" class="col-sm-3 col-form-label text-end fw-bold">Tên
                                        chuyên mục</label>
                                    <div class="col-sm-8">
                                        <select name="txtCatName" id="txtCatName"
                                                class="form-control form-select">
                                            <option value=""></option>

                                            <c:forEach var="cat" items="${cats}">
                                                <c:choose>
                                                    <c:when test="${cat.id==search[1]}">
                                                        <option value="${cat.id}" selected>${cat.catName}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${cat.id}">${cat.catName}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <label for="txtPrice" class="col-sm-3 col-form-label text-end fw-bold">Số lượng còn</label>
                                    <div class="col-sm-8">
                                        <input type="number" name="txtPrice" class="form-control"
                                               id="txtPrice" value="${search[2]}">
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="text-center">
                                        <button class="btn btn-success" onclick="document.search.action='/adv/product';document.search.method='POST';document.search.submit();"><i class="fas fa-search"></i> Tìm kiếm</button>
                                        <a href="/adv/product"></a> <button class="btn btn-success" ><i class="fas fa-sync-alt"></i> Làm mới</button></a>
                                    </div>
                                </div>
                        </div>
                    </div>
                </form>
                </c:if>
            </div>
        </div>
        <div class="row">
            <div class="content">
                <table class="tblview col-md-12">
                    <tr>
                        <th><input type="checkbox" id=""></th>
                        <th>Tên Sản phẩm</th>
                        <th>Ảnh</th>
                        <th>Mô tả</th>
                        <th>Giá </th>
                        <th>Số lượng</th>
                        <th colspan="2">Chỉnh Sửa</th>
                    </tr>
                    <c:if test="${orders.size()==0}">
                        <tr>
                            <th colspan="8"> Không có đơn hàng nào</th>
                        </tr>
                    </c:if>
                    <% int i=0;%>
                    <c:forEach items="${plants}" var="plant">
                        <% if(++i%2==0){
                            out.print("<tr class=\"EVEN\">");
                        }else {
                            out.print("<tr>");
                        }%>
                        <td class="NO"><input type="checkbox" id="chk1"></td>

                        <td class="NAME"><label for="chk1">${plant.name}</label></td>
                        <td><img src="${pageContext.request.contextPath}/assets/img/${plant.thumbnail}"></td>
                        <td class="ADDRESS">${plant.description}</td>
                        <td>${plant.price}</td>
                        <td>${plant.quantity}</td>

                        <td class="ED"><button type="button" onclick="location.href='/adv/product/ae?id=${plant.id}';" class="btn btn-link"><i class="far fa-edit fs-4"></i></button></td>
                        <td class="ED"><button type="button" class="btn btn-link" onclick="window.confirm('Bạn có chắc muốn xóa sản phẩm ${plant.name}')? location.href='/adv/product/ae/del?id=${plant.id}&conf=true' : location.href='/adv/product/';"><i class="far fa-trash-alt fs-4"></i></button></td>
                    </tr>
                    </c:forEach>
                </table>
                <c:if test="${!param.st}">
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
                </c:if>
            </div>
        </div>
    </div>
</div>
</div>

<jsp:include page="../template/footer.jsp" flush="true"/>