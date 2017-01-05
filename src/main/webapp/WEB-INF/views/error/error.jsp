<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>金州站减速顶管理系统</title>
    <meta charset="utf-8">
    <%-- 避免ie使用兼容模式 --%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <%--<meta http-equiv="X-UA-Compatible" content="IE=8" />--%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${contextPath}/static/favicon.ico" />
    <link href="${contextPath}/static/plugins/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
    <style>
        body{
            background: whitesmoke;
        }
        #errorInfo{
            width: 670px;
            height: 200px;
            margin: 160px auto;
            /*float: left;*/
        }
        img{
            display: inline-block;
            margin: 0px auto;
            margin-bottom: 0px;
            float: left;
            /*margin-left: auto;*/
        }
        span{
            color: #444444;
            font-family: "微软雅黑";
            /*text-align: center;*/
            display: inline-block;
            font-size: 20px;
            line-height: 40px;
            /*vertical-align: bottom;*/
            float: left;
            margin-top: 60px;
        }
        a{
            color: red;
        }
    </style>
</head>
<body>
<div id="errorInfo">
    <img src="${contextPath}/static/images/tielu.png" />
    <span>很抱歉！服务器内部错误，可能是由于您的误操所致。<br>您可以<a id="backMark" href="#">返回上一级</a>重新操作。</span>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>
<script type="text/javascript">
    $(function() {
        $('#backMark').on('click', function () {
            window.history.back(-1);
        });
    });
</script>
</body>
</html>
