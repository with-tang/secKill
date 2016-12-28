<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
  <head>
    <title>秒杀列表页</title>
<%@include file="common/head.jsp"%>  
<script src="../../resources/script/jquery/js/jquery.min.js"></script>
<script src="../../resources/script/bootstrap/js/bootstrap.min.js"></script>
<script src="../../resources/script/cookie/js/cookie.js"></script>
<script src="../../resources/script/jquery/js/jquery.countdown.js"></script>
<script type="text/javascript" src="../../resources/secKill.js"></script>
  </head>
  <body>
   <div class="container">
	   	<div class="panel panel-default">
		   	<div class="panel panel-heading text-center">
		   		<h2>秒杀列表</h2>
		   	</div>
		   	<div class="panle panel-body">
		   		<table class="table table-hover">
		   		<thead>
		   			<tr>
		   				<th>名称</th>
		   				<th>库存</th>
		   				<th>开始时间</th>
		   				<th>结束时间</th>
		   				<th>创建时间</th>
		   				<th>详情页</th>		   				
		   			</tr>
		   		</thead>
		   		<tbody>
			   		<c:forEach var="sk" items="${list}">
			   		<tr>
			   			<td>${sk.name}</td>
			   			<td>${sk.number}</td>
			   			<td>
			   			<fmt:formatDate value="${sk.start_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
			   			<td>
			   			<fmt:formatDate value="${sk.end_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
			   			<fmt:formatDate value="${sk.create_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
			   			<td>
			   			<a class="btn btn-info" href="/seckill/seckill/${sk.secKillId}/detail" target="blank">link</a>
			   			</td>
			   		</tr>
			   		</c:forEach>
		   		</tbody>
		   		</table>
		   	</div>
	   	</div>
   
   </div>
  </body>

</html>