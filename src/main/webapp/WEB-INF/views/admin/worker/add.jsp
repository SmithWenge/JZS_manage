<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/navs.jsp"%>
<div class="panel panel-default" style="float: left;width: 85%;">
  <div class="panel-heading" style="height: 45px;padding-top: 5px;"><span class="glyphicon glyphicon-map-marker"></span>工人管理 > 工人添加</div>
  <div class="panel-body">
    <div class="row" style="margin-top: 5px;">
      <c:if test="${not empty addFailureMessage}">
        <div class="col-md-12" id="message">
          <p class="bg-danger">${addFailureMessage}</p>
        </div>
      </c:if>
      <div class="col-md-12">
        <form class="form-horizontal" action="${contextPath}/admin/worker/add.action" method="post" id="addForm">
          <div class="form-group">
            <label for="userName" class="col-sm-2 control-label">工人姓名</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="userName" placeholder="张三" name="userName">
            </div>
          </div>
          <div class="form-group">
            <label for="userGender" class="col-sm-2 control-label">选择性别</label>
            <div class="col-sm-10">
              <tags:dicselect name="userGender" key="adminGender" value="1" id="userGender" />
            </div>
          </div>
          <div class="form-group">
            <label for="userPost" class="col-sm-2 control-label">选择岗位</label>
            <div class="col-sm-10">
              <select class="form-control" id="userPost" name="userPost">
                <option selected="selected" value="1" >驼峰防护员</option>
                <option selected="selected" value="5" >工人</option>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label for="userTelOne" class="col-sm-2 control-label">电话1</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="userTelOne" name="userTelOne">
            </div>
          </div>
          <div class="form-group">
            <label for="userTelTwo" class="col-sm-2 control-label">电话2</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="userTelTwo" placeholder="可以为空" name="userTelTwo">
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <button type="submit" class="btn btn-default">添加工人</button>
              <button type="button" id="backMark" class="btn btn-default" style="margin-left: 200px">返回</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
  $(function () {
    $('#addForm').validate({
      rules: {
        userName: {
          required: true
        },
        userTelOne: {
          required: true,
          minlength: 11,
          maxlength: 11
        }
      },
      messages: {
        userName: {
          required: "请填写工人姓名"
        },
        userTelOne: {
          required: "请填写工人电话",
          minlength: "电话号码为11位",
          maxlength: "电话号码为11位"
        }
      }
    });
  });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>