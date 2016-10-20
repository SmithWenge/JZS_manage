<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/navs.jsp"%>
<div class="panel panel-default" style="float: left;width: 85%;">
  <div class="panel-heading" style="height: 45px;padding-top: 5px;"><span class="glyphicon glyphicon-map-marker"></span>员工管理 > 员工修改</div>
  <div class="panel-body">
    <div class="row" style="margin-top: 5px;">
      <c:if test="${not empty editFailureMessage}">
        <div class="col-md-12" id="message">
          <p class="bg-danger">${editFailureMessage}</p>
        </div>
      </c:if>
      <div class="col-md-12">
        <form class="form-horizontal" action="${contextPath}/admin/adminManager/edit.action" method="post" id="addForm">
          <input type="hidden" name="userId" value="${adminManager.userId}">
          <div class="form-group">
            <button type="button" id="resetPass" class="btn btn-success" data-toggle="modal" data-target="#resetPassFrom" style="margin-left: 206px">重置密码</button>
          </div>
          <div class="form-group">
            <label for="userName" class="col-sm-2 control-label">用户姓名</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="userName" placeholder="张三" name="userName" value="${adminManager.userName}">
            </div>
          </div>
          <div class="form-group">
            <label for="userGender" class="col-sm-2 control-label">选择性别</label>
            <div class="col-sm-10">
              <tags:dicselect name="userGender" key="adminGender" value="${adminManager.userGender}" id="userGender" />
            </div>
          </div>
          <div class="form-group">
            <label for="roleId" class="col-sm-2 control-label">选择角色</label>
            <div class="col-sm-10">
              <select class="form-control" id="roleId" name="roleId">
                <c:forEach items="${roles}" var="role">
                  <c:if test="${role.roleId == adminManager.roleId}">
                    <option value="${adminManager.roleId}" selected>${adminManager.roleName}</option>
                  </c:if>
                  <c:if test="${role.roleId != adminManager.roleId}">
                    <option value="${role.roleId}">${role.roleName}</option>
                  </c:if>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label for="userPost" class="col-sm-2 control-label">选择岗位</label>
            <div class="col-sm-10">
              <tags:dicselect name="userPost" key="userPostForAttend" value="${adminManager.userPost}" id="userPost" />
            </div>
          </div>
          <div class="form-group">
            <label for="userLoginName" class="col-sm-2 control-label">用户登录名</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="userLoginName" name="userLoginName" value="${adminManager.userLoginName}">
            </div>
          </div>
          <div class="form-group">
            <label for="userTelOne" class="col-sm-2 control-label">电话</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="userTelOne" name="userTelOne" value="${adminManager.userTelOne}">
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <button type="submit" class="btn btn-default">保存</button>
              <button type="button" id="backMark" class="btn btn-default" style="margin-left: 200px">返回</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="resetPassFrom" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">密码重置</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" action="${contextPath}/admin/adminManager/resetPass.action" method="post">
          <input type="hidden" name="userId" value="${adminManager.userId}">
          <input type="hidden" name="userName" value="${adminManager.userName}">
          <div class="form-group">
            <label for="newUserLoginPass" class="col-sm-3 control-label">请输入新密码</label>
            <div class="col-sm-9">
              <input type="text" class="form-control" id="newUserLoginPass" name="newUserLoginPass">
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-10 col-sm-2">
              <button type="submit" class="btn btn-default">提交</button>
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
          required: "请填写用户姓名"
        },
        userTelOne: {
          required: "请填写用户电话",
          minlength: "电话号码为11位",
          maxlength: "电话号码为11位"
        }
      }
    });
  });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>
