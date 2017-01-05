<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/navs.jsp"%>
<div class="panel panel-default" style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;padding-top: 5px;">
        <ul class="nav nav-pills">
            <li role="presentation" ><a href="${contextPath}/admin/home/index.action"><span class="glyphicon glyphicon-map-marker"></span>首页</a></li>
            <c:forEach items="${sessionScope.functions}" var="function">
                <c:if test="${function.functionId == 39}">
                    <c:if test="${state == 0}">
                        <a style="text-decoration: none;margin: 60px" id="insStarButton">
                            <button type="button" class="btn btn-warning" name="insStarButton" data-toggle="modal" data-target="#insStarFrom">开始巡检</button>
                        </a>
                    </c:if>
                    <c:if test="${state != 0}">
                        <a href="${contextPath}/admin/maintain/inspectionStop.action" style="text-decoration: none;margin: 60px">
                            <button type="button" class="btn btn-danger">结束巡检</button>
                        </a>
                    </c:if>
                </c:if>
            </c:forEach>
        </ul>
    </div>

    <div class="panel-body">
        <c:if test="${not empty exitInspectionMessage}">
            <div class="col-md-12" id="message">
                <p class="bg-success">${exitInspectionMessage}</p>
            </div>
        </c:if>
        <a href="${contextPath}/admin/device/routeList.action" style="color: #AA0000"><h3 style="color: #CC0000">&nbsp;&nbsp;预警减速顶数量：${waringNum}个</h3></a>
        <a href="${contextPath}/admin/maintain/routeMaintainIndex.action" style="color: #AA0000"><h3 style="color: #CC0000">&nbsp;&nbsp;待处理故障数量：${num}个</h3></a>
        <div class="row" style="margin-top: 5px;">
            <h3>&nbsp;&nbsp;在岗员工列表：</h3>
            <div class="col-md-12">
                <table class="table" id="paginationTable" align="center">
                    <tr style="background-color: #3767b1; color: #dbdbdb;">
                        <th>序号</th>
                        <th>工人姓名</th>
                        <th>工人性别</th>
                        <th>岗位</th>
                        <th>电话</th>
                        <th>日期</th>
                    </tr>
                    <c:forEach items="${page}" var="worker" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${worker.userName}</td>
                            <tags:dicgentd groupValue="adminGender" itemKey="${worker.userGender}" />
                            <tags:dictd groupValue="userPostForAttend" itemKey="${worker.userPost}" />
                            <td>${worker.userTelOne}</td>
                            <td>${worker.attendanceTime}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        <c:if test="${empty page}">
            <h3 style="color: #CC0000">&nbsp;&nbsp;下班或换岗请点击退出！</h3>
            <a href="${contextPath}/admin/workerInfo/routeAdd.action"><h3 style="color: #CC0000">&nbsp;&nbsp;如登陆后忘记进行工作人员登记，请点击此处。</h3></a>
        </c:if>
    </div>
</div>

<div class="modal fade" id="insStarFrom" tabindex="-1" role="dialog" aria-labelledby="myModalLabelThree">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabelThree">巡检登记</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="${contextPath}/admin/maintain/inspectionAdd.action" method="post" id="inspectionForm">
                    <div class="form-group">
                        <label for="place1" class="col-sm-3 control-label">场</label>
                        <div class="col-sm-9">
                            <select class="form-control" id="place1" name="place">
                                <c:forEach items="${sessionScope.places}" var="place">
                                    <option value="${place.placeId}">${place.placeName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inspectionType" class="col-sm-3 control-label">巡检类型</label>
                        <div class="col-sm-9">
                            <tags:dicselect name="inspectionType" key="inspectionType" value="1" id="inspectionType" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="zhibanPeo" class="col-sm-3 control-label">驻站联络员</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="zhibanPeo" name="zhibanPeo" value="${protectRequestPeople.userName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-2">
                            <button type="submit" class="btn btn-default">提交</button>
                        </div>
                        <div class="col-sm-offset-1 col-sm-6">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script type="text/javascript">
    $(function() {
        // 设置table表格中的行高
        var $height = $('#paginationTable td').height() + 'px';
        $('#paginationTable td').css('line-height', $height);
    });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>
