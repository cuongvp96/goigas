<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1>Error page</h1>
        <font color="red">
        <h1>Có lỗi xảy ra!</h1>
    <br>
    <h1>Vui lòng chờ, hệ thống sẽ quay trở về trang chủ...</h1>
        <META HTTP-EQUIV="REFRESH" CONTENT="2;URL=index">
          <br><a href="index"> Goto home--></a>
        </font>
    </body>
    
</html>
