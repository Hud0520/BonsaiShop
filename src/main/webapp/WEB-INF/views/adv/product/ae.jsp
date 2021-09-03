<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="name" value=""/>
<c:set var="dep" value=""/>
<c:set var="slug" value=""/>
<c:set var="detail" value=""/>
<c:set var="id" value=""/>
<c:set var="qty" value=""/>
<c:set var="price" value=""/>
<c:set var="file" value=""/>
<c:set var="title" value="Thêm sản phẩm"/>
<c:if test="${plant != null}">
    <c:set var="name" value="${plant.name}"/>
    <c:set var="dep" value="${plant.description}"/>
    <c:set var="slug" value="${plant.slug}"/>
    <c:set var="title" value="Sửa sản phẩm"/>
    <c:set var="detail" value="${plant.details}"/>
    <c:set var="id" value="${plant.id}"/>
    <c:set var="qty" value="${plant.quantity}"/>
    <c:set var="price" value="${plant.price}"/>
    <c:set var="file" value="${plant.thumbnail}"/>
    <c:set var="idc" value="${plant.category.id}"/>
</c:if>
<c:if test="${err}">
    <script>
        window.alert("Bạn chưa dữ liệu bạn nhập không hợp lệ");
        location.href="/adv/product/ae";
    </script>
</c:if>
<jsp:include page="../template/header.jsp" flush="true"/>

<div class="col-md-10 ">
    <div class="view text-dark bg-light rounded p-2">
        <div class="row">
            <div class="title">
                <nav class="navbar px-2 border mb-2">
                    <h2 class="fw-bold">${title}</h2>
                    <div>
                        <c:if test="${plant == null}">
                            <button class="btn btn-success" type="button" onclick="document.info.action='/adv/product/ae/add?typ=1';document.info.submit();void(0);">Lưu</button>
                            <button class="btn btn-success " type="button" onclick="document.info.action='/adv/product/ae/add?typ=2';document.info.submit();void(0);">Lưu Và thêm</button>
                        </c:if>
                        <c:if test="${plant !=null}">
                            <button class="btn btn-success" type="button" onclick="document.info.action='/adv/product/ae/edit?id=${plant.id}';document.info.submit();void(0);">Lưu</button>
                        </c:if>
                    </div>
                </nav>
                <a href="/adv/product" class="my-2"><button class="btn btn-secondary btn-sm"><i class="fas fa-arrow-left"></i> Trở về</button></a>
            </div>
        </div>
        <div class="row mt-2">
            <div class="content">
                <div class="border rounded bg-white">
                    <h3 class="bg-info text-white p-1">Thông tin sản phẩm</h3>
                    <form enctype="multipart/form-data" class="p-1" method="POST" name="info">
                        <div class="row mb-3">
                            <label for="txtName" class="col-sm-2 col-form-label text-end">Tên sản phẩm</label>
                            <div class="col-sm-10">
                                <input type="text" name="txtName" class="form-control" id="txtName" value="${name}">
                                <input type="hidden" name="id" class="form-control" id="" value="${id}">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="txtCatName" class="col-sm-2 col-form-label text-end">Tên
                                chuyên mục</label>
                            <div class="col-sm-10">
                                <select name="txtCatName" id="txtCatName"
                                        class="form-control form-select"}>
                                    <c:forEach var="cat" items="${cats}">
                                        <c:choose>
                                            <c:when test="${cat.id==idc}">
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
                            <label for="txtNotes" class="col-sm-2 col-form-label text-end">Mô tả</label>
                            <div class="col-sm-10">
                                <textarea name="txtNotes" id="txtNotes" class="form-control" rows="3">${dep}</textarea>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="txtDetail" class="col-sm-2 col-form-label text-end">Chi tiết</label>
                            <div class="col-sm-10">
                                <textarea name="txtDetail" id="txtDetail">${detail}</textarea>
                                <script language="javascript">
                                    var editor = CKEDITOR.replace('txtDetail');
                                </script>
                            </div>
                        </div>
                        <div class="row mb-3 ">
                            <label for="txtSlug" class="col-sm-2 col-form-label text-end" >Slug</label>
                            <div class="col-sm-10">
                                <input type="text" name="txtSlug" class="form-control" id="txtSlug" value="${slug}">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="txtQty" class="col-sm-2 col-form-label text-end">Số lượng</label>
                            <div class="col-sm-4">
                                <input type="number" name="txtQty" class="form-control" id="txtQty" value="${qty}">
                            </div>

                            <label for="txtPrice" class="col-sm-2 col-form-label text-end">Đơn giá</label>
                            <div class="col-sm-4">
                                <input type="text" name="txtPrice" class="form-control" id="txtPrice" value="${price}">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="formFile" class="col-form-label col-sm-2 text-end">Ảnh</label>
                            <div class="col-sm-10">
                                <input class="form-control" name="file" type="file" id="formFile" value="${file}">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<jsp:include page="../template/footer.jsp" flush="true"/>