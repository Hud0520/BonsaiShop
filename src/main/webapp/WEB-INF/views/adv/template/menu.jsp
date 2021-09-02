<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="acc bg-dark">
            <div class="row ">
                <div class="col-md-4 text-light"><b>Vườn Cây Việt</b></div>
                <div class="col-md-8">
                    <div class="row">
                        <div class="col-md-8"></div>
                        <div class="col-md-2 text-end text-light"><i class="far fa-user-circle"></i> Admin</div>
                        <div class="col-md-2 text-end text-light"><a href="/adv/logout" class="text-decoration-none">Logout <i class="fas fa-sign-out-alt"></i></a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row mt-5">
        <div class="col-md-2">
            <div class="menu">
                <div class="accordion" id="accordionExample">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingOne">
                            <button class="accordion-button ${menuactive[0]}" type="button" onclick="javascript:location.href='/adv/'">
                                DashBoard
                            </button>
                        </h2>
                        <div id="collapseOne" class="accordion-collapse collapse ">
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
                            <button class="accordion-button ${menuactive[1]}" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="false"
                                    aria-controls="panelsStayOpen-collapseTwo">
                                Thống kê
                            </button>
                        </h2>
                        <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse show"
                             aria-labelledby="panelsStayOpen-headingTwo">
                            <div class="accordion-body">
                                <div class="accordion-item">
                                    <h2 class="accordion-header" id="headingTwo">
                                        <button class="accordion-button ${menuactive[2]}" type="button" onclick="javascript:location.href='/adv/category/'">
                                            Chuyên mục
                                        </button>
                                    </h2>
                                    <div id="collapseTwo" class="accordion-collapse collapse ">
                                    </div>
                                </div>
                                <div class="accordion-item">
                                    <h2 class="accordion-header" id="headingThree">
                                        <button class="accordion-button ${menuactive[3]}" type="button" onclick="javascript:location.href='/adv/product/'">
                                            Sản Phẩm
                                        </button>
                                    </h2>
                                    <div id="collapseThree" class="accordion-collapse collapse ">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingFour">
                            <button class="accordion-button ${menuactive[4]}" type="button" onclick="javascript:location.href='/adv/customer/'">
                                Khách Hàng
                            </button>
                        </h2>
                        <div id="collapseFour" class="accordion-collapse collapse ">
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingFive">
                            <button class="accordion-button ${menuactive[5]}" type="button" onclick="javascript:location.href='/adv/order/'">
                                Đơn Hàng
                            </button>
                        </h2>
                        <div id="collapseFive" class="accordion-collapse collapse ">
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingSix">
                            <button class="accordion-button ${menuactive[6]}" type="button" onclick="javascript:location.href='/adv/blog/'">
                                Blog
                            </button>
                        </h2>
                        <div id="collapseSix" class="accordion-collapse collapse ">

                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingSeven">
                            <button class="accordion-button ${menuactive[7]}" type="button" onclick="javascript:location.href='/adv/report/'">
                                Báo Cáo
                            </button>
                        </h2>
                        <div id="collapseSeven" class="accordion-collapse collapse ">

                        </div>
                    </div>
                </div>
            </div>
        </div>