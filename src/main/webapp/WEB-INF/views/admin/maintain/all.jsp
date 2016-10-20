<%@ page language="java" contentType="text/html; charset=UTF-8"
                                   pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@include file="/WEB-INF/include/navs.jsp"%>
    <div class="panel panel-default" style="float: left;width: 85%;">
      <div class="panel-heading" style="height: 45px;padding-top: 5px;">
        <ul class="nav nav-pills">
          <li role="presentation" ><a href="${contextPath}/admin/maintain/routeAll.action"><span class="glyphicon glyphicon-map-marker"></span>工单查询</a></li>
        </ul>
      </div>
      <div class="panel-body">
  <c:forEach items="${sessionScope.functions}" var="function">
    <c:if test="${function.functionId == 26}">
      <form class="form-inline" action="${contextPath}/admin/maintain/AllSearch.action" method="post">
        <div class="row">
          <div class="col-md-4 form-group">
            <label for="place" class="col-md-4 control-label">场</label>
            <div class="col-md-8">
              <tags:dicselect name="place" key="place" value="0" id="place" />
            </div>
          </div>
          <div class="col-md-4 form-group">
            <label for="track" class="col-md-4 control-label">道</label>
            <div class="col-md-8">
              <tags:dicselect name="track" key="track" value="0" id="track" />
            </div>
          </div>
          <div class="col-md-4 form-group">
            <label for="region" class="col-md-4 control-label">区</label>
            <div class="col-md-8">
              <tags:dicselect name="region" key="region" value="0" id="region" />
            </div>
          </div>
          <div class="col-md-4 form-group">
            <label for="faultType" class="col-md-4 control-label">故障类型</label>
            <div class="col-md-8">
              <tags:dicselect name="faultType" key="faultType" value="0" id="faultType" />
            </div>
          </div>
          <div class="col-md-4 form-group">
            <label for="faultState" class="col-md-4 control-label">故障状态</label>
            <div class="col-md-8">
              <tags:dicselect name="faultState" key="faultState" value="0" id="faultState" />
            </div>
          </div>
          <div class="col-md-4 form-group">
            <label for="protectState" class="col-md-4 control-label">防护状态</label>
            <div class="col-md-8">
              <tags:dicselect name="protectState" key="protectState" value="0" id="protectState" />
            </div>
          </div>
          <div class="col-md-4 form-group">
            <label for="faultDay" class="col-md-4 control-label">故障日期</label>
            <div class="col-md-8">
              <input type="date" class="form-control" id="faultDay" name="faultDay">
            </div>
          </div>
          <div class="col-md-4 form-group">
            <label for="protectDay" class="col-md-4 control-label">防护日期</label>
            <div class="col-md-8">
              <input type="date" class="form-control" id="protectDay" name="protectDay">
            </div>
          </div>
          <div class="col-md-4 form-group">
            <button type="submit" class="btn btn-danger" style="margin-left: 50px">检索</button>
          </div>
        </div>
      </form>
    </c:if>
  </c:forEach>
    <div class="row" style="margin-top: 5px;">
      <div class="col-md-12">
        <table class="table" id="paginationTable" align="center">
          <tr style="background-color: #3767b1; color: #dbdbdb;">
            <th>序号</th>
            <th>防护项目</th>
            <th>场</th>
            <th>股道</th>
            <th>区</th>
            <th>位置</th>
            <th>故障类型</th>
            <th>防护状态</th>
            <th>故障状态</th>
            <th>防护日期</th>
            <th>故障日期</th>
            <th>操作</th>
          </tr>
          <c:forEach items="${page.content}" var="maintain" varStatus="status">
            <tr>
              <td>${status.index + 1}</td>
              <td>${maintain.protectProject}</td>
              <tags:dictd groupValue="place" itemKey="${maintain.place}" />
              <tags:dictd groupValue="track" itemKey="${maintain.track}" />
              <tags:dictd groupValue="region" itemKey="${maintain.region}" />
              <td>${maintain.seat}</td>
              <tags:dictd groupValue="faultType" itemKey="${maintain.faultType}" />
              <tags:dictd groupValue="protectState" itemKey="${maintain.protectState}" />
              <tags:dictd groupValue="faultState" itemKey="${maintain.faultState}" />
              <td>${maintain.protectDay}</td>
              <td>${maintain.faultDay}</td>
              <td>
              <c:forEach items="${sessionScope.functions}" var="function">
                <c:if test="${function.functionId == 27}">
                  <a href="${contextPath}/admin/maintain/check/${maintain.faultRegisterId}.action" style="text-decoration: none;">
                    <button type="button" class="btn btn-danger">查看详情</button>
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
                <a href="${contextPath}/admin/maintain/allPage.action?page=${page.number - 1}"><span aria-hidden="true">&larr;</span> 上一页</a>
              </li>
            </c:if>
            <c:if test="${page.number <= 0 }">
              <li class="previous disabled">
                <a href="#"><span aria-hidden="true">&larr;</span>上一页</a>
              </li>
            </c:if>
            <c:if test="${page.number + 1 < page.totalPages }">
              <li class="next">
                <a href="${contextPath}/admin/maintain/allPage.action?page=${page.number + 1}">下一页 <span aria-hidden="true">&rarr;</span></a>
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

<%@include file="/WEB-INF/include/footer.jsp"%>