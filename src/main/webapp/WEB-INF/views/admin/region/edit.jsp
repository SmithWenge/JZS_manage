<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/navs.jsp"%>
<div class="panel panel-default" style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;padding-top: 5px;"><span class="glyphicon glyphicon-map-marker"></span>区管理 > 编辑区</div>
    <div class="panel-body">
        <div class="row" style="margin-top: 5px;">
            <c:if test="${not empty editFailureMessage}">
                <div class="col-md-12" id="message">
                    <p class="bg-danger">${editFailureMessage}</p>
                </div>
            </c:if>
            <div class="col-md-12">
                <form class="form-horizontal" action="${contextPath}/admin/region/edit.action" method="post" id="editForm">
                    <input type="hidden" name="regionId" value="${region.regionId}">
                    <div class="form-group">
                        <label for="regionName" class="col-sm-2 control-label">场名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="regionName" name="regionName" value="${region.regionName}">
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

<%@ include file="/WEB-INF/include/footer.jsp"%>