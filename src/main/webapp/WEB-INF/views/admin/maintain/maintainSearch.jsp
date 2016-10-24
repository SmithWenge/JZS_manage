<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/WEB-INF/include/navs.jsp"%>
<div class="panel panel-default" style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;padding-top: 5px;">
        <ul class="nav nav-pills">
            <li role="presentation" ><a href="${contextPath}/admin/maintain/maintainPage.action"><span class="glyphicon glyphicon-map-marker"></span>维修查询 &nbsp;&nbsp;</a></li>
            <li role="presentation">
                <c:forEach items="${sessionScope.functions}" var="function">
                    <c:if test="${function.functionId == 25}">
                        <form class="form-inline" action="${contextPath}/admin/maintain/maintainSearch.action" method="post">
                            <div class="row">
                                <div class="form-group">
                                    <label for="place">场</label>
                                    <select class="form-control" id="place" name="place">
                                        <option value="0">全部</option>
                                        <c:forEach items="${sessionScope.places}" var="place">
                                            <option value="${place.placeId}">${place.placeName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="track">道</label>
                                    <select class="form-control" id="track" name="track">
                                        <option value="0">全部</option>
                                        <c:forEach items="${sessionScope.tracks}" var="track">
                                            <option value="${track.trackId}">${track.trackName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="region">区</label>
                                    <select class="form-control" id="region" name="region">
                                        <option value="0">全部</option>
                                        <c:forEach items="${sessionScope.regions}" var="region">
                                            <option value="${region.regionId}">${region.regionName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="seat">位置</label>
                                    <input type="text" class="form-control" id="seat" name="seat">
                                </div>
                                <div class="form-group">
                                    <label for="faultState">故障状态</label>
                                    <tags:dicselect name="faultState" key="faultState" value="0" id="faultState" />
                                </div>
                                <div class="form-group">
                                    <label for="faultDay">故障日期</label>
                                    <input type="date" class="form-control" id="faultDay" name="faultDay">
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-default">检索</button>
                                </div>
                            </div>
                        </form>
                    </c:if>
                </c:forEach>
            </li>
        </ul>
    </div>
    <div class="panel-body">
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable" align="center">
                    <tr style="background-color: #3767b1; color: #dbdbdb;">
                        <th>序号</th>
                        <th>场</th>
                        <th>股道</th>
                        <th>区</th>
                        <th>位置</th>
                        <th>故障类型</th>
                        <th>故障状态</th>
                        <th>登记时间</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${page.content}" var="maintain" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${maintain.placeName}</td>
                            <td>${maintain.trackName}</td>
                            <td>${maintain.regionName}</td>
                            <td>${maintain.seat}</td>
                            <tags:dictd groupValue="faultType" itemKey="${maintain.faultType}" />
                            <tags:dictd groupValue="faultState" itemKey="${maintain.faultState}" />
                            <td>${maintain.registerTime}</td>
                            <td>
                            <c:forEach items="${sessionScope.functions}" var="function">
                                <c:if test="${function.functionId == 40}">
                                    <a href="${contextPath}/admin/maintain/maintainCheck/${maintain.faultRegisterId}.action" style="text-decoration: none;">
                                        <button type="button" class="btn btn-warning">查看详情</button>
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
                                <a href="${contextPath}/admin/maintain/maintainPage.action?page=${page.number - 1}"><span aria-hidden="true">&larr;</span> 上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number <= 0 }">
                            <li class="previous disabled">
                                <a href="#"><span aria-hidden="true">&larr;</span>上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number + 1 < page.totalPages }">
                            <li class="next">
                                <a href="${contextPath}/admin/maintain/maintainPage.action?page=${page.number + 1}">下一页 <span aria-hidden="true">&rarr;</span></a>
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
