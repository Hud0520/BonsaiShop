<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../template/header.jsp" flush="true"/>
<c:if test="${param.deler}">
    <script>
        window.alert("Xóa chuyên mục không thành công");
        location.href="/adv/category";
    </script>
</c:if>
<div class="col-md-10">
    <div class="view text-dark bg-light rounded p-2">
        <div class="row">
            <div class="title">
                <nav class="navbar border px-2 ">
                    <h2 class="fw-bold">Chuyên mục</h2>
                    <div>
                        <a href="/adv/" class="my-2"><button class="btn btn-secondary "><i class="fas fa-arrow-left"></i> Trở về</button></a>&nbsp;
                        <button class="btn btn-success" type="button" onclick="javascript:location.href='/adv/category/ae'"><i class="fas fa-plus"></i> Thêm</button>
                        <button class="btn btn-danger" type="button"><i class="far fa-trash-alt"></i> Xóa</button>
                    </div>
                </nav>
                <form action="/adv/category" method="GET" class="my-3">
                    <div class="row">
                        <div class="col-md-6 offset-md-3 text-center bg-white rounded p-2">
                            <input type="text" class="form-control" placeholder="Tên chyên mục"
                                   aria-label="First name" name="keyword" value="${search}">
                            <button class="btn btn-success mt-2" type="submit"><i class="fas fa-search"></i> Tìm kiếm</button>&nbsp;
                            <button class="btn btn-success mt-2" type="button" onclick="location.href='/adv/category?keyword=';"><i class="fas fa-sync-alt"></i> Làm mới</button>
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
                        <th>Tên Chuyên mục</th>
                        <th>Mô tả</th>
                        <th colspan="2">Chỉnh Sửa</th>
                    </tr>
                    <% int i=0;%>
                    <c:forEach items="${cats}" var="cat">
                        <% if(++i%2==0){
                            out.print("<tr class=\"EVEN\">");
                        }else {
                            out.print("<tr>");
                        }%>
                        <td class="NO"><input type="checkbox" id="${cats.indexOf(cat)}}"></td>
                        <td><label for="${cats.indexOf(cat)}}">${cat.catName}</label></td>
                        <td>${cat.description}</td>
                        <td class="ED"><a href="/adv/category/ae?id=${cat.id}"><i class="far fa-edit fs-4"></i></a></td>
                        <td class="ED"><button type="button" class="btn btn-link" onclick="window.confirm('Bạn có chắc muốn xóa chuyên mục ${cat.catName}')? location.href='/adv/category/del?id=${cat.id}&conf=true' : location.href='/adv/category/';"><i class="far fa-trash-alt fs-4"></i></button></td>
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
<c:if test="${getconf}">
    <script>
        window.alert("Bạn chưa xác nhận xóa");
    </script>
</c:if>

<jsp:include page="../template/footer.jsp" flush="true"/>


