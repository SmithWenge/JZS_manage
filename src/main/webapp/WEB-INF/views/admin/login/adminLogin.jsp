<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>金州站减速顶管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <%--<meta http-equiv="X-UA-Compatible" content="IE=8" />--%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${contextPath}/static/plugins/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="${contextPath}/static/plugins/bootstrap/css/bootstrap-theme.css" />
    <title>金州站减速顶管理系统</title>
    <style>
        .content{
            margin: 200px auto 0;
            width: 420px;
            background: #fff;
            height: 255px;
            opacity: 0.8;
            padding:10px ;
        }

        .right_div{
            width: 400px;
            padding: 16px 40px;
            margin: 0 auto;
        }
        #inputAdminLoginName,#inputAdminLoginPass{
            width: 270px;
            height: 40px;
            margin-top: 10px;
            opacity: 0.9;
        }
        #sub,#forget{
            width: 270px;
            height: 35px;
            margin: 15px 20px 0;
        }
        #forget{
            margin-top: 10px;
        }
        .right_div{
            border-shadow: 5px #A0A0A0;
            /*border: 2px #fff solid;*/
        }
    </style>
</head>


<body>
<form action="${contextPath}/admin/login.action" method="post" id="adminLoginForm">
    <div class="content"  style="border: 2px solid #A0A0A0;background: #eee">
        <div class="right_div">
            <H3 style="text-align: center;margin: 0px 0 16px 0;padding: 10px 0;border-bottom: 1px solid #444">用户登录</H3>
            <label for="inputAdminLoginName"><span class="glyphicon glyphicon-user"></span><span>&nbsp;</span><span>&nbsp;</span></label>
            <input type="text" id="inputAdminLoginName" name="userLoginName" placeholder="admin" /><br />
            <label for="inputAdminLoginPass"><span class="glyphicon glyphicon-lock"></span><span>&nbsp;</span><span>&nbsp;</span></label>
            <input type="password" id="inputAdminLoginPass" name="userLoginPass" placeholder="密码" /><br />
            <span>&nbsp;</span><button class="btn btn-info" type="submit" id="sub">登录</button>
        </div>
    </div>
</form>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>