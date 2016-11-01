<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/navs.jsp"%>
<link rel="stylesheet" href="${contextPath}/static/css/jquery-labelauty.css">
<style>
    .main-class{
        width: 70%;
        border: solid 1px #A7A7A7;
        margin: 0 auto;
    }
    .dowebok { list-style-type: none;}
    .dowebok li { display: inline-block;}
    .dowebok li { margin: 10px 0;}
    input.labelauty + label { font: 12px "Microsoft Yahei";}
</style>

<div class="main-class">
    <form class="form-horizontal" action="${contextPath}/admin/workerInfo/workerInfoAdd.action" method="post" id="addForm">
        <c:if test="${not empty workerInfoAddMessage}">
            <div class="col-md-12" id="message">
                <p class="bg-success">${workerInfoAddMessage}</p>
            </div>
        </c:if>
        <c:if test="${not empty addFailureMessage}">
            <div class="col-md-12" id="message">
                <p class="bg-success">${addFailureMessage}</p>
            </div>
        </c:if>
        <h1>金州站</h1>
        <h3>驼峰调车长（单选）</h3>
        <ul class="dowebok">
            <c:forEach items="${diaoches}" var="diaoche">
                <li><input type="radio" name="diaoche" data-labelauty="${diaoche.userName}" value="${diaoche.userId}"></li>
            </c:forEach>
        </ul>

        <hr>
        <h3>驻站联络员（单选）</h3>
        <ul class="dowebok">
            <c:forEach items="${zhibans}" var="zhiban">
                <li><input type="radio" name="zhiban" data-labelauty="${zhiban.userName}" value="${zhiban.userId}"></li>
            </c:forEach>
        </ul>
        <hr />

        <h3>包线工（复选）</h3>
        <ul class="dowebok">
            <c:forEach items="${workers}" var="worker">
                <li><input type="checkbox" name="worker" data-labelauty="${worker.userName}" value="${worker.userId}"></li>
            </c:forEach>
        </ul>
        <hr />
        <h3>防护员（复选）</h3>
        <ul class="dowebok">
            <c:forEach items="${fanghus}" var="fanghu">
                <li><input type="checkbox" name="fanghu" data-labelauty="${fanghu.userName}" value="${fanghu.userId}"></li>
            </c:forEach>
        </ul>
        <ul class="dowebok">
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button id="workerInfoAdd" type="submit" class="btn btn-danger" >登记</button>
                    <button type="button" id="backMark" class="btn btn-default" style="margin-left: 200px">返回</button>
                </div>
            </div>
        </ul>
    </form>

    <script type="text/javascript" src="${contextPath}/static/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${contextPath}/static/js/jquery-labelauty.js"></script>

    <script type="text/javascript" src="${contextPath}/static/plugins/bootstrap/js/bootstrap.js" ></script>
    <script type="text/javascript" src="${contextPath}/static/support/jquery.placeholder.js" ></script>
    <script>
        $(function(){
            $(':input').labelauty();

            //id="backMark" 返回按钮
            $('#backMark').on('click', function () {
                window.history.back(-1);
            });
            // 消息提示
            setTimeout(function() {
                $("#message").hide();
            }, 2000);

            $('#workerInfoAdd').on('click', function () {
                var statu = confirm('确认选中人员为今天上班人员？');
                if(!statu){
                    return false;
                }
            })

            $('#logout').on('click', function () {
                var statu = confirm('您确认退出吗?退出后上岗人员记录将要重新记录');
                if(!statu){
                    return false;
                }
            })
        });

    </script>
</div>

</body>
</html>