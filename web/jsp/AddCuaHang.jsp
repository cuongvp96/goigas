<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm cửa hàng mới</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_map.css">
        <script src="${pageContext.request.contextPath}/js/google_map2.js"></script>
        <link class="jsbin" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
        <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
        <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
        <meta name="google-signin-client_id" content="702639052870-59gr7lv1p4beolgbv68jh4bqu4eat04n.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js" async defer></script>

    </head>
    <body>
        <div class="g-signin2" style="display: none"></div>

        <!--        <script async defer
                        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAmPXjNX6xQqv9nw5Ui5UYtFH2gbdoVKYQ&callback=initMap">
                </script>-->
        <s:if test="#session['user_login']==null || #sesion['google_id']==null">
            <META HTTP-EQUIV="REFRESH" CONTENT="1;URL=login">
        </s:if>
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

            function KeyPress()
            {
                if (window.event.keyCode === 13)
                    window.event.keyCode = 0;
                return false;
            }
            function clickSubmit() {
                document.getElementById("fm_sbadd").submit();
            }
        </script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAmPXjNX6xQqv9nw5Ui5UYtFH2gbdoVKYQ&libraries=places&callback=initAutocomplete"
        async defer></script>

       <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
                <a class="navbar-brand" href="index">
                    <img src="${pageContext.request.contextPath}/img/home_icon.png" width="30" height="30" class="d-inline-block align-top" alt="">
                    Home
                </a>
                <ul class="nav navbar-nav ">
                    <li  class="nav-item active"><a class="nav-link"  href="addCuaHang">Thêm cửa hàng</a></li>
                </ul>
                <ul class="nav navbar-nav ">
                    <li  class="nav-item active"><a class="nav-link"  href="search">Tìm cửa hàng</a></li>
                </ul>
                <div class="navbar-collapse collapse"></div>
                <ul class="nav navbar-right">
                    <span class="navbar-text" style="color: white;">Xin chào ${user_fullname}!</span>
                    <li class="nav-item active" ><a class="nav-link" href="#" onclick="signOut();">Đăng xuất</a></li>
                </ul>
            </nav>
                <s:actionerror cssStyle="list-style-type:none;"/>
        <s:actionmessage/>
        <s:div id="add_ch"  class="card" cssStyle="margin-top: 10px">
            <s:div class="card-block">
                <s:form method="post" onKeyPress="KeyPress()" action="checkAddCuaHang" id="fm_sbadd"  enctype="multipart/form-data">

                    <h3 class="card-title" style=" text-align: center; margin-bottom: 30px;"> Thêm cửa hàng!</h3>
                    <s:div id="add_main1" cssClass="form-group" >
                        <s:textfield cssClass="form-control" name="ch.tencuahang" label="Tên cửa hàng" placeholder="Nhập tên cửa hàng" ></s:textfield>
                        <s:textfield cssClass="form-control" name="ch.loaigas" label="Loại gas" placeholder="Nhập loại gas"></s:textfield>
                        <s:textfield cssClass="form-control" name="ch.giagas" label="Giá gas" type="number" placeholder="Nhập giá gas"></s:textfield>
                        <s:textfield cssClass="form-control" name="ch.sdt" label="SDT" placeholder="Số điện thoại"></s:textfield>
                        <s:textfield cssClass="form-control" name="ch.chucuahang" label="Tên chủ cửa hàng" placeholder="Tên chủ của hàng" value="%{#session.user_fullname}"></s:textfield>
                        <s:file cssClass="form-control-file" name="photo" id="photo"  label="Hình ảnh" accept="image/gif, image/jpeg, image/png"
                                ></s:file>
                    </s:div>
                    <input id="pac-input"  type="text" placeholder="Nhập địa điểm cần tìm..."  onKeyPress="KeyPress();" onkeyup="KeyPress();"/>
                    <s:div id="map" cssClass="form-group">
                    </s:div>
                    <s:div id="add_main2" cssClass="card-body" >
                        <s:textarea cssClass="form-control" name="ch.diadiem" id="iddiadiem" label="Địa Điểm" cols="30" rows="3" 
                                    cssStyle=" resize: none;"></s:textarea>
                        <s:textfield cssClass="form-control" name="ch.latlng" id="idlatlng" label="Vị trí địa lý" readonly="true"></s:textfield>
                        <%--<s:submit type="button" id="sb" name="sb" value="Gửi"--%>
                        <!--align="center" cssClass="btn btn-primary"-->
                        <!--/>-->
                    </s:div>
                </s:form>
                <button style="margin-left: 300px" type="submit" id="sb" name="sb"
                        value="Gửi" class="btn btn-primary" onclick="clickSubmit();">Gửi</button>
            </s:div>
        </s:div>

        <img class="card-body" id="imgInp" src="#" alt="Your image"/>  
        <script>
            function readURL(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        $('#imgInp')
                                .attr('src', e.target.result)
                                .width(150)
                                .height(200);
                    };

                    reader.readAsDataURL(input.files[0]);
                }
            }
            $("#photo").change(function () {
                readURL(this);
            });

        </script>  

        <!--<br><a class="card-body" href="index"> Goto home</a>-->
    </body>
</html>
