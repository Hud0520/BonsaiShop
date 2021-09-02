<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../template/header.jsp" flush="true"/>
<div class="col-md-10">
    <div class="view text-dark bg-light rounded p-2">
        <div class="row">
            <div class="title">
                <nav class="navbar border px-2">
                    <h2 class="fw-bold">Báo cáo</h2>
                    <div>
                        <a href="/adv/" class="my-2"><button class="btn btn-secondary "><i class="fas fa-arrow-left"></i> Trở về</button></a>&nbsp;
                    </div>
                </nav>
                <div class="col-md-8 offset-md-2 bg-white rounded p-2 my-3">
                    <form action="/adv/report" method="post" class="">
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
                            <div class="text-center">
                                <button type="submit" class="btn btn-success">Tạo báo cáo</button>
                            </div>
                        </div>
                    </form>
                </div>

            </div>
        </div>
        <div class="row">
            <div class="content">
                <table class="tblview col-md-12">
                    <tr>

                        <th>Tóm tắt</th>
                        <th>Số lượng đơn hàng</th>
                        <th>Lợi nhuận</th>
                        <th>Thuế</th>

                    </tr>
                    <c:if test="${reports.size()==0}">
                        <tr>
                            <th colspan="4"> Không có đơn hàng nào</th>
                        </tr>
                    </c:if>
                    <% int i=0;%>
                    <c:forEach items="${reports}" var="report">
                        <% if(++i%2==0){
                            out.print("<tr class=\"EVEN\">");
                        }else {
                            out.print("<tr>");
                        }%>
                        <td class="NAME">${report.rpDate}</td>
                        <td class="NAME">${report.sumOrder}</td>
                        <td class="NAME"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${report.sumTotalMoney}" /></td>
                        <td class="NAME"><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${report.sumTotalMoney*0.1}" /></td>

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


