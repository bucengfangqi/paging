<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>分页测试</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		*{
			text-decoration:none;
			font-size:18px;
		}
		#test1{
			border:1px solid #E5E5E5;
			padding:8px;
			padding-left:15px;
			padding-right:15px;
			color:1024EE;
			
		}
		#test2{
			border:1px solid #E5E5E5;
			padding:8px;
			color:1024EE;
		}
		#test1:HOVER,#test2:HOVER{
		background-color:#FBFBFB;
		color:red;
		}
		
		#test3:HOVER{
		text-decoration: underline;
		}
		#test5{
			border:1px solid #E5E5E5;
			padding:8px;
		}
		#test4{
			border:1px solid #E5E5E5;
			padding:8px;
			padding-left:15px;
			padding-right:15px;
		}
	</style>
  </head>
  
  <body>
  一共<font style="font-weight: bold;" color="blue">${count}</font>条记录，每页显示<font style="font-weight: bold;" color="purple">${howManyPages}</font>条记录,分成<font style="font-weight: bold;" color="blue">${pagenumber}</font>页,当前第<font style="font-weight: bold;" color="blue">${pageNow}</font>页,<a href="index.jsp">
  <font id="test3" color="#1024EE"> 返回反馈界面</font></a><br /><br />
    <c:forEach var="me" items="${message}">
				<font color="red"> 第${me.key}条查询记录:</font>${me.value}<br /><br />
	</c:forEach><br />
	
	
	
	<c:choose>
		<c:when test="${pageNow>1}">
			<a href="LastPage" id="test2">上一页</a>
		</c:when>
		<c:otherwise>
			<font id="test5">上一页</font>
		</c:otherwise>
	</c:choose>
    
    
    
    
    <c:forEach var="me1" items="${message1}" >
    
    	<%-- <c:if test="${pageNow!=me1.value}">
				<a href="AnyPage?nowpage=${me1.value}" id="test1" style="width:24px; height:24px;">${me1.key}</a>
		</c:if>             在此为了显示当前所出页面when比if更美观     --%>
		
		<c:choose>
		<c:when test="${pageNow!=me1.value}">
			<a href="AnyPage?nowpage=${me1.value}" id="test1" style="width:24px; height:24px;">${me1.key}</a>
		</c:when>
		<c:otherwise>
			<font id="test4"> ${me1.key}</font>
		</c:otherwise>
		</c:choose>
		
	</c:forEach>
    
    
    
    
    <c:choose>
		<c:when test="${pageNow<pagenumber}">
			<a href="NextPage" id="test2">下一页</a>
		</c:when>
		<c:otherwise>
			<font id="test5">下一页</font>
		</c:otherwise>
	</c:choose>
    
  </body>
</html>
