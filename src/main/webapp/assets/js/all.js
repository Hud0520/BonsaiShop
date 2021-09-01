function checkPass(){
    var pass= document.getElementById("txtPass").value.trim();
    var pass2= document.getElementById("txtPass2").value.trim();
    var validPass= true;
    var validPass2= true;
    var message="";
    if(pass==""){
        message="Thiếu mật khẩu cho tài khoản!";
        validPass=false;
    }else{
        if((pass.length>=6) && (pass.length<=50)){
                if(pass2==""){
                    message = "Mật khẩu xác nhận lại không có";
                    validPass2=false;
                }else{
                    if(pass!=pass2){
                        message="Mật khẩu xác nhận không  khớp";
                        validPass2=false;
                    }
                }
        }else{
            message="Mật khẩu quá ngắn hoặc quá dài [6->50]";
            validPass= false;
        }
    }
    // Tham chiếu các đối tượng báo lỗi
    var errPass = document.getElementById("errPass");
    var errPass2 = document.getElementById("errPass2");

    if(validPass && validPass2){
        errPass.innerHTML = "<i class=\"fas fa-check-circle\"></i>";
        errPass.style.color="green";
        errPass.style.fontWeight = 600;
        errPass.style.marginTop=5;
        errPass2.innerHTML = "";
    }else{
        if(!validPass){
            errPass.innerHTML = message;
            errPass.style.color="red";
            errPass.style.fontWeight = 600;
        }else{
            errPass.innerHTML="";
        }

        if(!validPass2){
            errPass2.innerHTML = message;
            errPass2.style.color="red";
            errPass2.style.fontWeight = 600;
        }
    }
    return validPass && validPass2;

}

function check(para){
    if(checkPass()) {
        document.info.action = para;
        document.info.submit();
    }
}