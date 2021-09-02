<%@ page import="com.t9.bsshop.model.Order" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../template/header.jsp" flush="true"/>
<c:if test="${param.deler}">
    <script>
        window.alert("Xóa đơn hàng không thành công");
        location.href="/adv/order/view";
    </script>
</c:if>
<div class="col-md-10">
    <div class="view text-dark bg-light rounded p-2">
        <div class="row">
            <div class="title">
                <nav class="navbar border px-2">
                    <h2 class="fw-bold">Xem đơn hàng</h2>
                    <div>
                        <a href="/adv/order" class="my-2"><button class="btn btn-secondary "><i class="fas fa-arrow-left"></i> Trở về</button></a>
                        <button type="button" class="btn btn-danger" onclick="window.confirm('Bạn có chắc muốn xóa đơn hàng HD${order.id}')? location.href='/adv/order/view/del?id=${order.id}&conf=true': void(0);"><i class="far fa-trash-alt fs-4"></i></button>
                    </div>
                </nav>
            </div>
        </div>
        <div class="row">
            <div class="content">
                <div class="border rounded bg-white my-4 p-2">
                    <h5 class="bg-primary bg-gradient text-white p-2">Thông tin đơn hàng</h5>
                    <div class="row p-2">
                        <div class="col-md-4 fw-bold text-end">Mã đơn hàng:</div>
                        <div class="col-md-8">HD${order.id}</div>
                    </div>
                    <div class="row p-2">
                        <div class="col-md-4 fw-bold text-end">Ngày lập:</div>
                        <div class="col-md-8">${order.orderDate}</div>
                    </div>
                    <div class="row p-2">
                        <form class="row" action="/adv/order/view" method="POST">
                            <label class="col-md-4 col-form-label fw-bold text-end" for="txtEx">Trạng thái:</label>
                            <div class="col-md-3">
                                <input type="hidden" value="${order.id}" name ="id">
                            <select name="txtEx" id="txtEx" class="form-select">
                                <option value="${order.status}">${order.status}</option>
                                <hr class="dropdown-divider">
                                <option value="<%=Order.Status.ACCEPTED%>" class="text-success"><%=Order.Status.ACCEPTED%></option>
                                <option value="<%=Order.Status.DELIVERED%>" class="text-success"><%=Order.Status.DELIVERED%></option>
                                <option value="<%=Order.Status.DELIVERING%>" class="text-success"><%=Order.Status.DELIVERING%></option>
                                <option value="<%=Order.Status.PENDING%>" class="text-primary"><%=Order.Status.PENDING%></option>
                                <option value="<%=Order.Status.CANCELED%>" class="text-warning"><%=Order.Status.CANCELED%></option>
                                <option value="<%=Order.Status.DENIED%>" class="text-danger"><%=Order.Status.DENIED%></option>
                            </select>
                            </div>
                            <div class="col-md-3">
                                <button type="submit" class="btn btn-success -px-2">Xách nhận</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="border rounded bg-white my-4 p-2">
                    <h5 class="bg-primary bg-gradient text-white p-2">Thông tin khách hàng</h5>
                    <div class="row p-2">
                        <div class="col-md-4 fw-bold text-end">Họ tên:</div>
                        <div class="col-md-8">${order.account.fullName}</div>
                    </div>
                    <div class="row p-2">
                        <div class="col-md-4 fw-bold text-end">Email:</div>
                        <div class="col-md-8">${order.account.email}</div>
                    </div>
                    <div class="row p-2">
                        <div class="col-md-4 fw-bold text-end">Số điện thoại:</div>
                        <div class="col-md-8">${order.account.tel}</div>
                    </div>
                    <div class="row p-2">
                        <div class="col-md-4 fw-bold text-end">Địa chỉ nhận hàng:</div>
                        <div class="col-md-8">Tên người nhận : ${order.receiverName} <br>Số điện thoại : ${order.receiverTel}<br>Nơi nhận : ${order.receiverAddress}</div>
                    </div>
                </div>
                <div class="border rounded bg-white my-4">
                    <h5 class="bg-primary bg-gradient text-white mb-3 p-2">Danh sách sản phẩm</h5>
                    <table class="tblview col-md-12">
                        <tr>

                            <th>STT</th>

                            <th>Tên sản phẩm</th>
                            <th>Ảnh</th>
                            <th>Giá tiền</th>
                            <th>Số lượng</th>
                            <th>Thành tiền</th>
                        </tr>

                        <% int i=0;%>
                        <c:forEach items="${order.orderDetails}" var="sp">
                            <% if(++i%2==0){
                                out.print("<tr class=\"EVEN\">");
                            }else {
                                out.print("<tr>");
                            }%>
                            <td class="NO"><%=i%></td>
                            <td class="NAME">${sp.plant.name}</td>
                            <td class="NAME">${sp.plant.thumbnail}</td>
                            <td class="NAME">${sp.price}</td>
                            <td class="NAME">${sp.quantity}</td>
                            <td class="NAME">${sp.sum}</td>

                        </tr>
                        </c:forEach>
                        <tr>
                            <th colspan="5" class="text-end">Tổng thành tiền</td>
                            <td>${order.sum}</td>
                        </tr>
                    </table>
                </div>
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


