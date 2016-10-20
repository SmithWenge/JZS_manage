<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/navs.jsp"%>
<div class="panel panel-default" style="float: left;width: 85%;">
  <div class="panel-heading" style="height: 45px;padding-top: 5px;"><span class="glyphicon glyphicon-map-marker"></span>股道管理 > 股道添加</div>
  <div class="panel-body">
    <div class="row" style="margin-top: 5px;">
      <c:if test="${not empty addFailureMessage}">
        <div class="col-md-12" id="message">
          <p class="bg-danger">${addFailureMessage}</p>
        </div>
      </c:if>
      <div class="col-md-12">
        <form class="form-horizontal" action="${contextPath}/admin/track/add.action" method="post" id="addForm">
          <div class="form-group">
            <label for="placeId" class="col-sm-2 control-label">场</label>
            <div class="col-sm-10">
              <select class="form-control" id="placeId" name="placeId">
                <c:forEach items="${sessionScope.places}" var="place">
                  <option value="${place.placeId}">${place.placeName}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label for="trackName" class="col-sm-2 control-label">股道</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="trackName" name="trackName">
            </div>
          </div>
          <div class="form-group">
            <label for="trackLength" class="col-sm-2 control-label">股道长度</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="trackLength" name="trackLength">
            </div>
          </div>
          <div class="form-group">
            <label for="carNumEle" class="col-sm-2 control-label">容车数(11m)</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="carNumEle" name="carNumEle">
            </div>
          </div>
          <div class="form-group">
            <label for="carNumFou" class="col-sm-2 control-label">容车数(14.3m)</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="carNumFou" name="carNumFou">
            </div>
          </div>
          <div class="form-group">
            <label for="useable" class="col-sm-2 control-label">股道用途</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="useable" name="useable">
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
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