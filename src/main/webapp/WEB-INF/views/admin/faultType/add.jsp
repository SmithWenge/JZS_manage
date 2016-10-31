<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/navs.jsp"%>
<div class="panel panel-default" style="float: left;width: 85%;">
  <div class="panel-heading" style="height: 45px;padding-top: 5px;"><span class="glyphicon glyphicon-map-marker"></span>故障类型管理 > 故障类型添加</div>
  <div class="panel-body">
    <div class="row" style="margin-top: 5px;">
      <c:if test="${not empty addFailureMessage}">
        <div class="col-md-12" id="message">
          <p class="bg-danger">${addFailureMessage}</p>
        </div>
      </c:if>
      <div class="col-md-12">
        <form class="form-horizontal" action="${contextPath}/admin/faultType/add.action" method="post" id="addForm">
          <div class="form-group">
            <label for="faultTypeName" class="col-sm-3 control-label">故障名</label>
            <div class="col-sm-9">
              <input type="text" class="form-control" id="faultTypeName" name="faultTypeName">
            </div>
          </div>
          <div class="form-group">
            <label for="faultSugestion" class="col-sm-3 control-label">处理办法建议</label>
            <div class="col-sm-9">
              <input type="text" class="form-control" id="faultSugestion" name="faultSugestion">
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-3 col-sm-9">
              <button type="submit" class="btn btn-default">添加</button>
              <button type="button" id="backMark" class="btn btn-default" style="margin-left: 200px">返回</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<%@ include file="/WEB-INF/include/footer.jsp"%>