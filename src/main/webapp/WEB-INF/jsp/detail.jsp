<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>秒杀详情页</title>
    <%@include file="common/head.jsp"%>
    <%--<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
<%--<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->--%>

<script src="../../resources/script/jquery/js/jquery.min.js"></script>
<script src="../../resources/script/bootstrap/js/bootstrap.min.js"></script>
<script src="../../resources/script/cookie/js/cookie.js"></script>
<script src="../../resources/script/jquery/js/jquery.countdown.js"></script>
<script type="text/javascript" src="../../resources/script/seckill.js"></script>
<script type="text/javascript">
    $(function (){
        seckill.detail.init({
            seckillId :${seckill.secKillId},
            startTime : ${seckill.start_time.time},
            endTime : ${seckill.end_time.time}
        });
    });
</script>
</head>
<body>
<div class="container">
    <div class="panel panel-default text-center">
        <div class="panel-heading">
            <h1>${seckill.name}</h1>
        </div>
        <div class="panel-body">
            <h2 class="text-danger">
                <span class="glyphicon glyphicon-time"></span>
                <span class="glyphicon" id="seckill-box"></span>
            </h2>
        </div>
    </div>
</div>
<div id="killPhoneModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title text-center">
                    <span class="glyphicon glyphicon-phone"></span>phone：
                </h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-8 col-xs-offset-2">
                        <input type="text" name="killphone" id="killphoneKey" placeholder="输入手机号" class="form-control"/>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <span id="killphoneMessage" class="glyphicon"></span>
                <button type="button" id="killPhoneBtn" class="btn btn-success">
                    <span class="glyphicon glyphicon-phone"></span>
                    	提交	
                </button>
            </div>
        </div>
    </div>
</div>
</body>
</html>