<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/navs.jsp"%>
<div class="panel panel-default" style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;padding-top: 5px;">
        <ul class="nav nav-pills">
            <li role="presentation" ><a href="${contextPath}/admin/workerInfo/routeList.action"><span class="glyphicon glyphicon-map-marker"></span>查看今天在岗人员</a></li>
            <c:forEach items="${sessionScope.functions}" var="function">
                <c:if test="${function.functionId == 12}">
                <li role="presentation">
                    <form class="form-inline" action="${contextPath}/admin/workerInfo/routeList.action" method="post">
                        <div class="col-md-0 form-group">
                            <input type="date" class="form-control" id="attendanceDate" name="attendanceDate">
                        </div>
                        <button type="submit" class="btn btn-default">选择日期检索</button>
                    </form>
                </li>
                </c:if>
            </c:forEach>
        </ul>
    </div>

    <div class="panel-body">
        <c:if test="${not empty addMessage}">
            <div class="col-md-12" id="message">
                <p class="bg-success">${addMessage}</p>
            </div>
        </c:if>
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable" align="center">
                    <tr style="background-color: #3767b1; color: #dbdbdb;">
                        <th>序号</th>
                        <th>工人姓名</th>
                        <th>工人性别</th>
                        <th>岗位</th>
                        <th>电话</th>
                        <th>上岗时间</th>
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
