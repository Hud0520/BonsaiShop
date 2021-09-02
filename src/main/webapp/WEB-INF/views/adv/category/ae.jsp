<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../template/header.jsp" flush="true"/>
<c:set var="name" value=""/>
<c:set var="dep" value=""/>
<c:set var="slug" value=""/>
<c:set var="title" value="Thêm chuyên mục"/>
<c:if test="${cat != null}">
    <c:set var="name" value="${cat.catName}"/>
    <c:set var="dep" value="${cat.description}"/>
    <c:set var="slug" value="${cat.slug}"/>
    <c:set var="title" value="Sửa chuyên mục"/>
</c:if>
<c:if test="${err}">
    <script>
        window.alert("Bạn chưa dữ liệu bạn nhập không hợp lệ");
        location.href="/adv/category";
    </script>
</c:if>
<div class="col-md-10 ">
    <div class="view text-dark bg-light rounded p-2">
        <div class="row">
            <div class="title">
                <nav class="navbar border px-2 mb-2">
                    <h2 class="fw-bold">${title}</h2>
                    <div>
                        <c:if test="${cat == null}">
                            <button class="btn btn-success" type="button" onclick="document.info.action='/adv/category/ae/add?typ=1';document.info.submit();void(0);">Lưu</button>
                            <button class="btn btn-success " type="button" onclick="document.info.action='/adv/category/ae/add?typ=2';document.info.submit();void(0);">Lưu Và thêm</button>
                        </c:if>
                        <c:if test="${cat !=null}">
                            <button class="btn btn-success" type="button" onclick="document.info.action='/adv/category/ae/edit';document.info.submit();void(0);">Lưu</button>
                        </c:if>
                    </div>
                </nav>
                <a href="/adv/category" class="my-2"><button class="btn btn-secondary btn-sm"><i class="fas fa-arrow-left"></i> Trở về</button></a>
            </div>
        </div>
        <div class="row mt-2">
            <div class="content">
                <form class="" method="POST" name="info">
                    <div class="border rounded bg-white">
                        <h3 class="bg-info text-white p-1">Thông tin chuyên mục</h3>

                        <div class="row m-3">
                            <label for="txtName" class="col-sm-2 col-form-label text-end">Tên chuyên
                                mục</label>
                            <div class="col-sm-10">
                                <input type="text" name="txtName" class="form-control" id="txtName" value="${name}">
                                <input type="hidden" name="txtId" value="${cat.id}">
                            </div>
                        </div>
                        <div class="row m-3">
                            <label for="txtNotes" class="col-sm-2 col-form-label text-end">Mô tả</label>
                            <div class="col-sm-10">
                                <textarea name="txtNotes" id="txtNotes">${dep}</textarea>
                                <script language="javascript">
                                    var editor = CKEDITOR.replace('txtNotes');
                                </script>
                            </div>
                        </div>
                        <div class="row m-3">
                            <label for="txtSlug" class="col-sm-2 col-form-label text-end">Slug</label>
                            <div class="col-sm-10">
                                <input type="text" name="txtSlug" class="form-control" id="txtSlug" value="${slug}">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
<jsp:include page="../template/footer.jsp" flush="true"/>
