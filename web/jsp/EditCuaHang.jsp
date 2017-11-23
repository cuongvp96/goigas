<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sửa cửa hàng</title>
        <meta name="google-signin-client_id" content="702639052870-59gr7lv1p4beolgbv68jh4bqu4eat04n.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_map.css">
        <script src="${pageContext.request.contextPath}/js/google_map2.js"></script>
    </head>
    <body>
         <s:if test="#session['user_login']==null || #sesion['google_id']==null">
            <META HTTP-EQUIV="REFRESH" CONTENT="1;URL=login">
        </s:if>
        <div class="g-signin2" style="display: none"></div>
        <script>
            function signOut() {
                var auth2 = gapi.auth2.getAuthInstance();
                auth2.signOut().then(function () {
                    console.log('User signed out.');
                });
                window.location.href = 'signOut';
            }
            FB.logout(function (response) {
                // Person is now logged out
            });
        </script>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <a class="navbar-brand" href="index">
                <img src="${pageContext.request.contextPath}/img/home_icon.png" width="30" height="30" class="d-inline-block align-top" alt="">
                Home
            </a>
            <ul class="nav navbar-nav ">
                <li  class="nav-item active"><a class="nav-link"  href="addCuaHang">Thêm cửa hàng</a></li>
            </ul>
            <div class="navbar-collapse collapse"></div>
            <ul class="nav navbar-right">
                <span class="navbar-text" style="color: white;">Xin chào ${user_fullname}!</span>
                <li class="nav-item active" ><a class="nav-link" href="#" onclick="signOut();">Đăng xuất</a></li>
            </ul>
        </nav>
        <s:div  cssClass="card"  cssStyle="width: 400px; margin:auto; 
                align-content: center; margin-top: 10px;" >
            <s:actionerror/>
            <s:actionmessage/>
            <s:div class="card-block" >
                <h4 class="card-header" style="text-align: center; ">Sửa cửa hàng</h4>
                <s:form method="post" action="checkEditCuaHang" cssStyle="padding: 20px;">
                    <s:textfield cssClass="form-control" name="chEdit.tencuahang" label="Tên cửa hàng"></s:textfield>
                    <s:textfield cssClass="form-control" name="chEdit.loaigas" label="Loại gas"></s:textfield>
                    <s:textfield cssClass="form-control" name="chEdit.giagas" label="Giá gas" type="number"></s:textfield>
                    <s:textfield cssClass="form-control" name="chEdit.sdt" label="SDT"></s:textfield>
                    <s:textfield cssClass="form-control" name="chEdit.chucuahang" label="Tên chủ cửa hàng"></s:textfield>
                    <input type="hidden" name="chEdit.idcuahang" value="${chEdit.idcuahang}" />
                    <s:submit cssClass="btn btn-primary" value="Gửi" align="center" cssStyle="margin-top: 20px;"/>
                </s:form>

            </s:div>
            <a href="index" style="margin-bottom: 20px; margin-left: 40px"> Goto home--></a>  
        </s:div>


    </body>
</html>
