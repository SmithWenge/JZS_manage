<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/WEB-INF/include/navs.jsp"%>
<div class="panel panel-default" style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;padding-top: 5px;">
        <ul class="nav nav-pills">
            <li role="presentation" ><a href="${contextPath}/admin/maintain/inspectionPage.action"><span class="glyphicon glyphicon-map-marker"></span>巡检查询 &nbsp;&nbsp;</a></li>
            <li role="presentation">
                <c:forEach items="${sessionScope.functions}" var="function">
                    <c:if test="${function.functionId == 38}">
                        <form class="form-inline" action="${contextPath}/admin/maintain/inspectionSearch.action" method="post">
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
                                    <label for="inspectionType">巡检类型</label>
                                    <select class="form-control" id="inspectionType" name="inspectionType">
                                        <option value="0">全部</option>
                                        <option value="1">日常巡检</option>
                                        <option value="2">春检</option>
                                        <option value="3">秋检</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="inspectionState">巡检状态</label>
                                    <select class="form-control" id="inspectionState" name="inspectionState">
                                        <option value="0">全部</option>
                                        <option value="1">巡检中</option>
                                        <option value="2">正常</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="inspectionDate">巡检日期</label>
                                    <input type="date" class="form-control" id="inspectionDate" name="inspectionDate">
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
                        <th>巡检场次</th>
                        <th>巡检类型</th>
                        <th>巡检状态</th>
                        <th>调车长</th>
                        <th>驻站联络员</th>
                        <th>巡检开始时间</th>
                        <th>巡检结束时间</th>
                    </tr>
                    <c:forEach items="${page.content}" var="maintain" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${maintain.placeName}</td>
                            <tags:dictd groupValue="inspectionType" itemKey="${maintain.inspectionType}" />
                            <tags:dictd groupValue="inspectionState" itemKey="${maintain.inspectionState}" />
                            <td>${maintain.diaochePeo}</td>
                            <td>${maintain.zhibanPeo}</td>
                            <td>${maintain.inspectionTime}</td>
                            <td>${maintain.inspectionStopTime}</td>
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
                                <a href="${contextPath}/admin/maintain/inspectionPage.action?page=${page.number - 1}"><span aria-hidden="true">&larr;</span> 上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number <= 0 }">
                            <li class="previous disabled">
                                <a href="#"><span aria-hidden="true">&larr;</span>上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number + 1 < page.totalPages }">
                            <li class="next">
                                <a href="${contextPath}/admin/maintain/inspectionPage.action?page=${page.number + 1}">下一页 <span aria-hidden="true">&rarr;</span></a>
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

<%@include file="/WEB-INF/include/footer.jsp"%>
