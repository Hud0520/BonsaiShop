<%@ page import="com.t9.bsshop.model.Order" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
<fmt:formatDate var="month" value="${now}" pattern="MM" />
<c:if test="${param.st}">
    <c:set value=" tháng ${month}/${year}" var="title"></c:set>
</c:if>
<jsp:include page="../template/header.jsp" flush="true"/>

<div class="col-md-10">
    <div class="view text-dark bg-light rounded p-2">
        <div class="row">
            <div class="title">
                <nav class="navbar border px-2">
                    <h2 class="fw-bold">Đơn hàng${title}</h2>
                    <div>
                        <a href="/adv/" class="my-2"><button class="btn btn-secondary "><i class="fas fa-arrow-left"></i> Trở về</button></a>&nbsp;
                    </div>
                </nav>
                <c:if test="${!param.st}">
                <div class="col-md-8 offset-md-2 bg-white rounded p-2 my-3">
                    <form name="search" >
                        <div class="row my-3">
                            <label for="txtName" class="col-sm-3 col-form-label text-end fw-bold ">Ngày Lập
                                từ</label>
                            <div class="col-sm-8">
                                <input type="date" name="txtDateS" class="form-control" id="txtName" value="${search.get(0)}">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="txtName1" class="col-sm-3 col-form-label text-end fw-bold ">Đến ngày</label>
                            <div class="col-sm-8">
                                <input type="date" name="txtDateE" class="form-control" id="txtName1" value="${search.get(1)}">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="txtCatName" class="col-sm-3 col-form-label text-end fw-bold ">Trạng thái</label>
                            <div class="col-sm-8">
                                <select name="txtCatName" id="txtCatName" class="form-control form-select">
                                    <option value="${search.get(2)}">${search.get(2)}</option>
                                    <option value="<%=Order.Status.ACCEPTED%>" class="text-success"><%=Order.Status.ACCEPTED%></option>
                                    <option value="<%=Order.Status.DELIVERED%>" class="text-success"><%=Order.Status.DELIVERED%></option>
                                    <option value="<%=Order.Status.DELIVERING%>" class="text-success"><%=Order.Status.DELIVERING%></option>
                                    <option value="<%=Order.Status.PENDING%>" class="text-primary"><%=Order.Status.PENDING%></option>
                                    <option value="<%=Order.Status.CANCELED%>" class="text-warning"><%=Order.Status.CANCELED%></option>
                                    <option value="<%=Order.Status.DENIED%>" class="text-danger"><%=Order.Status.DENIED%></option>
                                </select>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="text-center">
                                <button class="btn btn-success" onclick="document.search.action='/adv/order';document.search.method='POST';document.search.submit();"><i class="fas fa-search"></i> Tìm kiếm</button>
                                <a href="/adv/order"></a> <button class="btn btn-success" ><i class="fas fa-sync-alt"></i> Làm mới</button></a>
                            </div>
                        </div>
                    </form>
                </div>
                </c:if>
            </div>
        </div>
        <div class="row">
            <div class="content">
                <table class="tblview col-md-12">
                    <tr>
                        <th><input type="checkbox"></th>
                        <th>Mã đơn hàng</th>
                        <th>Trạng thái</th>
                        <th>Tên khách hàng</th>
                        <th>Thông tin nhận hàng</th>
                        <th>Tổng tiền</th>
                        <th>Ngày tạo</th>
                        <th>Xem</th>
                    </tr>
                    <c:if test="${orders.size()==0}">
                        <tr>
                            <th colspan="8"> Không có đơn hàng nào</th>
                        </tr>
                    </c:if>
                    <% int i=0;%>
                    <c:forEach items="${orders}" var="order">
                        <% if(++i%2==0){
                            out.print("<tr class=\"EVEN\">");
                        }else {
                            out.print("<tr>");
                        }%>
                        <td class="NO"><input type="checkbox"></td>
                        <td class="NAME">HD${order.id}</td>
                        <td >${order.status}</td>
                        <td class="NAME">${order.account.fullName}</td>
                        <td class="ADDRESS">Tên người nhận: ${order.receiverName}<br>
                        Số điện thoại: ${order.receiverTel}<br>
                        Số điện thoại: ${order.receiverAddress}</td>
                        <td class="NAME">${order.sum}</td>
                        <td class="DATE">${order.orderDate}</td>
                        <td class="ED"><button type="button" onclick="location.href='/adv/order/view?id=${order.id}';" class="btn btn-link"><i class="far fa-eye fs-4"></i></button></td>
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


