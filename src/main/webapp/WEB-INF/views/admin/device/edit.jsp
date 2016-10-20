<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/navs.jsp"%>
<div class="panel panel-default" style="float: left;width: 85%;">
  <div class="panel-heading" style="height: 45px;padding-top: 5px;"><span class="glyphicon glyphicon-map-marker"></span>顶位管理 > 顶位修改</div>
  <div class="panel-body">
    <div class="row" style="margin-top: 5px;">
      <c:if test="${not empty editFailureMessage}">
        <div class="col-md-12" id="message">
          <p class="bg-danger">${editFailureMessage}</p>
        </div>
      </c:if>
      <div class="col-md-12">
        <form class="form-horizontal" action="${contextPath}/admin/device/edit.action" method="post" id="addForm">
          <input type="hidden" name="deviceId" value="${device.deviceId}">
          <div class="form-group">
            <label for="deviceNumber" class="col-sm-2 control-label">顶位编号</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="deviceNumber" name="deviceNumber" value="${device.deviceNumber}">
            </div>
          </div>
          <div class="form-group">
            <label for="placeNum" class="col-sm-2 control-label">场</label>
            <div class="col-sm-10">
              <select class="form-control" id="placeNum" name="placeNum">
                <option value="${device.placeNum}">${device.place}</option>
                <c:forEach items="${sessionScope.places}" var="place">
                  <c:if test="${device.placeNum != place.placeId}">
                    <option value="${place.placeId}">${place.placeName}</option>
                  </c:if>
                </c:forEach>
                </select>
            </div>
          </div>
          <div class="form-group">
            <label for="trackNum" class="col-sm-2 control-label">股道</label>
            <div class="col-sm-10">
              <select class="form-control" id="trackNum" name="trackNum">
                <option value="${device.trackNum}">${device.track}</option>
                <c:forEach items="${sessionScope.tracks}" var="track">
                  <c:if test="${device.trackNum != track.trackId}">
                    <option value="${track.trackId}">${track.trackName}</option>
                  </c:if>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label for="regionNum" class="col-sm-2 control-label">区</label>
            <div class="col-sm-10">
              <select class="form-control" id="regionNum" name="regionNum">
                <option value="${device.regionNum}">${device.region}</option>
                <c:forEach items="${sessionScope.regions}" var="region">
                  <c:if test="${device.regionNum != region.regionId}">
                    <option value="${region.regionId}">${region.regionName}</option>
                  </c:if>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label for="seat" class="col-sm-2 control-label">位置</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="seat" name="seat" value="${device.seat}">
            </div>
          </div>
          <div class="form-group">
            <label for="deviceState" class="col-sm-2 control-label">减速顶状态</label>
            <div class="col-sm-10">
              <tags:dicselect name="deviceState" key="deviceState" value="${device.deviceState}" id="deviceState" />
            </div>
          </div>
          <div class="form-group">
            <label for="deviceTimeDate" class="col-md-2 control-label">安装时间</label>
            <div class="col-sm-10">
              <input type="date" class="form-control" id="deviceTimeDate" name="deviceTimeDate" value="${device.deviceTimeDate}">
            </div>
          </div>
          <div class="form-group">
            <label for="cancelTimeDate" class="col-md-2 control-label">撤销时间</label>
            <div class="col-sm-10">
              <input type="date" class="form-control" id="cancelTimeDate" name="cancelTimeDate" value="${device.cancelTimeDate}">
            </div>
          </div>
          <div class="form-group">
            <label for="rollingTimes" class="col-sm-2 control-label">碾压次数</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="rollingTimes" name="rollingTimes" value="${device.rollingTimes}">
            </div>
          </div>
          <div class="form-group">
            <label for="deviceFactory" class="col-sm-2 control-label">生产厂家</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="deviceFactory" name="deviceFactory" value="${device.deviceFactory}">
            </div>
          </div>
          <div class="form-group">
            <label for="deviceModel" class="col-sm-2 control-label">型号</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="deviceModel" name="deviceModel" value="${device.deviceModel}">
            </div>
          </div>
          <div class="form-group">
            <label for="stationName" class="col-sm-2 control-label">所属车站</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="stationName" name="stationName" value="${device.stationName}">
            </div>
          </div>
          <div class="form-group">
            <label for="stationNum" class="col-sm-2 control-label">所属代码</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="stationNum" name="stationNum" value="${device.stationNum}">
            </div>
          </div>
          <div class="form-group">
            <label for="remark" class="col-sm-2 control-label">备注</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="remark" name="remark" value="${device.remark}">
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <button type="submit" class="btn btn-default">保存</button>
              <button type="button" id="backMark" class="btn btn-default" style="margin-left: 200px">返回</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<%@ include file="/WEB-INF/include/footer.jsp"%>
