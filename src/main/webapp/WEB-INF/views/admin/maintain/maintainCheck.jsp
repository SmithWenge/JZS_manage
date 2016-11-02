<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/navs.jsp"%>
<div class="panel panel-default" style="float: left;width: 85%;">
  <div class="panel-heading" style="height: 45px;padding-top: 5px;"><span class="glyphicon glyphicon-map-marker"></span>工单查询 > 查看详情</div>
  <div class="panel-body">
    <div class="row" style="margin-top: 5px;">
      <div class="col-md-12">
        <form class="form-horizontal" id="addForm">
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="placeName" class="col-sm-3 control-label">场:</label>
              <div class="col-sm-9">
                <td id="placeName" class="form-control">${maintain.placeName}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="trackName" class="col-sm-3 control-label">股道:</label>
              <div class="col-sm-9">
                <td id="trackName" class="form-control">${maintain.trackName}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="regionName" class="col-sm-3 control-label">区:</label>
              <div class="col-sm-9">
                <td id="regionName" class="form-control">${maintain.regionName}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="seat" class="col-sm-3 control-label">位置:</label>
              <div class="col-sm-9">
                <td class="form-control" id="seat">${maintain.seat}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label class="col-sm-3 control-label">故障状态:</label>
              <div class="col-sm-9">
                <tags:dictd groupValue="faultState" itemKey="${maintain.faultState}" />
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label class="col-sm-3 control-label">故障类型:</label>
              <div class="col-sm-9">
                <tags:dictd groupValue="faultType" itemKey="${maintain.faultType}" />
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="faultRegisterPeople" class="col-sm-3 control-label">故障登记人:</label>
              <div class="col-sm-9">
                <td class="form-control" id="faultRegisterPeople">${maintain.faultRegisterPeople}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="faultFindPeople" class="col-sm-3 control-label">故障发现人:</label>
              <div class="col-sm-9">
                <td class="form-control" id="faultFindPeople">${maintain.faultFindPeople}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="faultDealPeople" class="col-sm-3 control-label">故障处理人:</label>
              <div class="col-sm-9">
                <td class="form-control" id="faultDealPeople">${maintain.faultDealPeople}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label class="col-sm-3 control-label">处理结果:</label>
              <div class="col-sm-9">
                <tags:dictd groupValue="faultReason" itemKey="${maintain.faultReason}" />
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="registerTime" class="col-sm-3 control-label">故障登记时间:</label>
              <div class="col-sm-9">
                <td class="form-control" id="registerTime">${maintain.registerTime}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="pinTime" class="col-sm-3 control-label">故障销记时间:</label>
              <div class="col-sm-9">
                <td class="form-control" id="pinTime">${maintain.pinTime}</td>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-3 col-sm-9">
              <button type="button" id="backMark" class="btn btn-default" style="margin-left: 600px">返回</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<%@ include file="/WEB-INF/include/footer.jsp"%>

