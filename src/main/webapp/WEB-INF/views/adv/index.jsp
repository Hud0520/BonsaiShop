<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
<fmt:formatDate var="mounth" value="${now}" pattern="MM" />

<jsp:include page="template/header.jsp" flush="true"/>
<div class="col-md-10">
    <div class="view text-dark bg-light p-2">
        <div class="row">
            <div class="title">
                <nav class="navbar border px-2 ">
                    <h2 class="fw-bold">DashBoard</h2>
                </nav>
            </div>
        </div>
        <div class="row mt-3">
            <div class="content">
                <div class="row  g-4">
                    <div class="col">
                        <div class="card text-light bg-success mb-3" style="max-width: 18rem;">
                            <div class="card-body pt-2">
                                <h1 class="card-title text-end">${card.get(0)}</h1>
                                <p>Tổng đơn hàng trong tháng ${mounth}</p>
                            </div>
                            <a href="/adv/order/dhm?st=true" class="text-light text-decoration-none ">
                            <div class="card-footer text-center">
                                Xem thêm <i class="fas fa-arrow-right"></i>
                            </div></a>
                        </div>
                    </div>
                    <div class="col">
                        <div class="card text-light bg-warning mb-3" style="max-width: 18rem;">
                            <div class="card-body pt-2">
                                <h1 class="card-title text-end">${card.get(1)}</h1>
                                <p>Đơn hàng chờ xử lý<br>&nbsp;</p>
                            </div>
                            <a href="/adv/order/new?st=true" class="text-light text-decoration-none ">
                                <div class="card-footer text-center">
                                    Xem thêm <i class="fas fa-arrow-right"></i>
                                </div></a>
                        </div>
                    </div>
                    <div class="col">
                        <div class="card text-light bg-info mb-3" style="max-width: 18rem;">
                            <div class="card-body pt-2">
                                <h1 class="card-title text-end">${card.get(2)}</h1>
                                <p>Những sản phẩm được mua trong tháng ${mounth}</p>
                            </div>
                            <a href="/adv/product/phb?st=true" class="text-light text-decoration-none ">
                                <div class="card-footer text-center">
                                    Xem thêm <i class="fas fa-arrow-right"></i>
                                </div></a>
                        </div>
                    </div>
                    <div class="col">
                        <div class="card text-light bg-danger mb-3" style="max-width: 18rem;">
                            <div class="card-body pt-2">
                                <h1 class="card-title text-end">${card.get(3)}</h1>
                                <p>Sản phẩm it được mua trong năm ${year}</p>
                            </div>
                            <a href="/adv/product/plb?st=true" class="text-light text-decoration-none ">
                                <div class="card-footer text-center">
                                    Xem thêm <i class="fas fa-arrow-right"></i>
                                </div></a>
                        </div>
                    </div>
                </div>

                <!--Biểu đồ-->
                <div class="row">
                    <div class="col-md-6">
                        <div class="card chart-container">
                            <h5 class="p-1 fw-bold">Hóa đơn</h5>
                            <canvas id="chart"></canvas>
                        </div>
                        <script
                                src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.js"></script>
                        <script>
                            const ctx = document.getElementById("chart").getContext('2d');
                            const myChart = new Chart(ctx, {
                                type: 'line',
                                data: {
                                    labels: ["Jan","Feb","Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"],
                                    datasets: [{
                                        label: "Đơn hàng hợp lệ",
                                        borderColor: 'rgb(47, 128, 237)',
                                        data: ${card.get(4).get(0)},
                                    },{
                                        label: 'Đơn hàng không hợp lệ',
                                        borderColor: 'rgb(255, 51, 51)',
                                        data: ${card.get(4).get(2)},
                                    }]
                                },
                                options: {
                                    scales: {
                                        yAxes: [{
                                            ticks: {
                                                beginAtZero: true,
                                            }
                                        }]
                                    }
                                },
                            });
                        </script>

                    </div>
                    <div class="col-md-6">
                        <div class="card chart-container">
                            <h5 class="p-1 fw-bold">Doanh thu</h5>
                            <canvas id="chart1"></canvas>
                        </div>
                        <script
                                src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.js"></script>
                        <script>
                            const ctx1 = document.getElementById("chart1").getContext('2d');
                            const myChart1 = new Chart(ctx1, {
                                type: 'line',
                                data: {
                                    labels: ["Jan","Feb","Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"],
                                    datasets: [{
                                        label: 'Năm:${year}',
                                        backgroundColor: 'rgba(153, 255, 153, 1)',
                                        borderColor: 'rgb(0, 153, 67)',
                                        data: ${card.get(4).get(1)},
                                    }]
                                },
                                options: {
                                    scales: {
                                        yAxes: [{
                                            ticks: {
                                                beginAtZero: true,
                                            }
                                        }]
                                    }
                                },
                            });
                        </script>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<jsp:include page="template/footer.jsp" flush="true"/>


