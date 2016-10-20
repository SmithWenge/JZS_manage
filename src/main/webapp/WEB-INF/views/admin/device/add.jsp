<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/navs.jsp"%>
<div class="panel panel-default" style="float: left;width: 85%;">
  <div class="panel-heading" style="height: 45px;padding-top: 5px;"><span class="glyphicon glyphicon-map-marker"></span>顶位管理 > 添加顶位</div>
  <div class="panel-body">
    <div class="row" style="margin-top: 5px;">
      <c:if test="${not empty addFailureMessage}">
        <div class="col-md-12" id="message">
          <p class="bg-danger">${addFailureMessage}</p>
        </div>
      </c:if>
      <c:if test="${not empty addRepeatMessage}">
        <div class="col-md-12" id="message">
          <p class="bg-danger">${addRepeatMessage}</p>
        </div>
      </c:if>
      <div class="col-md-12">
        <form class="form-horizontal" action="${contextPath}/admin/device/add.action" method="post" id="addForm">
          <div class="form-group">
            <label for="placeNum" class="col-sm-2 control-label">场</label>
            <div class="col-sm-10">
              <select class="form-control" id="placeNum" name="placeNum">
                <c:forEach items="${sessionScope.places}" var="place">
                  <option value="${place.placeId}">${place.placeName}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label for="trackNum" class="col-sm-2 control-label">股道</label>
            <div class="col-sm-10">
              <select class="form-control" id="trackNum" name="trackNum">
                <c:forEach items="${sessionScope.tracks}" var="track">
                  <option value="${track.trackId}">${track.trackName}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label for="regionNum" class="col-sm-2 control-label">区</label>
            <div class="col-sm-10">
              <select class="form-control" id="regionNum" name="regionNum">
                <c:forEach items="${sessionScope.regions}" var="region">
                  <option value="${region.regionId}">${region.regionName}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label for="seat" class="col-sm-2 control-label">位置</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="seat" name="seat" placeholder="001">
            </div>
          </div>
          <div class="form-group">
            <label for="deviceState" class="col-sm-2 control-label">减速顶状态</label>
            <div class="col-sm-10">
              <tags:dicselect name="deviceState" key="deviceState" value="1" id="deviceState" />
            </div>
          </div>
          <div class="form-group">
            <label for="deviceTimeDate" class="col-md-2 control-label">安装时间</label>
            <div class="col-sm-10">
              <input type="date" class="form-control" id="deviceTimeDate" name="deviceTimeDate">
            </div>
          </div>
          <div class="form-group">
            <label for="rollingTimes" class="col-sm-2 control-label">碾压次数</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="rollingTimes" name="rollingTimes">
            </div>
          </div>
          <div class="form-group">
            <label for="deviceFactory" class="col-sm-2 control-label">生产厂家</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id
                      ="deviceFactory" name="deviceFactory" value="明城机械厂">
            </div>
          </div>
          <div class="form-group">
            <label for="deviceModel" class="col-sm-2 control-label">型号</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="deviceModel" name="deviceModel" value="TDW94">
            </div>
          </div>
          <div class="form-group">
            <label for="stationName" class="col-sm-2 control-label">所属车站</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="stationName" name="stationName" value="金州站">
            </div>
          </div>
          <div class="form-group">
            <label for="stationNum" class="col-sm-2 control-label">所属代码</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="stationNum" name="stationNum">
            </div>
          </div>
          <div class="form-group">
            <label for="remark" class="col-sm-2 control-label">备注</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="remark" name="remark">
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
