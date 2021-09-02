
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login - Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="<c:url value="/assets/css/layout.css"/>" rel="stylesheet" type="text/css">
</head>

<body>
<div class="container-sm">
    <div class="row gx-5">
        <div class="col-md-6 offset-md-3">
            <form name="frmLogin" class="bg-light border rounded" action="/adv/login" method="POST">
                <div class="loginview">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="title bg-dark p-2 fs-4 text-white text-center">Login</div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-10 offset-md-1 text-center">
                            <p class="bg-warning rounded-bottom ">${errorMessge}</p>
                        </div>
                    </div>

                    <div class="row p-2">
                        <div class="col-md-1"></div>
                        <label class="col-md-3 text-right col-form-label fw-bold" >Username</label>
                        <div class="col-md-7"><input type="text" id="txtName" class="form-control" name="username"/></div>
                    </div>

                    <div class="row p-2">
                        <div class="col-md-1"></div>
                        <label class="col-md-3 text-right col-form-label fw-bold" >Password</label>
                        <div class="col-md-7"><input type="password" id="txtPass" class="form-control" name="password"/></div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-6 offset-md-4 p-2">
                            <div class="form-check">
                                <input type="checkbox" id="chkSave" class="form-check-input"/>
                                <label class="form-check-label" for="chkSave">Save password!</label>
                            </div>
                        </div>
                    </div>

                    <div class="row p-2">
                        <div class="col-md-12 text-center">
                            <a href="#"><i class="fas fa-key"></i> Password forget</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                            <a href="#"><i class="fas fa-question-circle"></i> Help!</a>
                        </div>
                    </div>

                    <div class="row p-3">
                        <div class="col-md-12 text-center">
                            <button type="submit" class="btn btn-primary btn-sm" ><i class="fas fa-sign-in-alt"></i> Sign in</button>&nbsp;&nbsp;&nbsp;&nbsp;
                            <button type="button" onclick="location.href='/'" class="btn btn-primary btn-sm" >Exit <i class="fas fa-sign-out-alt"></i></button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>