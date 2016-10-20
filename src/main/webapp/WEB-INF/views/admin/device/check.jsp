<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/navs.jsp"%>
<div class="panel panel-default" style="float: left;width: 85%;">
  <div class="panel-heading" style="height: 45px;padding-top: 5px;"><span class="glyphicon glyphicon-map-marker"></span>顶位管理 > 查看详情</div>
  <div class="panel-body">
    <div class="row" style="margin-top: 5px;">
      <div class="col-md-12">
        <form class="form-horizontal" id="addForm">
          <input type="hidden" name="deviceId" value="${device.deviceId}">
          <div class="form-group">
            <label for="deviceNumber" class="col-sm-2 control-label">顶位编号:</label>
            <div class="col-sm-10">
              <td id="deviceNumber" class="form-control">${device.deviceNumber}</td>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">场:</label>
            <div class="col-sm-10">
              <tags:dictd groupValue="place" itemKey="${device.placeNum}" />
            </div>
          </div>
          <div class="form-group">
            <label  class="col-sm-2 control-label">股道:</label>
            <div class="col-sm-10">
              <tags:dictd groupValue="track" itemKey="${device.trackNum}" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">区:</label>
            <div class="col-sm-10">
              <tags:dictd groupValue="region" itemKey="${device.regionNum}" />
            </div>
          </div>
          <div class="form-group">
            <label for="seat" class="col-sm-2 control-label">位置:</label>
            <div class="col-sm-10">
              <td class="form-control" id="seat">${device.seat}</td>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">减速顶状态:</label>
            <div class="col-sm-10">
              <tags:dictd groupValue="deviceState" itemKey="${device.deviceState}" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">撤销状态:</label>
            <div class="col-sm-10">
              <tags:dictd groupValue="cancelState" itemKey="${device.cancelStateNum}" />
            </div>
          </div>
          <div class="form-group">
            <label for="deviceTimeDate" class="col-md-2 control-label">安装时间:</label>
            <div class="col-sm-10">
              <td class="form-control" id="deviceTimeDate">${device.deviceTimeDate}</td>
            </div>
          </div>
          <div class="form-group">
            <label for="cancelTimeDate" class="col-md-2 control-label">撤销时间:</label>
            <div class="col-sm-10">
              <td class="form-control" id="cancelTimeDate">${device.cancelTimeDate}</td>
            </div>
          </div>
          <div class="form-group">
            <label for="rollingTimes" class="col-sm-2 control-label">碾压次数:</label>
            <div class="col-sm-10">
              <td class="form-control" id="rollingTimes">${device.rollingTimes}</td>
            </div>
          </div>
          <div class="form-group">
            <label for="deviceFactory" class="col-sm-2 control-label">生产厂家:</label>
            <div class="col-sm-10">
              <td class="form-control" id="deviceFactory">${device.deviceFactory}</td>
            </div>
          </div>
          <div class="form-group">
            <label for="deviceModel" class="col-sm-2 control-label">型号:</label>
            <div class="col-sm-10">
              <td class="form-control" id="deviceModel">${device.deviceModel}</td>
            </div>
          </div>
          <div class="form-group">
            <label for="stationName" class="col-sm-2 control-label">所属车站:</label>
            <div class="col-sm-10">
              <td class="form-control" id="stationName">${device.stationName}</td>
            </div>
          </div>
          <div class="form-group">
            <label for="stationNum" class="col-sm-2 control-label">车站代码:</label>
            <div class="col-sm-10">
              <td class="form-control" id="stationNum">${device.stationNum}</td>
            </div>
          </div>
          <div class="form-group">
            <label for="remark" class="col-sm-2 control-label">备注:</label>
            <div class="col-sm-10">
              <td class="form-control" id="remark">${device.remark}</td>
            </div>
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

<%@ include file="/WEB-INF/include/footer.jsp"%>
