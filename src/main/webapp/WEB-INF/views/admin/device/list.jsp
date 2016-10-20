<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/navs.jsp"%>
<div class="panel panel-default" style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;padding-top: 5px;">
        <ul class="nav nav-pills">
            <li role="presentation" ><a href="${contextPath}/admin/device/routeList.action"><span class="glyphicon glyphicon-map-marker"></span>顶位管理</a></li>
            <c:forEach items="${sessionScope.functions}" var="function">
                <c:if test="${function.functionId == 16}">
                        <li role="presentation"class="active" style="float: right"><a href="${contextPath}/admin/device/routeImport.action">excel导入设备</a></li>
                </c:if>
            </c:forEach>
            <c:forEach items="${sessionScope.functions}" var="function">
                <c:if test="${function.functionId == 15}">
                    <li role="presentation"class="active" style="float: right"><a href="${contextPath}/admin/device/routeAdd.action">添加设备</a></li>
                </c:if>
            </c:forEach>
        </ul>
    </div>
    <div class="panel-body">
        <c:if test="${not empty editMessage}">
            <div class="col-md-12" id="message">
                <p class="bg-success">${editMessage}</p>
            </div>
        </c:if>
        <c:if test="${not empty addMessage}">
            <div class="col-md-12" id="message">
                <p class="bg-success">${addMessage}</p>
            </div>
        </c:if>
        <c:if test="${not empty cancleMessage}">
            <div class="col-md-12" id="message">
                <p class="bg-success">${cancleMessage}</p>
            </div>
        </c:if>
        <c:if test="${not empty cancleRepiMessage}">
            <div class="col-md-12" id="message">
                <p class="bg-success">${cancleRepiMessage}</p>
            </div>
        </c:if>
        <c:if test="${not empty cancleFailureMessage}">
            <div class="col-md-12" id="message">
                <p class="bg-success">${cancleFailureMessage}</p>
            </div>
        </c:if>
        <c:forEach items="${sessionScope.functions}" var="function">
            <c:if test="${function.functionId == 14}">
                <li role="presentation" >
                    <form class="form-inline" action="${contextPath}/admin/device/pageSearch.action" method="post">
                        <div class="form-group">
                            <label for="placeNum">场</label>
                            <select class="form-control" id="placeNum" name="placeNum">
                                <option value="0">全部</option>
                                <c:forEach items="${sessionScope.places}" var="place">
                                    <option value="${place.placeId}">${place.placeName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="trackNum">道</label>
                            <select class="form-control" id="trackNum" name="trackNum">
                                <option value="0">全部</option>
                                <c:forEach items="${sessionScope.tracks}" var="track">
                                    <option value="${track.trackId}">${track.trackName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="regionNum">区</label>
                            <select class="form-control" id="regionNum" name="regionNum">
                                <option value="0">全部</option>
                                <c:forEach items="${sessionScope.regions}" var="region">
                                    <option value="${region.regionId}">${region.regionName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="cancelStateNum">撤销状态</label>
                            <tags:dicselect name="cancelStateNum" key="cancelState" value="0" id="cancelStateNum" />
                        </div>
                        <div class="form-group">
                            <label for="deviceState">减速顶状态</label>
                            <select class="form-control" id="deviceState" name="deviceState">
                                <option value="0">全部</option>
                                <option value="1">正常</option>
                                <option value="2">预警</option>
                                <option value="3">故障</option>
                                <option value="4">其他</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="startDate" class="control-label">安装时间</label>
                            <input type="date" class="form-control" id="startDate" name="startDate" style="width:120px">
                        </div>
                        <div class="form-group">
                            <label for="stopDate" class="control-label">至</label>
                            <input type="date" class="form-control" id="stopDate" name="stopDate" style="width:120px">
                        </div>
                        <button type="submit" class="btn btn-default">检索</button>
                    </form>
                </li>
            </c:if>
        </c:forEach>
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable" align="center">
                    <tr style="background-color: #3767b1; color: #dbdbdb;">
                        <th>序号</th>
                        <th>顶位编号</th>
                        <th>场</th>
                        <th>股道</th>
                        <th>区</th>
                        <th>位置</th>
                        <th>顶状态</th>
                        <th>安装时间</th>
                        <th>撤销时间</th>
                        <th>碾压次数</th>
                        <th>撤销状态</th>
                        <th>最近维修时间</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${page.content}" var="device" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${device.deviceNumber}</td>
                            <td>${device.place}</td>
                            <td>${device.track}</td>
                            <td>${device.region}</td>
                            <td>${device.seat}</td>
                            <tags:dictd groupValue="deviceState" itemKey="${device.deviceState}" />
                            <td>${device.deviceTimeDate}</td>
                            <c:if test="${empty device.cancelTimeDate }">
                                <td>无撤销时间</td>
                            </c:if>
                            <c:if test="${!empty device.cancelTimeDate }">
                                <td>${device.cancelTimeDate}</td>
                            </c:if>
                            <td>${device.rollingTimes}</td>
                            <tags:dictd groupValue="cancelState" itemKey="${device.cancelStateNum}" />
                            <td>${device.latestTime}</td>
                            <td>
                            <c:forEach items="${sessionScope.functions}" var="function">
                                <c:if test="${function.functionId == 17}">
                                    <a href="${contextPath}/admin/device/routeEdit/${device.deviceId}.action" style="text-decoration: none;">
                                        <button type="button" class="btn btn-warning" style="width:50px">编辑</button>
                                    </a>
                                </c:if>
                            </c:forEach>
                            <c:forEach items="${sessionScope.functions}" var="function">
                                <c:if test="${function.functionId == 18}">
                                    <a href="${contextPath}/admin/device/check/${device.deviceId}.action" style="text-decoration: none;" >
                                        <button type="button" class="btn btn-warning" style="width:50px">详情</button>
                                    </a>
                                </c:if>
                            </c:forEach>
                            <c:forEach items="${sessionScope.functions}" var="function">
                                <c:if test="${function.functionId == 19}">
                                    <a href="${contextPath}/admin/device/cancle/${device.deviceId}.action" style="text-decoration: none;" >
                                        <button type="button" class="btn btn-danger" style="width:50px">撤销</button>
                                    </a>
                                </c:if>
                            </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        <div class="row" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
            <div class="col-md-12">
                <nav>
                    <ul class="pager">
                        <c:if test="${page.number > 0 }">
                            <li class="previous">
                                <a href="${contextPath}/admin/device/page.action?page=${page.number - 1}"><span aria-hidden="true">&larr;</span> 上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number <= 0 }">
                            <li class="previous disabled">
                                <a href="#"><span aria-hidden="true">&larr;</span>上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number + 1 < page.totalPages }">
                            <li class="next">
                                <a href="${contextPath}/admin/device/page.action?page=${page.number + 1}">下一页 <span aria-hidden="true">&rarr;</span></a>
                            </li>
                        </c:if>
                        <c:if test="${page.number + 1 >= page.totalPages }">
                            <li class="next disabled">
                                <a href="#">下一页 <span aria-hidden="true">&rarr;</span></a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>



<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@ include file="/WEB-INF/include/footer.jsp"%>
