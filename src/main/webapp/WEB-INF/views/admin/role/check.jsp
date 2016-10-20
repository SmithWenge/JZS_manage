<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/navs.jsp"%>
<link rel="stylesheet" href="${contextPath}/static/css/jquery-labelauty.css">
<style>
  .dowebok li { display: inline-block;}
  .alignR{
    text-align: right;

  }
</style>
<div class="panel panel-default" style="float: left;width: 85%;">
  <div class="panel-heading" style="height: 45px;padding-top: 5px;"><span class="glyphicon glyphicon-map-marker"></span>角色管理 > 查看角色</div>
  <div class="panel-body">
    <div class="row" style="margin-top: 5px;">
      <div class="col-md-12">
        <form class="form-horizontal" id="addForm">
          <div class="form-group">
            <label for="roleName" class="col-sm-2 control-label">角色名</label>
            <div class="col-sm-10">
              <input type="button" class="btn btn-info" disabled="disabled" id="roleName" name="roleName" value="${role.roleName}">
            </div>
          </div>

          <div class="form-group" >
            <ul style="list-style: none;padding-left: 20px" class="roles">
              <%--<c:if test="${not empty workers}">--%>
                <%--<li class="col-sm-2 alignR"><b>工人管理</b></li>--%>
                <%--<ul class="dowebok">--%>
                  <%--<c:forEach items="${workers}" var="worker">--%>
                    <%--<li><input class="btn btn-default" type="button" disabled="disabled" name="workerCheckbox" value="${worker.functionName}"></li>--%>
                  <%--</c:forEach>--%>
                <%--</ul>--%>
              <%--</c:if>--%>
              <c:if test="${not empty adminUsers}">
                <li class="col-sm-2 alignR"><b>员工管理</b></li>
                <ul class="dowebok">
                  <c:forEach items="${adminUsers}" var="adminUser">
                    <li><input class="btn btn-default" type="button" disabled="disabled" name="adminUserCheckbox" value="${adminUser.functionName}"></li>
                  </c:forEach>
                </ul>
              </c:if>
              <c:if test="${not empty roles}">
                <li class="col-sm-2 alignR"><b>角色管理</b></li>
                <ul class="dowebok">
                  <c:forEach items="${roles}" var="role">
                    <li><input class="btn btn-default" type="button" disabled="disabled" name="roles" value="${role.functionName}"></li>
                  </c:forEach>
                </ul>
              </c:if>
              <c:if test="${not empty logs}">
                <li class="col-sm-2 alignR"><b>日志查询</b></li>
                <ul class="dowebok">
                  <c:forEach items="${logs}" var="log">
                    <li><input class="btn btn-default" type="button" disabled="disabled" name="logs" value="${log.functionName}"></li>
                  </c:forEach>
                </ul>
              </c:if>
              <c:if test="${not empty devices}">
                <li class="col-sm-2 alignR"><b>顶位管理</b></li>
                <ul class="dowebok">
                  <c:forEach items="${devices}" var="device">
                    <li><input class="btn btn-default" type="button" disabled="disabled" name="devices" value="${device.functionName}"></li>
                  </c:forEach>
                </ul>
              </c:if>
              <c:if test="${not empty workerInfos}">
                <li class="col-sm-2 alignR"><b>上岗登记</b></li>
                <ul class="dowebok">
                  <c:forEach items="${workerInfos}" var="workerInfo">
                    <li><input class="btn btn-default" type="button" disabled="disabled" name="workerInfos"value="${workerInfo.functionName}"></li>
                  </c:forEach>
                </ul>
              </c:if>
              <c:if test="${not empty selectInfos}">
                <li class="col-sm-2 alignR"><b>在岗查询</b></li>
                <ul class="dowebok">
                  <c:forEach items="${selectInfos}" var="selectInfo">
                    <li><input class="btn btn-default" type="button" disabled="disabled" name="selectInfos" value="${selectInfo.functionName}"></li>
                  </c:forEach>
                </ul>
              </c:if>
              <%--<c:if test="${not empty selectMaintains}">--%>
                <%--<li class="col-sm-2 alignR"><b>工单查询</b></li>--%>
                <%--<ul class="dowebok">--%>
                  <%--<c:forEach items="${selectMaintains}" var="selectMaintain">--%>
                    <%--<li><input class="btn btn-default" type="button" disabled="disabled" name="selectMaintains" value="${selectMaintain.functionName}"></li>--%>
                  <%--</c:forEach>--%>
                <%--</ul>--%>
              <%--</c:if>--%>
              <c:if test="${not empty maintains}">
                <li class="col-sm-2 alignR"><b>值班员工作台</b></li>
                <ul class="dowebok">
                  <c:forEach items="${maintains}" var="maintain">
                    <li><input class="btn btn-default" type="button" disabled="disabled" name="maintains" value="${maintain.functionName}"></li>
                  </c:forEach>
                </ul>
              </c:if>
                <c:if test="${not empty places}">
                  <li class="col-sm-2 alignR"><b>场管理</b></li>
                  <ul class="dowebok">
                    <c:forEach items="${places}" var="place">
                      <li><input class="btn btn-default" type="button" disabled="disabled" name="adminUserCheckbox" value="${place.functionName}"></li>
                    </c:forEach>
                  </ul>
                </c:if>
                <c:if test="${not empty tracks}">
                  <li class="col-sm-2 alignR"><b>股道管理</b></li>
                  <ul class="dowebok">
                    <c:forEach items="${tracks}" var="track">
                      <li><input class="btn btn-default" type="button" disabled="disabled" name="adminUserCheckbox" value="${track.functionName}"></li>
                    </c:forEach>
                  </ul>
                </c:if>
                <c:if test="${not empty regions}">
                  <li class="col-sm-2 alignR"><b>区管理</b></li>
                  <ul class="dowebok">
                    <c:forEach items="${regions}" var="region">
                      <li><input class="btn btn-default" type="button" disabled="disabled" name="adminUserCheckbox" value="${region.functionName}"></li>
                    </c:forEach>
                  </ul>
                </c:if>
                <c:if test="${not empty selectInspections}">
                  <li class="col-sm-2 alignR"><b>巡检查询</b></li>
                  <ul class="dowebok">
                    <c:forEach items="${selectInspections}" var="selectInspection">
                      <li><input class="btn btn-default" type="button" disabled="disabled" name="adminUserCheckbox" value="${selectInspection.functionName}"></li>
                    </c:forEach>
                  </ul>
                </c:if>
                <c:if test="${not empty selectProtections}">
                  <li class="col-sm-2 alignR"><b>防护查询</b></li>
                  <ul class="dowebok">
                    <c:forEach items="${selectProtections}" var="selectProtection">
                      <li><input class="btn btn-default" type="button" disabled="disabled" name="adminUserCheckbox" value="${selectProtection.functionName}"></li>
                    </c:forEach>
                  </ul>
                </c:if>
                <c:if test="${not empty maintainSearchs}">
                  <li class="col-sm-2 alignR"><b>维修查询</b></li>
                  <ul class="dowebok">
                    <c:forEach items="${maintainSearchs}" var="maintainSearch">
                      <li><input class="btn btn-default" type="button" disabled="disabled" name="adminUserCheckbox" value="${maintainSearch.functionName}"></li>
                    </c:forEach>
                  </ul>
                </c:if>
            </ul>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <button type="button" id="backMark" class="btn btn-default" style="margin-left: 600px">返回</button>
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