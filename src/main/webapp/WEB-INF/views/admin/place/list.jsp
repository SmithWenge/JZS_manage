<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/navs.jsp"%>
<div class="panel panel-default" style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;padding-top: 5px;">
        <ul class="nav nav-pills">
            <li role="presentation" ><a href="${contextPath}/admin/place/routeList.action"><span class="glyphicon glyphicon-map-marker"></span>场管理</a></li>
            <c:forEach items="${sessionScope.functions}" var="function">
                <c:if test="${function.functionId == 29}">
                    <li role="presentation"class="active" style="float: right"><a href="${contextPath}/admin/place/routeAdd.action">添加场</a></li>
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
        <c:if test="${not empty deleteMessage}">
            <div class="col-md-12" id="message">
                <p class="bg-success">${deleteMessage}</p>
            </div>
        </c:if>
        <c:if test="${not empty deleteFailureMessage}">
            <div class="col-md-12" id="message">
                <p class="bg-success">${deleteFailureMessage}</p>
            </div>
        </c:if>
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable" align="center">
                    <tr style="background-color: #3767b1; color: #dbdbdb;">
                        <th>序号</th>
                        <th>场名</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${page}" var="place" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${place.placeName}</td>
                            <td>
                            <c:forEach items="${sessionScope.functions}" var="function">
                                <c:if test="${function.functionId == 30}">
                                    <a href="${contextPath}/admin/place/routeEdit/${place.placeId}.action" style="text-decoration: none;">
                                        <button type="button" class="btn btn-warning">编辑</button>
                                    </a>
                                </c:if>
                            </c:forEach>
                            <c:forEach items="${sessionScope.functions}" var="function">
                                <c:if test="${function.functionId == 31}">
                                    <a href="${contextPath}/admin/place/cancle/${place.placeId}.action" style="text-decoration: none;" >
                                        <button type="button" class="btn btn-danger">撤销</button>
                                    </a>
                                </c:if>
                            </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@ include file="/WEB-INF/include/footer.jsp"%>
