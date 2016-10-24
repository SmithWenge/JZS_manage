
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
        <div class="row" style="margin-top: 5px;">
            <a href="${contextPath}/admin/device/routeList.action" style="color: #AA0000"><h3 style="color: #CC0000">&nbsp;&nbsp;预警减速顶数量：${waringNum}个</h3></a>
            <a href="${contextPath}/admin/maintain/routeMaintainIndex.action" style="color: #AA0000"><h3 style="color: #CC0000">&nbsp;&nbsp;待处理故障数量：${num}个</h3></a>
            <h3>&nbsp;&nbsp;在岗员工列表：</h3>
            <div class="col-md-12">
                <table class="table" id="paginationTable" align="center">
                    <tr style="background-color: #3767b1; color: #dbdbdb;">
                        <th>序号</th>
                        <th>工人姓名</th>
                        <th>工人性别</th>
                        <th>岗位</th>
                        <th>电话</th>
                    </tr>
                    <c:forEach items="${page}" var="worker" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${worker.userName}</td>
                            <tags:dicgentd groupValue="adminGender" itemKey="${worker.userGender}" />
                            <tags:dictd groupValue="userPostForAttend" itemKey="${worker.userPost}" />
                            <td>${worker.userTelOne}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        <c:if test="${empty page}">
            <a href="${contextPath}/admin/workerInfo/routeAdd.action" style="color: #AA0000"><h3 style="color: #CC0000">&nbsp;&nbsp;请确定是否导入在岗人员,点击进行上岗人员导入</h3></a>
        </c:if>
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
