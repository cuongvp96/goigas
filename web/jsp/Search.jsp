<%@page import="java.util.ArrayList"%>
<%@page import="Model.CuaHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@taglib uri="http://displaytag" prefix="display" %>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tìm kiếm</title>
        <link rel="stylesheet" media="screen" href="css/boostrap.min.css">
        <meta name="google-signin-client_id" content="702639052870-59gr7lv1p4beolgbv68jh4bqu4eat04n.apps.googleusercontent.com">
    </head>
    <body >
        <script src="https://apis.google.com/js/platform.js" async defer></script>

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

        <div class="g-signin2" style="display: none"></div>
        <div >
            <!--<div class="row">-->
            <!--<div class="col-md-15" style="width: 100%">-->
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
            <br>
            <s:form action="search" method="post"  cssStyle="padding: 20px;">
                <s:select name="categorySearchChoise" label="Tìm kiếm theo"
                          headerKey="1"
                          list="listSearch"
                          />
                <s:textfield cssClass="form-control" name="textSearch" 
                             label="Tìm kiếm"  placeholder="Nhập nội dung tìm kiếm" size="50px"></s:textfield>
                <s:submit cssClass="btn btn-primary" value="Tìm kiếm" 
                          align="center" cssStyle="margin-top: 20px;" ></s:submit>
            </s:form>
            <s:actionerror cssStyle="list-style-type:none;"></s:actionerror>
            <s:actionmessage cssStyle="list-style-type:none;"></s:actionmessage>

            <s:if test="%{listCuaHang!= null}">
                <table id="tbsearch" border="1" style="margin-left: 40px; margin-right: 40px; text-align: center"
                       cellpadding="2" cellspacing="2">
                    <thead class="table-primary" >
                        <tr style="font-weight: 600;">
                            <td>Stt</td>
                            <td>Ảnh</td>
                            <td>ID CH</td>
                            <td>Tên cửa hàng</td>
                            <td>Loại gas</td>
                            <td>Giá gas</td>
                            <td>SDT</td>
                            <td>Chủ cửa hàng</td>
                            <td>Địa điểm</td>
                            <td> latlng</td>
                            <td>user_name</td>
                            <td>Điểm vote</td>
                            <td>Số vote</td>
                            <td>Số comment</td>
                            <td style="width: 100px">Tùy chỉnh</td>
                        </tr>
                    </thead>
                    <tbody >
                        <%
                            int i = 1;
                        %>
                        <s:iterator value="listCuaHang" >
                            <tr align="center">
                                <td><%=i++%></td>
                                <td><image src="<s:property value="linkimg"/>" width="80" height="80" alt="1"/></td>
                                <td><s:property value="idcuahang" /></td>
                                <td><s:property value="tencuahang" /></td>
                                <td align="center"><s:property value="loaigas" /></td>
                                <td><s:property value="giagas" /></td>
                                <td><s:property value="sdt" /></td>
                                <td><s:property value="chucuahang" /></td>
                                <td><s:property value="diadiem" /></td>
                                <td><s:property value="latlng" /></td>
                                <td><s:property value="user_name" /></td>
                                <td><s:property value="votescore" /></td>
                                <td><s:property value="votenumber" /></td>
                                <td><s:property value="commentnumber" /></td>
                                <td>
                                    <form id="div_editCh" method="post"  action="editCH" style="display: inline;">
                                        <input type="hidden" name="chid" value="<s:property value="idcuahang" />"/>
                                        <input type="submit"  value="Sửa" />
                                    </form>
                                    <form method="post"  action="delCH" style="display: inline;">
                                        <input type="hidden" name="chid" value="<s:property value="idcuahang" />"/>
                                        <input type="submit"  value="Xóa"  class=" btn-danger" onclick="return confirm('Ban co muon xoa khong?')"/>
                                    </form>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </s:if>
            <%
                String numberItem = "" + session.getAttribute("numberItem");
                int k = Integer.parseInt(numberItem);
                int i1 = 1;
                if (k % 5 == 0) {
                    k = k / 5;
                } else {
                    k = k / 5 + 1;
                }
                if (k > 1) {
            %>
            <nav aria-label="Page navigation example" style=" padding: 20px; width: 50%; margin: auto;">
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="paging?begin=0&numend=5" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </li>

                    <%
//                        if(k>1){
                        for (int j = 0; j < k; j += 1) {
                    %>
                    <li class="page-item"><a class="page-link" href="paging?begin=<%=j * 5%>&numend=5"><%=i1++%></a></li>
                        <%
                            }
                        %>
                    <li class="page-item">
                        <a class="page-link" href="paging?begin=<%=(k - 1) * 5%>&numend=5" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                </ul>
            </nav>
            <%
                }else{
            %>
            <br/> <br/> <br/> <br/> <br
             <%
}
            %>
        </div>
    </body>
</html>
