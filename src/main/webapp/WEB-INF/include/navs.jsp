<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<style>
  li {
    list-style-type: none;
  }

  .header ul {
    padding-left: 0;
    margin-bottom: 0;
    /*display: none;*/
  }

  .header ul li {
    float: left;
    height: 55px;
    line-height: 55px;
    color: white;
    list-style: none;
  }

  .header ul li a {
    color: white;
  }

  .header ul {
    background: #444444;
    height: 55px;
  }

  .nav_main {
    width: 15%;
    background: #f4f4f4;
    padding: 0;
    float: left;
  }

  .nav_main .nav_side {
    padding-left: 0;
  }

  .nav_main li {
    /*height: 45px;*/
    line-height: 45px;
    border-bottom: solid 1px white;
    text-align: center;
  }

  /*.nav_main .managerInfo{*/
    /*display: none;*/
  /*}*/


  .nav_main li a {
    height: 45px;
    width: 200px;
    display: inline-block;
  }
  .nav_main .managerInfo{
    padding-left: 0;
  }
.nav_main .managerInfo li a{
  padding-left: 0%;
  /*border-left: 45px #f3f3f3 solid;*/
  margin-left: 45px;
  width: 155px;
  ;
}
  .nav_main li a:hover {
    background: #C7E0F4;
    font-weight: 900;
  }
</style>

<div class="header">
  <ul>
    <li style="width: 55%;">&nbsp;&nbsp;&nbsp;&nbsp;金州站减速顶管理系统</li>
    <li style="width: 20%;" id="clock"></li>
    <li style="width: 7%;"> <span class="glyphicon glyphicon-user"> </span> ${sessionScope.adminLogin.userName}</li>
    <li style="width: 8%;"><a href="${contextPath}/admin/routePass.action">更改密码</a></li>
    <li style="width: 10%;"><a id="logout" href="${contextPath}/admin/logout.action"><span class="glyphicon glyphicon-off"></span> 退出</a></li>
  </ul>
</div>

<div class="nav_main">
  <ul class="nav_side">
    <li role="presentation" class="active"><a id="index" href="${contextPath}/admin/home/index.action">首页</a></li>
    <c:forEach items="${sessionScope.authoritys}" var="authority" varStatus="status">
      <%--<c:if test="${authority.authorityTwoId == 1}">--%>
        <%--<li role="presentation" class="active"><a id="workerManager" href="${contextPath}/admin/worker/routeList.action">工人管理</a></li>--%>
      <%--</c:if>--%>
      <c:if test="${authority.authorityTwoId == 10}">
        <li role="presentation" class="active"><a id="placeManager" href="${contextPath}/admin/place/routeList.action">场管理</a></li>
      </c:if>
      <c:if test="${authority.authorityTwoId == 11}">
        <li role="presentation" class="active"><a id="trackManager" href="${contextPath}/admin/track/routeList.action">股道管理</a></li>
      </c:if>
      <c:if test="${authority.authorityTwoId == 12}">
        <li role="presentation" class="active"><a id="regionManager" href="${contextPath}/admin/region/routeList.action">区管理</a></li>
      </c:if>
      <c:if test="${authority.authorityTwoId == 13}">
        <li role="presentation" class="active"><a id="inspectionSearch" href="${contextPath}/admin/maintain/inspectionPage.action">巡检查询</a></li>
      </c:if>
      <c:if test="${authority.authorityTwoId == 14}">
        <li role="presentation" class="active"><a id="protectSearch" href="${contextPath}/admin/maintain/protectPage.action">防护查询</a></li>
      </c:if>
      <c:if test="${authority.authorityTwoId == 15}">
        <li role="presentation" class="active"><a id="maintainSearch" href="${contextPath}/admin/maintain/maintainPage.action">维修查询</a></li>
      </c:if>
      <c:if test="${authority.authorityTwoId == 2}">
        <li role="presentation" class="active"><a id="adminManager" href="${contextPath}/admin/adminManager/routeList.action">员工管理</a></li>
      </c:if>
      <c:if test="${authority.authorityTwoId == 3}">
        <li role="presentation" class="active"><a id="adminRole" href="${contextPath}/admin/role/routeList.action">角色管理</a></li>
      </c:if>
      <c:if test="${authority.authorityTwoId == 4}">
        <li role="presentation" class="active"><a id="logManager" href="${contextPath}/admin/log/routePage.action">日志查询</a></li>
      </c:if>
      <c:if test="${authority.authorityTwoId == 5}">
        <li role="presentation" class="active"><a id="deivceManager" href="${contextPath}/admin/device/routeList.action">顶位管理</a></li>
      </c:if>
      <c:if test="${authority.authorityTwoId == 7}">
        <li role="presentation" class="active"><a id="workerInfoList" href="${contextPath}/admin/workerInfo/routeList.action">在岗查询</a></li>
      </c:if>
      <c:if test="${authority.authorityTwoId == 30}">
        <li role="presentation" class="active downInfo"><a id="maintainManager">工作台 &nbsp;<span id="signal" class="glyphicon glyphicon-chevron-right downSign" aria-hidden="true"></span></a>
          <ul class="managerInfo" id="managerInfo">
            <li role="presentation" class="active"><a id="protect" href="${contextPath}/admin/maintain/routeIndex.action">巡检管理 &nbsp;</a></li>
            <li role="presentation" class="active"><a id="maintain" href="${contextPath}/admin/maintain/routeMaintainIndex.action">故障管理 &nbsp;</a></li>
          </ul>
        </li>
      </c:if>
    </c:forEach>
  </ul>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>
<script>
  $(document).ready(function() {
    $(".downInfo").click(function() {
      $("#managerInfo").slideToggle();
      $("#signal").toggleClass("glyphicon-chevron-down")
    });
  });

  document.getElementById('clock').write(showtime());

</script>
