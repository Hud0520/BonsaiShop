<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
    String now=dateFormat.format(new java.util.Date()).trim();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="name" value=""/>
<c:set var="dep" value=""/>
<c:set var="slug" value=""/>
<c:set var="create" value="<%=now%>"/>
<c:set var="title" value="Thêm Blog - tin tức"/>
<c:if test="${blog.id != null}">
    <c:set var="name" value="${blog.title}"/>
    <c:set var="content" value="${blog.content}"/>
    <c:set value="${blog.createdDate}" var="create"></c:set>
    <c:set var="title" value="Sửa Blog - tin tức"/>
</c:if>
<c:if test="${err}">
    <script>
        window.alert("Bạn chưa dữ liệu bạn nhập không hợp lệ");
        location.href="/adv/blog/ae";
    </script>
</c:if>
<jsp:include page="../template/header.jsp" flush="true"/>
<div class="col-md-10 ">
    <div class="view text-dark bg-light rounded p-2">
        <div class="row">
            <div class="title">
                <nav class="navbar border px-2 mb-2">
                    <h2 class="fw-bold">${title}</h2>
                    <div>
                        <c:if test="${blog == null}">
                            <button class="btn btn-success" type="button" onclick="document.info.action='/adv/blog/ae/add?typ=1';document.info.submit();void(0);">Lưu</button>
                            <button class="btn btn-success " type="button" onclick="document.info.action='/adv/blog/ae/add?typ=2';document.info.submit();void(0);">Lưu Và thêm</button>
                        </c:if>
                        <c:if test="${blog !=null}">
                            <button class="btn btn-success" type="button" onclick="document.info.action='/adv/blog/ae/edit';document.info.submit();void(0);">Lưu</button>
                        </c:if>
                    </div>
                </nav>
                <a href="/adv/blog" class="my-2"><button class="btn btn-secondary btn-sm"><i class="fas fa-arrow-left"></i> Trở về</button></a>
            </div>
        </div>
        <div class="row mt-2">
            <div class="content col-md-12  ">
                <div class="border rounded bg-white">
                    <h3 class="bg-info text-white p-1">Thông tin Blog</h3>
                    <form enctype="multipart/form-data" class="p-1" name="info" method="post">
                        <div class="row m-3">
                            <label for="txtName" class="col-sm-2 col-form-label text-end">Title</label>
                            <div class="col-sm-10">
                                <input type="text" name="txtName" class="form-control" id="txtName" value="${name}">
                                <input type="hidden" name="txtId" value="${blog.id}">
                            </div>
                        </div>
                        <div class="row m-3">
                            <label for="txtNotes" class="col-sm-2 col-form-label text-end">Nội dung</label>
                            <div class="col-sm-10">
                                <textarea name="txtNotes" id="txtNotes">${content}</textarea>
                                <script language="javascript">
                                    var editor = CKEDITOR.replace('txtNotes');
                                </script>
                            </div>
                        </div>
                        <div class="row m-3">
                            <label for="txtSlug" class="col-sm-2 col-form-label text-end">Thumbnail</label>
                            <div class="col-sm-10">
                                <input type="file" name="txtSlug" class="form-control" id="txtSlug">
                            </div>
                        </div>
                        <div class="row m-3">
                            <label for="txtDate" class="col-sm-2 col-form-label text-end">Ngày tạo</label>
                            <div class="col-sm-10">
                                <input type="date" name="txtDate" class="form-control" id="txtDate"  value="${create}" disabled>
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