<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <meta name="google-signin-client_id" content="702639052870-59gr7lv1p4beolgbv68jh4bqu4eat04n.apps.googleusercontent.com">
    </head>
    <body style="background: #0c5460">
         
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script type = "text/javascript" src = "js/fb_login.js" ></script>
        <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
      
        <s:if test="#session['user_login']!=null || #sesion['google_id']!=null">
            <h2 style="color: #FFFFFF">Bạn đã đăng nhập thành công, trang web sẽ tự chuyển!</h2>   
<!--            <script>
                document.getElementById("div_login").style.visibility = hide;
            </script>-->
            <META HTTP-EQUIV="REFRESH" CONTENT="1;URL=index">
        </s:if>
        <script>
            window.___gcfg = {
        lang: 'vi-VN',
    parsetags: 'onload'
      };
            function onSignIn(googleUser) {
                var profile = googleUser.getBasicProfile();
                document.getElementById('uIdGG').value = profile.getId();
//                document.getElementById('smgg').value = "Tiếp tục đăng nhập với tư cách " + profile.getName()+" !";
//                document.getElementById("gg_form").value="Chọn tài khoản";
                if (${faile_google==1}) {
                    var auth2 = gapi.auth2.getAuthInstance();
                    auth2.signOut().then(function () {
//                        console.log('User signed out.');
                    });
                    //document.getElementById('uIdGG').value = "";
                }
                document.getElementById("gg_form").submit();
            }
            function onSuccess(googleUser) {
                var profile = googleUser.getBasicProfile();
                document.getElementById('uIdGG').value = profile.getId();
                if (${fail_google==1}) {
                    var auth2 = gapi.auth2.getAuthInstance();
                    auth2.signOut().then(function () {
//                        console.log('User signed out.');
                    });
                }
                document.getElementById("gg_form").submit();
            }
            function onFailure(error) {
                console.log(error);
            }
            function renderButton() {
                gapi.signin2.render('my-signin2', {
                    'scope': 'profile email',
                    'width': 200,
                    'height': 40,
                    'longtitle': true,
                    'theme': 'dark',
                    'onsuccess': onSuccess,
                    'onfailure': onFailure
                });
            }
        </script>

        <s:div  cssClass="card" style="width: 200px;align-content: center; 
                margin-left: 40px; width: 450px; margin:0 auto; margin-top: 80px;" >
            <s:div class="card-block" >
                <h4 class="card-header" style="text-align: center;">Đăng nhập</h4>
                <s:actionerror cssStyle="  color: red;"/>
                <div id="div_login">
                    <s:form action="checkLogin" method="post"  style="padding: 20px;">
                        <s:label value="Tên tài khoản" cssStyle="font-weight: bold; "></s:label>
                        <s:textfield cssClass="form-control" name="user.user_name" placeholder="Nhập tên tài khoản" size="50px"></s:textfield>
                        <s:label  value="Mật khẩu" cssStyle="font-weight: bold;"></s:label>
                        <s:password cssClass="form-control" name="user.password" placeholder="Nhập mật khẩu" size="50px"></s:password>
                        <s:submit cssClass="btn btn-primary" value="Đăng nhập" 
                                  align="center" cssStyle="margin-top: 20px;" ></s:submit>
                    </s:form>
                </div>
                <s:div style=" width: 50%; margin: 0 auto;">
                    <form id="gg_form" action="checkLoginGG" method="post">
                        <input type="hidden" name="googleid" id="uIdGG"/>
                        <!--<div id="gglg" class="g-signin2" data-onsuccess="onSignIn"></div>-->
                        <div id="my-signin2" ></div>
                    </form>
                <fb:login-button data-max-rows="1" data-size="medium" data-button-type="continue_with" 
                                 data-show-faces="true" data-auto-logout-link="false" data-use-continue-as="true"
                                 scope="public_profile,email"
                                 onlogin="checkLoginState();">
                </fb:login-button>
            </s:div>
            <form id="fb_form" action="checkLoginFB" method="post" >
                <input type="hidden" name="fbid" id="idFb"/>
            </form>
        </s:div>
    </s:div>




</body>

</html>
