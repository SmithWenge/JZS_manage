<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/navs.jsp"%>
<div class="panel panel-default" style="float: left;width: 85%;">
  <div class="panel-heading" style="height: 45px;padding-top: 5px;">
    <ul class="nav nav-pills">
      <li role="presentation" ><a href="${contextPath}/admin/worker/routeList.action"><span class="glyphicon glyphicon-map-marker"></span>工人管理</a></li>
      <c:forEach items="${sessionScope.functions}" var="function">
        <c:if test="${function.functionId == 1}">
          <li role="presentation"class="active" style="float: right"><a href="${contextPath}/admin/worker/routeAdd.action">添加工人</a></li>
        </c:if>
      </c:forEach>
    </ul>
  </div>
  <div class="panel-body">
    <%--<div class="row" style="margin-top: 5px;">--%>
    <c:if test="${not empty addMessage}">
      <div class="col-md-12" id="message">
        <p class="bg-success">${addMessage}</p>
      </div>
    </c:if>
    <c:if test="${not empty editMessage}">
      <div class="col-md-12" id="message">
        <p class="bg-success">${editMessage}</p>
      </div>
    </c:if>
    <c:if test="${not empty deleteMessage}">
      <div class="col-md-12" id="message">
        <p class="bg-success">${deleteMessage}</p>
      </div>
    </c:if>
    <c:if test="${not empty promoteMessage}">
      <div class="col-md-12" id="message">
        <p class="bg-danger">${promoteMessage}</p>
      </div>
    </c:if>
    <c:if test="${not empty deleteFailureMessage}">
      <div class="col-md-12" id="message">
        <p class="bg-danger">${deleteFailureMessage}</p>
      </div>
    </c:if>
    <%--<div class="col-md-12">--%>
    <%--<a href="${contextPath}/admin/major/routeAdd.action" style="text-decoration: none;">--%>
    <%--<button type="button" class="btn btn-info" style="float: right;">添加</button>--%>
    <%--</a>--%>
    <%--</div>--%>
    <%--</div>--%>

    <div class="row" style="margin-top: 5px;">
      <div class="col-md-12">
        <table class="table" id="paginationTable" align="center">
          <tr style="background-color: #3767b1; color: #dbdbdb;">
            <th>序号</th>
            <th>工人姓名</th>
            <th>工人性别</th>
            <th>岗位</th>
            <th>电话1</th>
            <th>电话2</th>
            <th>操作</th>
          </tr>
          <c:forEach items="${page.content}" var="worker" varStatus="status">
            <tr>
              <td>${status.index + 1}</td>
              <td>${worker.userName}</td>
              <tags:dictd groupValue="adminGender" itemKey="${worker.userGender}" />
              <tags:dictd groupValue="userPostForAttend" itemKey="${worker.userPost}" />
              <td>${worker.userTelOne}</td>
              <td>${worker.userTelTwo}</td>
              <td>
              <c:forEach items="${sessionScope.functions}" var="function">
                <c:if test="${function.functionId == 2}">
                  <a href="${contextPath}/admin/worker/routeEdit/${worker.userId}.action" style="text-decoration: none;">
                    <button type="button" class="btn btn-warning">编辑</button>
                  </a>
                </c:if>
              </c:forEach>
              <c:forEach items="${sessionScope.functions}" var="function">
                <c:if test="${function.functionId == 3}">
                  <a href="${contextPath}/admin/worker/delete/${worker.userId}.action" style="text-decoration: none;" >
                    <button type="button" class="btn btn-danger">删除</button>
                  </a>
                </c:if>
              </c:forEach>
              <c:forEach items="${sessionScope.functions}" var="function">
                <c:if test="${function.functionId == 4}">
                  <a href="${contextPath}/admin/worker/routePromote/${worker.userId}.action" style="text-decoration: none;" >
                    <button type="button" class="btn btn-danger">升级为管理员</button>
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
                <a href="${contextPath}/admin/worker/page.action?page=${page.number - 1}"><span aria-hidden="true">&larr;</span> 上一页</a>
              </li>
            </c:if>
            <c:if test="${page.number <= 0 }">
              <li class="previous disabled">
                <a href="#"><span aria-hidden="true">&larr;</span>上一页</a>
              </li>
            </c:if>
            <c:if test="${page.number + 1 < page.totalPages }">
              <li class="next">
                <a href="${contextPath}/admin/worker/page.action?page=${page.number + 1}">下一页 <span aria-hidden="true">&rarr;</span></a>
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

<script type="text/javascript">
  $(function() {
    // 设置table表格中的行高
    var $height = $('#paginationTable td').height() + 'px';
    $('#paginationTable td').css('line-height', $height);
  });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>
