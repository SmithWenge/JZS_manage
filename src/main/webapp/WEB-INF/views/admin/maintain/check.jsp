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
              <label for="protectProject" class="col-sm-2 control-label">防护项目:</label>
              <div class="col-sm-10">
                <td id="protectProject" class="form-control">${maintain.protectProject}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label class="col-sm-2 control-label">场:</label>
              <div class="col-sm-10">
                <tags:dictd groupValue="place" itemKey="${maintain.place}" />
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label  class="col-sm-2 control-label">股道:</label>
              <div class="col-sm-10">
                <tags:dictd groupValue="track" itemKey="${maintain.track}" />
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label class="col-sm-2 control-label">区:</label>
              <div class="col-sm-10">
                <tags:dictd groupValue="region" itemKey="${maintain.region}" />
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="seat" class="col-sm-2 control-label">位置:</label>
              <div class="col-sm-10">
                <td class="form-control" id="seat">${maintain.seat}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label class="col-sm-2 control-label">防护状态:</label>
              <div class="col-sm-10">
                <tags:dictd groupValue="protectState" itemKey="${maintain.protectState}" />
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label class="col-sm-2 control-label">故障状态:</label>
              <div class="col-sm-10">
                <tags:dictd groupValue="faultState" itemKey="${maintain.faultState}" />
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label class="col-sm-2 control-label">故障类型:</label>
              <div class="col-sm-10">
                <tags:dictd groupValue="faultType" itemKey="${maintain.faultType}" />
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="protectApprovePeople" class="col-sm-2 control-label">防护批准人:</label>
              <div class="col-sm-10">
                <td class="form-control" id="protectApprovePeople">${maintain.protectApprovePeople}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="protectPeople" class="col-sm-2 control-label">驼峰值班员:</label>
              <div class="col-sm-10">
                <td class="form-control" id="protectPeople">${maintain.protectPeople}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="protectRequestPeople" class="col-sm-2 control-label">防护请求人:</label>
              <div class="col-sm-10">
                <td class="form-control" id="protectRequestPeople">${maintain.protectRequestPeople}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="faultFindPeople" class="col-sm-2 control-label">故障发现人:</label>
              <div class="col-sm-10">
                <td class="form-control" id="faultFindPeople">${maintain.faultFindPeople}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="faultDealPeople" class="col-sm-2 control-label">故障处理人:</label>
              <div class="col-sm-10">
                <td class="form-control" id="faultDealPeople">${maintain.faultDealPeople}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="protectDay" class="col-sm-2 control-label">防护日期:</label>
              <div class="col-sm-10">
                <td class="form-control" id="protectDay">${maintain.protectDay}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="faultDay" class="col-sm-2 control-label">故障日期:</label>
              <div class="col-sm-10">
                <td class="form-control" id="faultDay">${maintain.faultDay}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="protectApproveTime" class="col-sm-2 control-label">防护登记时间:</label>
              <div class="col-sm-10">
                <td class="form-control" id="protectApproveTime">${maintain.protectApproveTime}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="registerTime" class="col-sm-2 control-label">故障登记时间:</label>
              <div class="col-sm-10">
                <td class="form-control" id="registerTime">${maintain.registerTime}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="protectStopTime" class="col-sm-2 control-label">防护取消时间:</label>
              <div class="col-sm-10">
                <td class="form-control" id="protectStopTime">${maintain.protectStopTime}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="pinTime" class="col-sm-2 control-label">故障销记时间:</label>
              <div class="col-sm-10">
                <td class="form-control" id="pinTime">${maintain.pinTime}</td>
              </div>
            </div>
          </div>
          <div class="col-md-6 form-group">
            <div class="form-group">
              <label for="protectRemark" class="col-sm-2 control-label">防护备注:</label>
              <div class="col-sm-10">
                <td class="form-control" id="protectRemark">${maintain.protectRemark}</td>
              </div>
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

