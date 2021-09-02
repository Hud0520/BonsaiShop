<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row py-3 bg-dark text-white mt-3">
    <div class="col-md-3"><b>VuonCayViet</b></div>
    <div class="col-md-6 text-center" id="time"></div>
    <script>
        function time(){
            var today = new Date();
            var date = today.getDate()+' / '+(today.getMonth()+1)+' / '+today.getFullYear();
            var time = today.getHours() + " : " + today.getMinutes() + " : " + today.getSeconds();
            var dateTime = date+' | '+time;

            document.getElementById("time").innerHTML = dateTime;
        }
        var time = setInterval(time,1000);
    </script>
    <div class="col-md-3 text-end">Version 1.1</div>
</div>
</div>
</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</html>