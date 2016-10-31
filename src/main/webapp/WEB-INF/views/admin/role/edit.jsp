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
  <div class="panel-heading" style="height: 45px;padding-top: 5px;"><span class="glyphicon glyphicon-map-marker"></span>角色管理 > 角色编辑</div>
  <div class="panel-body">
    <div class="row" style="margin-top: 5px;">
      <c:if test="${not empty editFailureMessage}">
        <div class="col-md-12" id="message">
          <p class="bg-danger">${editFailureMessage}</p>
        </div>
      </c:if>
      <div class="col-md-12">
        <form class="form-horizontal" action="${contextPath}/admin/role/edit.action" method="post" id="addForm">
          <div class="form-group">
            <input type="hidden" name="roleId" value="${role.roleId}">
            <label for="roleName" class="col-sm-2 control-label">角色名</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" id="roleName" placeholder="请输入角色名" name="roleName" value="${role.roleName}">
            </div>
          </div>
          <div class="form-group" >
            <h4 class="col-sm-offset-5" style="margin-bottom: 40px;"><b>请选择功能</b></h4>
            <ul style="list-style: none;padding-left: 20px" class="roles">
              <%--<li class="col-sm-2 alignR"><b>工人管理</b></li>--%>
              <%--<ul class="dowebok">--%>
                <%--<c:if test="${not empty workers}">--%>
                  <%--<c:forEach items="${workers}" var="worker">--%>
                    <%--<li><input type="checkbox" checked = "checked" name="workerCheckbox" data-labelauty="${worker.functionName}" value="${worker.functionIdS}${":"}${"1"}"></li>--%>
                  <%--</c:forEach>--%>
                  <%--<c:forEach items="${workersCut}" var="workerC">--%>
                    <%--<li><input type="checkbox" name="workerCheckbox" data-labelauty="${workerC.functionName}" value="${workerC.functionIdS}${":"}${"1"}"></li>--%>
                  <%--</c:forEach>--%>
                <%--</c:if>--%>
                <%--<c:if test="${empty workers}">--%>
                  <%--<c:forEach items="${workersAll}" var="workerA">--%>
                    <%--<li><input type="checkbox" name="workerCheckbox" data-labelauty="${workerA.functionName}" value="${workerA.functionIdS}${":"}${"1"}"></li>--%>
                  <%--</c:forEach>--%>
                <%--</c:if>--%>
              <%--</ul>--%>
              <li class="col-sm-2 alignR"><b>用户管理</b></li>
              <ul class="dowebok">
              <c:if test="${not empty adminUsers}">
                <c:forEach items="${adminUsers}" var="adminUser">
                  <li><input type="checkbox" checked = "checked" name="adminUserCheckbox" data-labelauty="${adminUser.functionName}" value="${adminUser.functionIdS}${":"}${"2"}"></li>
                </c:forEach>
                <c:forEach items="${adminUsersCut}" var="adminUserC">
                  <li><input type="checkbox" name="adminUserCheckbox" data-labelauty="${adminUserC.functionName}" value="${adminUserC.functionIdS}${":"}${"2"}"></li>
                </c:forEach>
              </c:if>
              <c:if test="${empty adminUsers}">
                <c:forEach items="${adminUsersAll}" var="adminUserA">
                  <li><input type="checkbox" name="adminUserCheckbox" data-labelauty="${adminUserA.functionName}" value="${adminUserA.functionIdS}${":"}${"2"}"></li>
                </c:forEach>
              </c:if>
              </ul>
              <li class="col-sm-2 alignR"><b>角色管理</b></li>
              <ul class="dowebok">
              <c:if test="${not empty roles}">
                <c:forEach items="${roles}" var="role">
                  <li><input type="checkbox" checked = "checked" name="roles" data-labelauty="${role.functionName}" value="${role.functionIdS}${":"}${"3"}"></li>
                </c:forEach>
                <c:forEach items="${rolesCut}" var="roleC">
                  <li><input type="checkbox" name="roles" data-labelauty="${roleC.functionName}" value="${roleC.functionIdS}${":"}${"3"}"></li>
                </c:forEach>
              </c:if>
            <c:if test="${empty roles}">
              <c:forEach items="${rolesAll}" var="roleA">
                <li><input type="checkbox" name="roles" data-labelauty="${roleA.functionName}" value="${roleA.functionIdS}${":"}${"3"}"></li>
              </c:forEach>
            </c:if>
              </ul>
              <li class="col-sm-2 alignR"><b>日志管理</b></li>
              <ul class="dowebok">
              <c:if test="${not empty logs}">
                <c:forEach items="${logs}" var="log">
                  <li><input type="checkbox" checked = "checked" name="logs" data-labelauty="${log.functionName}" value="${log.functionIdS}${":"}${"4"}"></li>
                </c:forEach>
                <c:forEach items="${logsCut}" var="logC">
                  <li><input type="checkbox" name="logs" data-labelauty="${logC.functionName}" value="${logC.functionIdS}${":"}${"4"}"></li>
                </c:forEach>
              </c:if>
              <c:if test="${empty logs}">
                <c:forEach items="${logsAll}" var="logA">
                  <li><input type="checkbox" name="logs" data-labelauty="${logA.functionName}" value="${logA.functionIdS}${":"}${"4"}"></li>
                </c:forEach>
              </c:if>
              </ul>
              <li class="col-sm-2 alignR"><b>设备管理</b></li>
              <ul class="dowebok">
              <c:if test="${not empty devices}">
                <c:forEach items="${devices}" var="device">
                  <li><input type="checkbox" checked = "checked" name="devices" data-labelauty="${device.functionName}" value="${device.functionIdS}${":"}${"5"}"></li>
                </c:forEach>
                <c:forEach items="${devicesCut}" var="deviceC">
                  <li><input type="checkbox" name="devices" data-labelauty="${deviceC.functionName}" value="${deviceC.functionIdS}${":"}${"5"}"></li>
                </c:forEach>
              </c:if>
              <c:if test="${empty devices}">
                <c:forEach items="${devicesAll}" var="deviceA">
                  <li><input type="checkbox" name="devices" data-labelauty="${deviceA.functionName}" value="${deviceA.functionIdS}${":"}${"5"}"></li>
                </c:forEach>
              </c:if>
              </ul>
              <li class="col-sm-2 alignR"><b>导入上班人员</b></li>
              <ul class="dowebok">
              <c:if test="${not empty workerInfos}">
                <c:forEach items="${workerInfos}" var="workerInfo">
                  <li><input type="checkbox" checked = "checked" name="workerInfos" data-labelauty="${workerInfo.functionName}" value="${workerInfo.functionIdS}${":"}${"6"}"></li>
                </c:forEach>
                <c:forEach items="${workerInfosCut}" var="workerInfoC">
                  <li><input type="checkbox" name="workerInfos" data-labelauty="${workerInfoC.functionName}" value="${workerInfoC.functionIdS}${":"}${"6"}"></li>
                </c:forEach>
              </c:if>
              <c:if test="${empty workerInfos}">
                <c:forEach items="${workerInfosAll}" var="workerInfoA">
                  <li><input type="checkbox" name="workerInfos" data-labelauty="${workerInfoA.functionName}" value="${workerInfoA.functionIdS}${":"}${"6"}"></li>
                </c:forEach>
              </c:if>
              </ul>
              <li class="col-sm-2 alignR"><b>查看在岗人员</b></li>
              <ul class="dowebok">
              <c:if test="${not empty selectInfos}">
                <c:forEach items="${selectInfos}" var="selectInfo">
                  <li><input type="checkbox" checked = "checked" name="selectInfos" data-labelauty="${selectInfo.functionName}" value="${selectInfo.functionIdS}${":"}${"7"}"></li>
                </c:forEach>
                <c:forEach items="${selectInfosCut}" var="selectInfoC">
                  <li><input type="checkbox" name="selectInfos" data-labelauty="${selectInfoC.functionName}" value="${selectInfoC.functionIdS}${":"}${"7"}"></li>
                </c:forEach>
              </c:if>
              <c:if test="${empty selectInfos}">
                <c:forEach items="${selectInfosAll}" var="selectInfoA">
                  <li><input type="checkbox" name="selectInfos" data-labelauty="${selectInfoA.functionName}" value="${selectInfoA.functionIdS}${":"}${"7"}"></li>
                </c:forEach>
              </c:if>
              </ul>
              <%--<li class="col-sm-2 alignR"><b>维修防护查询</b></li>--%>
              <%--<ul class="dowebok">--%>
              <%--<c:if test="${not empty selectMaintains}">--%>
                <%--<c:forEach items="${selectMaintains}" var="selectMaintain">--%>
                  <%--<li><input type="checkbox" checked = "checked" name="selectMaintains" data-labelauty="${selectMaintain.functionName}" value="${selectMaintain.functionIdS}${":"}${"8"}"></li>--%>
                <%--</c:forEach>--%>
                <%--<c:forEach items="${selectMaintainsCut}" var="selectMaintainC">--%>
                  <%--<li><input type="checkbox" name="selectMaintains" data-labelauty="${selectMaintainC.functionName}" value="${selectMaintainC.functionIdS}${":"}${"8"}"></li>--%>
                <%--</c:forEach>--%>
              <%--</c:if>--%>
              <%--<c:if test="${empty selectMaintains}">--%>
                <%--<c:forEach items="${selectMaintainsAll}" var="selectMaintainA">--%>
                  <%--<li><input type="checkbox" name="selectMaintains" data-labelauty="${selectMaintainA.functionName}" value="${selectMaintainA.functionIdS}${":"}${"8"}"></li>--%>
                <%--</c:forEach>--%>
              <%--</c:if>--%>
              <%--</ul>--%>
              <li class="col-sm-2 alignR"><b>场管理</b></li>
              <ul class="dowebok">
                <c:if test="${not empty places}">
                  <c:forEach items="${places}" var="place">
                    <li><input type="checkbox" checked = "checked" name="places" data-labelauty="${place.functionName}" value="${place.functionIdS}${":"}${"10"}"></li>
                  </c:forEach>
                  <c:forEach items="${placesCut}" var="placeC">
                    <li><input type="checkbox" name="places" data-labelauty="${placeC.functionName}" value="${placeC.functionIdS}${":"}${"10"}"></li>
                  </c:forEach>
                </c:if>
                <c:if test="${empty places}">
                  <c:forEach items="${placesAll}" var="placeA">
                    <li><input type="checkbox" name="places" data-labelauty="${placeA.functionName}" value="${placeA.functionIdS}${":"}${"10"}"></li>
                  </c:forEach>
                </c:if>
              </ul>
              <li class="col-sm-2 alignR"><b>股道管理</b></li>
              <ul class="dowebok">
                <c:if test="${not empty tracks}">
                  <c:forEach items="${tracks}" var="track">
                    <li><input type="checkbox" checked = "checked" name="tracks" data-labelauty="${track.functionName}" value="${track.functionIdS}${":"}${"11"}"></li>
                  </c:forEach>
                  <c:forEach items="${tracksCut}" var="trackC">
                    <li><input type="checkbox" name="tracks" data-labelauty="${trackC.functionName}" value="${trackC.functionIdS}${":"}${"11"}"></li>
                  </c:forEach>
                </c:if>
                <c:if test="${empty tracks}">
                  <c:forEach items="${tracksAll}" var="trackA">
                    <li><input type="checkbox" name="tracks" data-labelauty="${trackA.functionName}" value="${trackA.functionIdS}${":"}${"11"}"></li>
                  </c:forEach>
                </c:if>
              </ul>
              <li class="col-sm-2 alignR"><b>区管理</b></li>
              <ul class="dowebok">
                <c:if test="${not empty regions}">
                  <c:forEach items="${regions}" var="region">
                    <li><input type="checkbox" checked = "checked" name="regions" data-labelauty="${region.functionName}" value="${region.functionIdS}${":"}${"12"}"></li>
                  </c:forEach>
                  <c:forEach items="${regionsCut}" var="regionC">
                    <li><input type="checkbox" name="regions" data-labelauty="${regionC.functionName}" value="${regionC.functionIdS}${":"}${"12"}"></li>
                  </c:forEach>
                </c:if>
                <c:if test="${empty regions}">
                  <c:forEach items="${regionsAll}" var="regionA">
                    <li><input type="checkbox" name="regions" data-labelauty="${regionA.functionName}" value="${regionA.functionIdS}${":"}${"12"}"></li>
                  </c:forEach>
                </c:if>
              </ul>
                <li class="col-sm-2 alignR"><b>故障类型管理</b></li>
                <ul class="dowebok">
                  <c:if test="${not empty faultTypes}">
                    <c:forEach items="${faultTypes}" var="faultType">
                      <li><input type="checkbox" checked = "checked" name="faultTypes" data-labelauty="${faultType.functionName}" value="${faultType.functionIdS}${":"}${"16"}"></li>
                    </c:forEach>
                    <c:forEach items="${faultTypesCut}" var="faultTypesC">
                      <li><input type="checkbox" name="faultTypes" data-labelauty="${faultTypesC.functionName}" value="${faultTypesC.functionIdS}${":"}${"16"}"></li>
                    </c:forEach>
                  </c:if>
                  <c:if test="${empty faultTypes}">
                    <c:forEach items="${faultTypesAll}" var="faultTypesA">
                      <li><input type="checkbox" name="faultTypes" data-labelauty="${faultTypesA.functionName}" value="${faultTypesA.functionIdS}${":"}${"16"}"></li>
                    </c:forEach>
                  </c:if>
                </ul>
              <li class="col-sm-2 alignR"><b>巡检查询</b></li>
              <ul class="dowebok">
                <c:if test="${not empty selectInspections}">
                  <c:forEach items="${selectInspections}" var="selectInspection">
                    <li><input type="checkbox" checked = "checked" name="selectInspections" data-labelauty="${selectInspection.functionName}" value="${selectInspection.functionIdS}${":"}${"13"}"></li>
                  </c:forEach>
                  <c:forEach items="${selectInspectionsCut}" var="selectInspectionsC">
                    <li><input type="checkbox" name="selectInspections" data-labelauty="${selectInspectionsC.functionName}" value="${selectInspectionsC.functionIdS}${":"}${"13"}"></li>
                  </c:forEach>
                </c:if>
                <c:if test="${empty selectInspections}">
                  <c:forEach items="${selectInspectionsAll}" var="selectInspectionsA">
                    <li><input type="checkbox" name="selectInspections" data-labelauty="${selectInspectionsA.functionName}" value="${selectInspectionsA.functionIdS}${":"}${"13"}"></li>
                  </c:forEach>
                </c:if>
              </ul>
              <li class="col-sm-2 alignR"><b>防护查询</b></li>
              <ul class="dowebok">
                <c:if test="${not empty selectProtections}">
                  <c:forEach items="${selectProtections}" var="selectProtection">
                    <li><input type="checkbox" checked = "checked" name="selectProtections" data-labelauty="${selectProtection.functionName}" value="${selectProtection.functionIdS}${":"}${"14"}"></li>
                  </c:forEach>
                  <c:forEach items="${selectProtectionsCut}" var="selectProtectionsC">
                    <li><input type="checkbox" name="selectProtections" data-labelauty="${selectProtectionsC.functionName}" value="${selectProtectionsC.functionIdS}${":"}${"14"}"></li>
                  </c:forEach>
                </c:if>
                <c:if test="${empty selectProtections}">
                  <c:forEach items="${selectProtectionsAll}" var="selectProtectionsA">
                    <li><input type="checkbox" name="selectProtections" data-labelauty="${selectProtectionsA.functionName}" value="${selectProtectionsA.functionIdS}${":"}${"14"}"></li>
                  </c:forEach>
                </c:if>
              </ul>
              <li class="col-sm-2 alignR"><b>维修查询</b></li>
              <ul class="dowebok">
                <c:if test="${not empty maintainSearchs}">
                  <c:forEach items="${maintainSearchs}" var="maintainSearch">
                    <li><input type="checkbox" checked = "checked" name="maintainSearchs" data-labelauty="${maintainSearch.functionName}" value="${maintainSearch.functionIdS}${":"}${"15"}"></li>
                  </c:forEach>
                  <c:forEach items="${maintainSearchsCut}" var="maintainSearchsC">
                    <li><input type="checkbox" name="maintainSearchs" data-labelauty="${maintainSearchsC.functionName}" value="${maintainSearchsC.functionIdS}${":"}${"15"}"></li>
                  </c:forEach>
                </c:if>
                <c:if test="${empty maintainSearchs}">
                  <c:forEach items="${maintainSearchsAll}" var="maintainSearchsA">
                    <li><input type="checkbox" name="maintainSearchs" data-labelauty="${maintainSearchsA.functionName}" value="${maintainSearchsA.functionIdS}${":"}${"15"}"></li>
                  </c:forEach>
                </c:if>
              </ul>
              <li class="col-sm-2 alignR"><b>值班员工作台</b></li>
              <ul class="dowebok">
                <c:if test="${not empty maintains}">
                  <c:forEach items="${maintains}" var="maintain">
                    <li><input type="checkbox" checked = "checked" name="maintains" data-labelauty="${maintain.functionName}" value="${maintain.functionIdS}${":"}${"30"}"></li>
                  </c:forEach>
                  <c:forEach items="${maintainsCut}" var="maintainC">
                    <li><input type="checkbox" name="maintains" data-labelauty="${maintainC.functionName}" value="${maintainC.functionIdS}${":"}${"30"}"></li>
                  </c:forEach>
                </c:if>
                <c:if test="${empty maintains}">
                  <c:forEach items="${maintainsAll}" var="maintainA">
                    <li><input type="checkbox" name="maintains" data-labelauty="${maintainA.functionName}" value="${maintainA.functionIdS}${":"}${"30"}"></li>
                  </c:forEach>
                </c:if>
              </ul>
            </ul>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-4">
              <button type="submit" class="btn btn-info col-sm-5">保存</button>
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
<script>
  $(function(){
    $(':input').labelauty();
  });
</script>

<div style="text-align:center;clear:both;">
  <script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>
  <script src="/follow.js" type="text/javascript"></script>
</div>

<%@ include file="/WEB-INF/include/footer.jsp"%>