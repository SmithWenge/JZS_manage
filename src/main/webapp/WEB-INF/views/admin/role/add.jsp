<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/navs.jsp"%>
<link rel="stylesheet" href="${contextPath}/static/css/jquery-labelauty.css">
<style>
  .dowebok li {
    display: inline-block;

  }
 .alignR{
   text-align: right;

 }
</style>
<div class="panel panel-default" style="float: left;width: 85%;">
  <div class="panel-heading" style="height: 45px;padding-top: 5px;"><span class="glyphicon glyphicon-map-marker"></span>角色管理 > 角色添加</div>
  <div class="panel-body">
    <div class="row" style="margin-top: 5px;">
      <c:if test="${not empty addFailureMessage}">
        <div class="col-md-12" id="message">
          <p class="bg-danger">${addFailureMessage}</p>
        </div>
      </c:if>
      <div class="col-md-12">
        <form class="form-horizontal" action="${contextPath}/admin/role/add.action" method="post" id="addForm">
          <div class="form-group">
            <label for="roleName" class="col-sm-2 control-label">角色名</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" id="roleName" placeholder="请输入角色名" name="roleName">
            </div>
          </div>
          <div class="form-group" >
            <h4 class="col-sm-offset-5" style="margin-bottom: 40px;"><b>请选择功能</b></h4>
              <ul style="list-style: none;padding-left: 20px" class="roles">
                <%--<li class="col-sm-2 alignR"><b>工人管理</b></li>--%>
                <%--<ul class="dowebok">--%>
                  <%--<c:forEach items="${workers}" var="worker">--%>
                      <%--<li><input type="checkbox" name="workerCheckbox" data-labelauty="${worker.functionName}" value="${worker.functionIdS}${":"}${"1"}"></li>--%>
                  <%--</c:forEach>--%>
                <%--</ul>--%>
                <li class="col-sm-2 alignR"><b>员工管理</b></li>
                <ul class="dowebok">
                  <c:forEach items="${adminUsers}" var="adminUser">
                      <li><input type="checkbox" name="adminUserCheckbox" data-labelauty="${adminUser.functionName}" value="${adminUser.functionIdS}${":"}${"2"}"></li>
                  </c:forEach>
                </ul>
                <li class="col-sm-2 alignR"><b>角色管理</b></li>
                <ul class="dowebok">
                  <c:forEach items="${roles}" var="role">
                    <li><input type="checkbox" name="roles" data-labelauty="${role.functionName}" value="${role.functionIdS}${":"}${"3"}"></li>
                  </c:forEach>
                </ul>
                <li class="col-sm-2 alignR"><b>日志查询</b></li>
                <ul class="dowebok">
                  <c:forEach items="${logs}" var="log">
                    <li><input type="checkbox" name="logs" data-labelauty="${log.functionName}" value="${log.functionIdS}${":"}${"4"}"></li>
                  </c:forEach>
                </ul>
                <li class="col-sm-2 alignR"><b>顶位管理</b></li>
                <ul class="dowebok">
                  <c:forEach items="${devices}" var="device">
                    <li><input type="checkbox" name="devices" data-labelauty="${device.functionName}" value="${device.functionIdS}${":"}${"5"}"></li>
                  </c:forEach>
                </ul>
                <li class="col-sm-2 alignR"><b>上岗登记</b></li>
                <ul class="dowebok">
                  <c:forEach items="${workerInfos}" var="workerInfo">
                    <li><input type="checkbox" name="workerInfos" data-labelauty="${workerInfo.functionName}" value="${workerInfo.functionIdS}${":"}${"6"}"></li>
                  </c:forEach>
                </ul>
                <li class="col-sm-2 alignR"><b>在岗查询</b></li>
                <ul class="dowebok">
                  <c:forEach items="${selectInfos}" var="selectInfo">
                    <li><input type="checkbox" name="selectInfos" data-labelauty="${selectInfo.functionName}" value="${selectInfo.functionIdS}${":"}${"7"}"></li>
                  </c:forEach>
                </ul>
                <%--<li class="col-sm-2 alignR"><b>工单查询</b></li>--%>
                <%--<ul class="dowebok">--%>
                  <%--<c:forEach items="${selectMaintains}" var="selectMaintain">--%>
                    <%--<li><input type="checkbox" name="selectMaintains" data-labelauty="${selectMaintain.functionName}" value="${selectMaintain.functionIdS}${":"}${"8"}"></li>--%>
                  <%--</c:forEach>--%>
                <%--</ul>--%>
                <li class="col-sm-2 alignR"><b>场管理</b></li>
                <ul class="dowebok">
                  <c:forEach items="${places}" var="place">
                    <li><input type="checkbox" name="places" data-labelauty="${place.functionName}" value="${place.functionIdS}${":"}${"10"}"></li>
                  </c:forEach>
                </ul>
                <li class="col-sm-2 alignR"><b>股道管理</b></li>
                <ul class="dowebok">
                  <c:forEach items="${tracks}" var="track">
                    <li><input type="checkbox" name="tracks" data-labelauty="${track.functionName}" value="${track.functionIdS}${":"}${"11"}"></li>
                  </c:forEach>
                </ul>
                <li class="col-sm-2 alignR"><b>区管理</b></li>
                <ul class="dowebok">
                  <c:forEach items="${regions}" var="region">
                    <li><input type="checkbox" name="regions" data-labelauty="${region.functionName}" value="${region.functionIdS}${":"}${"12"}"></li>
                  </c:forEach>
                </ul>
                <li class="col-sm-2 alignR"><b>故障类型管理</b></li>
                <ul class="dowebok">
                  <c:forEach items="${faultTypes}" var="faultType">
                    <li><input type="checkbox" name="faultTypes" data-labelauty="${faultType.functionName}" value="${faultType.functionIdS}${":"}${"16"}"></li>
                  </c:forEach>
                </ul>
                <li class="col-sm-2 alignR"><b>巡检查询</b></li>
                <ul class="dowebok">
                  <c:forEach items="${selectInspections}" var="selectInspection">
                    <li><input type="checkbox" name="selectInspections" data-labelauty="${selectInspection.functionName}" value="${selectInspection.functionIdS}${":"}${"13"}"></li>
                  </c:forEach>
                </ul>
                <li class="col-sm-2 alignR"><b>防护查询</b></li>
                <ul class="dowebok">
                  <c:forEach items="${selectProtections}" var="selectProtection">
                    <li><input type="checkbox" name="selectProtections" data-labelauty="${selectProtection.functionName}" value="${selectProtection.functionIdS}${":"}${"14"}"></li>
                  </c:forEach>
                </ul>
                <li class="col-sm-2 alignR"><b>维修查询</b></li>
                <ul class="dowebok">
                  <c:forEach items="${maintainSearchs}" var="maintainSearch">
                    <li><input type="checkbox" name="maintainSearchs" data-labelauty="${maintainSearch.functionName}" value="${maintainSearch.functionIdS}${":"}${"15"}"></li>
                  </c:forEach>
                </ul>
                <li class="col-sm-2 alignR"><b>值班员工作台</b></li>
                <ul class="dowebok">
                  <c:forEach items="${maintains}" var="maintain">
                    <li><input type="checkbox" name="maintains" data-labelauty="${maintain.functionName}" value="${maintain.functionIdS}${":"}${"30"}"></li>
                  </c:forEach>
                </ul>
              </ul>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-4">
              <button type="submit" class="btn btn-info col-sm-5">添加角色</button>
              <button type="button" id="backMark" class="btn btn-default" style="margin-left: 200px">返回</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src="${contextPath}/static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${contextPath}/static/js/jquery-labelauty.js"></script>
<script type="text/javascript" src="${contextPath}/static/plugins/bootstrap/js/bootstrap.js" ></script>
<script type="text/javascript" src="${contextPath}/static/support/jquery.placeholder.js" ></script>
<script>
  $(function(){
    $(':input').labelauty();
  });
</script>

<script type="text/javascript">
  $(function () {
    $('#backMark').on('click', function () {
      window.history.back(-1);
    });

    $('input, textarea').placeholder();

    $('#addForm').validate({
      rules: {
        roleName: {
          required: true
        }
      },
      messages: {
        roleName: {
          required: "请填写角色名"
        }
      }
    });
  });
</script>

<div style="text-align:center;clear:both;">
  <script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>
  <script src="/follow.js" type="text/javascript"></script>
</div>

<link href="/css/bootstrap-ie8.css" rel="stylesheet">
<script src="${contextPath}/static/support/html5shiv.min.js"></script>
<script src="${contextPath}/static/support/respond.min.js"></script>

</body>
</html>
