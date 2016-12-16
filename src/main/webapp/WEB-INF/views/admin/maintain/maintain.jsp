<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/WEB-INF/include/navs.jsp"%>
<div class="panel panel-default" style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;padding-top: 5px;">
        <ul class="nav nav-pills">
            <li role="presentation" ><a href="${contextPath}/admin/maintain/routeMaintainIndex.action"><span class="glyphicon glyphicon-map-marker"></span>故障管理</a></li>
            <c:forEach items="${sessionScope.functions}" var="function">
                <c:if test="${function.functionId == 43}">
                    <li role="presentation" >
                        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#maintainFrom">故障登记</button>
                    </li>
                </c:if>
            </c:forEach>
        </ul>
    </div>
    <div class="panel-body">
        <c:if test="${not empty opreateMessage}">
        <div class="col-md-12" id="message">
            <p class="bg-success">${opreateMessage}</p>
        </div>
        </c:if>
        <c:if test="${not empty opreateFailureMessage}">
        <div class="col-md-12" id="message">
            <p class="bg-success">${opreateFailureMessage}</p>
        </div>
        </c:if>

        <c:if test="${not empty protectMessage}">
        <div class="col-md-12" id="message">
            <p class="bg-success">${protectMessage}</p>
        </div>
        </c:if>
        <c:if test="${not empty protectFailureMessage}">
        <div class="col-md-12" id="message">
            <p class="bg-danger">${protectFailureMessage}</p>
        </div>
        </c:if>
        <c:if test="${not empty maintainMessage}">
        <div class="col-md-12" id="message">
            <p class="bg-success">${maintainMessage}</p>
        </div>
        </c:if>
        <c:if test="${not empty maintainFailureMessage}">
        <div class="col-md-12" id="message">
            <p class="bg-success">${maintainFailureMessage}</p>
        </div>
        </c:if>
        <c:if test="${not empty protectRepeatMessage}">
        <div class="col-md-12" id="message">
            <p class="bg-danger">${protectRepeatMessage}</p>
        </div>
        </c:if>
        <c:if test="${not empty cancleProtectFailureMessage}">
        <div class="col-md-12" id="message">
            <p class="bg-danger">${cancleProtectFailureMessage}</p>
        </div>
        </c:if>
        <c:if test="${not empty cancleProtectRepeatMessage}">
        <div class="col-md-12" id="message">
            <p class="bg-danger">${cancleProtectRepeatMessage}</p>
        </div>
        </c:if>
        <c:if test="${not empty cancleProtectMessage}">
        <div class="col-md-12" id="message">
            <p class="bg-danger">${cancleProtectMessage}</p>
        </div>
        </c:if>
        <c:if test="${not empty maintainFinishMessage}">
        <div class="col-md-12" id="message">
            <p class="bg-success">${maintainFinishMessage}</p>
        </div>
        </c:if>
        <c:if test="${not empty maintainFinishFailureMessage}">
        <div class="col-md-12" id="message">
            <p class="bg-success">${maintainFinishFailureMessage}</p>
        </div>
        </c:if>
        <c:if test="${not empty maintainRepeatMessage}">
            <div class="col-md-12" id="message">
                <p class="bg-success">${maintainRepeatMessage}</p>
            </div>
        </c:if>
        <c:if test="${not empty protectRemainMessage}">
            <div class="col-md-12" id="message">
                <p class="bg-success">${protectRemainMessage}</p>
            </div>
        </c:if>
        <c:if test="${not empty diaocheWrongMessage}">
            <div class="col-md-12" id="message">
                <p class="bg-success">${diaocheWrongMessage}</p>
            </div>
        </c:if>
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
                        <th>预计时间倒计时</th>
                    </tr>
                    <c:forEach items="${list}" var="maintain" varStatus="status">
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
                                <c:if test="${function.functionId == 42}">
                                    <c:if test="${maintain.trackState == 2}">
                                        <a href="${contextPath}/admin/maintain/maiCancleProtect/${maintain.track}.action" style="text-decoration: none;">
                                            <button type="button" class="btn btn-danger">取消防护</button>
                                        </a>
                                    </c:if>
                                    <c:if test="${maintain.trackState == 1}">
                                        <a style="text-decoration: none;">
                                            <button type="button" class="btn btn-warning" name="${maintain.track}" data-toggle="modal" data-target="#protectFrom">开始防护</button>
                                        </a>
                                    </c:if>
                                </c:if>
                                <c:if test="${function.functionId == 43}">
                                    <a style="text-decoration: none;" id="modelButton">
                                        <button type="button" class="btn btn-success" data-toggle="modal" name="${maintain.faultRegisterId}" data-target="#finishMaintainFrom">故障销记</button>
                                    </a>
                                </c:if>
                            </c:forEach>
                            </td>
                            <c:if test="${maintain.trackState == 2}">
                                <td class="timeRecord" >${maintain.dealTime}</td>
                            </c:if>
                            <c:if test="${maintain.trackState == 1}">
                                <td>未开始防护</td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="protectFrom" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">防护登记</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="${contextPath}/admin/maintain/maintainProtectAdd.action" method="post" id="portectFrom">
                    <input type="hidden" name="track" id="proRegTrackId">
                    <input type="hidden" name="place" id="proRegPlaceId">
                    <div class="form-group">
                        <label for="proRegPlaceName" class="col-sm-3 control-label">场</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="proRegPlaceName" name="placeName" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="proRegTrackName" class="col-sm-3 control-label">股道</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="proRegTrackName" name="trackName" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="protectPeople" class="col-sm-3 control-label">防护人</label>
                        <div class="col-sm-9">
                            <select class="form-control" id="protectPeople" name="protectPeople">
                                <c:forEach items="${workers}" var="worker">
                                    <option value="${worker.userName}">${worker.userName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="protectRequestPeople" class="col-sm-3 control-label">请求人</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="protectRequestPeople" name="protectRequestPeople" value="${protectRequestPeople.userName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="protectApprovePeople" class="col-sm-3 control-label">批准人</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="protectApprovePeople" name="protectApprovePeople" value="${protectApprovePeople.userName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="protectRemark" class="col-sm-3 control-label"><h5 style="color: #AA0000"><b>预计时间（分钟）</b></h5></label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="protectRemark" name="protectRemark">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="diaochePass" class="col-sm-3 control-label"><h5 style="color: #AA0000"><b>调车长确认密码</b></h5></label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" id="diaochePass" name="diaochePass">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-2">
                            <button type="submit" class="btn btn-default">提交</button>
                        </div>
                        <div class="col-sm-offset-1 col-sm-6">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="maintainFrom" tabindex="-1" role="dialog" aria-labelledby="myModalLabelTwo">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabelTwo">故障登记</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="${contextPath}/admin/maintain/reMaintainAdd.action" method="post" id="faultForm">
                    <div class="form-group">
                        <label for="track2" class="col-sm-3 control-label">股道</label>
                        <div class="col-sm-9">
                            <select class="form-control" id="track2" name="track">
                                <c:forEach items="${sessionScope.tracks}" var="track">
                                    <option value="${track.trackId}">${track.trackName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="region" class="col-sm-3 control-label">区</label>
                        <div class="col-sm-9">
                            <select class="form-control" id="region" name="region">
                                <c:forEach items="${sessionScope.regions}" var="region">
                                    <option value="${region.regionId}">${region.regionName}</option>
                                </c:forEach>
                                <option value="4">整线</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" id="seatId">
                        <label for="seat" class="col-sm-3 control-label">位置</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="seat" name="seat" placeholder="001">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="faultTypeId" class="col-sm-3 control-label">故障类型</label>
                        <div class="col-sm-9">
                            <select class="form-control" id="faultTypeId" name="faultType">
                                <c:forEach items="${faultTypes}" var="faultType">
                                    <option value="${faultType.faultTypeId}">${faultType.faultTypeName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" id="findPeople">
                        <label for="faultFindPeople" class="col-sm-3 control-label">发现人</label>
                        <div class="col-sm-9">
                            <select class="form-control" id="faultFindPeople" name="faultFindPeople">
                                <c:forEach items="${workers}" var="worker">
                                    <option value="${worker.userName}" selected>${worker.userName}</option>
                                </c:forEach>
                                <option value="qt">其他</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" id="findPeopleText">
                        <label for="faultFindPeopleText" class="col-sm-3 control-label">发现人</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="faultFindPeopleText" name="faultFindPeopleText" placeholder="请输入发现人姓名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="faultRegisterPeople" class="col-sm-3 control-label">记录人</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="faultRegisterPeople" name="faultRegisterPeople" value="${protectRequestPeople.userName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="faultSugestion" class="col-sm-3 control-label">建议处理办法</label>
                        <div class="col-sm-9">
                            <textarea class="form-control" id="faultSugestion" name="faultSugestion" readonly></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-2">
                            <button type="submit" class="btn btn-default">提交</button>
                        </div>
                        <div class="col-sm-offset-1 col-sm-6">
                            <button type="button" class="btn btn-default" data-dismiss="modal">放弃</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="finishMaintainFrom" tabindex="-1" role="dialog" aria-labelledby="myModalLabelForth">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabelForth">故障销记</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="${contextPath}/admin/maintain/reFinishMaintain.action" method="post" id="finishFaultForm">
                    <input type="hidden" name="faultRegisterId" id="fauCanId">
                    <div class="form-group">
                        <label for="faultRegisterPeopleTwo" class="col-sm-2 control-label">记录人</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="faultRegisterPeopleTwo" name="faultRegisterPeople" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="faultDealPeople" class="col-sm-2 control-label">处理人</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="faultDealPeople" name="faultDealPeople">
                                <c:forEach items="${workers}" var="worker">
                                    <option value="${worker.userName}">${worker.userName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="faultReason" class="col-sm-2 control-label">处理结果</label>
                        <div class="col-sm-10">
                            <tags:dicselect name="faultReason" key="faultReason" value="1" id="faultReason" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-2">
                            <button type="submit" class="btn btn-default">提交</button>
                        </div>
                        <div class="col-sm-offset-1 col-sm-7">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function() {
        // 设置table表格中的行高
        var $height = $('#paginationTable td').height() + 'px';
        $('#paginationTable td').css('line-height', $height);
        //发现人处理
        document.getElementById('findPeopleText').style.display = "none";
        $("#faultFindPeople").on('change',function() {
            var $value = $("#faultFindPeople").val();
            if($value == "qt") {
                document.getElementById('findPeopleText').style.display = "";
            }else {
                document.getElementById('findPeopleText').style.display = "none";
            }
        });
        //整线登记故障
        $("#region").on('change',function() {
            var $value = $("#region").val();
            if($value == 4) {
                document.getElementById('seatId').style.display = "none";
            }else {
                document.getElementById('seatId').style.display = "";
            }
        });

        //建议处理办法
        $("#faultTypeId").on('change',function() {
            var $value = $("#faultTypeId").val();
            $.ajax({
                type: 'post',
                contentType: 'application/json',
                dataType: 'json',
                url: '${contextPath}/admin/faultType/selectByFaultTypeId/' + $value + '.action',
                success: function (result) {
                    var $faultTypeJson = result.jsonByFauTyId;
                    document.getElementById("faultSugestion").value=$faultTypeJson.faultSugestion;
                }
            });
        })

        $(".btn.btn-success").click(function() {
            var value = $(this).attr("name"); // $(this)表示获取当前被点击元素的name值
            document.getElementById("fauCanId").value=value;
            $.ajax({
                type: 'post',
                contentType: 'application/json',
                dataType: 'json',
                url: '${contextPath}/admin/maintain/selectJsonByFauId/' + value + '.action',
                success: function (result) {
                    var RegisterPeople = result.jsonByFauId;
                    document.getElementById("faultRegisterPeopleTwo").value=RegisterPeople.faultRegisterPeople;
                }
            });
        });

        $(".btn.btn-warning").click(function() {
            var value = $(this).attr("name"); // $(this)表示获取当前被点击元素的name值
            $.ajax({
                type: 'post',
                contentType: 'application/json',
                dataType: 'json',
                url: '${contextPath}/admin/maintain/fauRegTrack/' + value + '.action',
                success: function (result) {
                    var device = result.maintain;
                    document.getElementById("proRegPlaceName").value=device.placeName;
                    document.getElementById("proRegTrackName").value=device.trackName;
                    document.getElementById("proRegTrackId").value=device.track;
                    document.getElementById("proRegPlaceId").value=device.place;
                }
            });
            var $value = $("#faultTypeId").val();
            $.ajax({
                type: 'post',
                contentType: 'application/json',
                dataType: 'json',
                url: '${contextPath}/admin/faultType/selectByFaultTypeId/' + $value + '.action',
                success: function (result) {
                    var $faultTypeJson = result.jsonByFauTyId;
                    document.getElementById("faultSugestion").value=$faultTypeJson.faultSugestion;
                }
            });
        });

        // 防护倒计时
        $.each($(".timeRecord"), function() {
            var maxtime = 0;

            if($(this).html() <= 0) {
                $(this).css('color', 'red');
            }
            if ($(this).html() != null && $(this).html() > 0) {
                maxtime = $(this).html();
            }

            if (maxtime == NaN) {
                return;
            }

            var content = $(this);

            setInterval(function countDown() {
                if(maxtime >=0 ) {
                    minutes = Math.floor(maxtime/60);
                    seconds = Math.floor(maxtime%60);
                    msg ="距离结束还有 "+minutes+" 分 "+seconds+" 秒";
                    content.html(msg);
                    --maxtime;
                    if(maxtime <= 5*60) {
                        content.css('color', 'red');
                    }
                }
            },1000);
        });

        //整数验证
        $('#portectFrom').validate({
            rules: {
                protectRemark: {
                    digits:true
                }
            },
            messages: {
                protectRemark: {
                    digits:"请输入整数！"
                }
            }
        });

        $('#faultForm').validate({
            rules: {
                seat: {
                    required:true
                }
            },
            messages: {
                seat: {
                    required:"位置不能为空!"
                }
            }
        });
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>