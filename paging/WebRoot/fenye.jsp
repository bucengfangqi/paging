<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*"%>
<%//变量声明   
    java.sql.Connection sqlCon; //数据库连接对象   
    java.sql.Statement sqlStmt; //SQL语句对象   
    java.sql.ResultSet sqlRst; //结果集对象   
    String statement; //数据库连接字符串   
    String strSQL; //SQL语句   
    int intPageSize; //一页显示的记录数   
    int intRowCount; //记录总数   
    int intPageCount; //总页数   
    int intPage; //待显示页码   
    String strPage;   
    int i;   
    //设置一页显示的记录数   
    intPageSize =2;   
    //取得待显示页码   
    strPage = request.getParameter("page");   
      
    if(strPage==null)  
    {//表明在QueryString中没有page这一个参数，此时显示第一页数据   
        intPage = 1;   
    }   
    else{//将字符串转换成整型   
        intPage = java.lang.Integer.parseInt(strPage);   
        if(intPage<1) intPage = 1;   
    }   
    //装载JDBC驱动程序   
    Class.forName("com.mysql.jdbc.Driver");   
    //设置数据库连接字符串   
    statement = "jdbc:mysql://localhost:3306/mybatis";   
    //连接数据库   
    sqlCon = java.sql.DriverManager.getConnection(statement,"root","123456");   
    //创建一个可以滚动的只读的SQL语句对象   
    sqlStmt = sqlCon.createStatement();//准备SQL语句   
    strSQL = "select id,txt from advice";   
    //执行SQL语句并获取结果集   
    sqlRst = sqlStmt.executeQuery(strSQL);   
    //获取记录总数   
    sqlRst.last();//光标在最后一行   
    intRowCount = sqlRst.getRow();//获得当前行号   
    //记算总页数   
    intPageCount = (intRowCount+intPageSize-1) / intPageSize;   
    //调整待显示的页码,超过总页数则显示最后一页  
    if(intPage>intPageCount) intPage = intPageCount;   
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'test1.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form method="POST" action="fenye.jsp">
		第<%=intPage%>页 共<%=intPageCount%>页
		<% if(intPage>1){    //当前在第一页之后需要有上一页%>
		<a href="fenye.jsp?page=<%=intPage-1%>">上一页</a>
		<%}%>
		<% if(intPage<intPageCount){   //总页数大于当前页说明还有下一页%>
		<a href="fenye.jsp?page=<%=intPage+1%>">下一页 </a>
		<%}%>
		转到第:<input type="text" name="page" size="8" maxlength="4">页 
		<span>
		<input class=buttonface type="submit" value="GO">
		</span>
	</form>


	<table border="1" cellspacing="0" cellpadding="0">
		<tr>
			<th>ID</th>
			<th>建议</th>
		</tr>
		<%   
            if(intPageCount>0){   
            //将记录指针定位到待显示页的第一条记录上   
            sqlRst.absolute((intPage-1) * intPageSize + 1);   
            //显示数据   
            i = 0;   
            String user_id,user_name;   
            while(i<intPageSize && !sqlRst.isAfterLast()){   
            user_id=sqlRst.getString(1);   
            user_name=sqlRst.getString(2);   
        %>
		<tr>
			<td><%=user_id%></td>
			<td><%=user_name%></td>

		</tr>
		<%   
                sqlRst.next();   
                i++;   
                }   
            }   
        %>
	</table>

	<%   
        //关闭结果集   
        sqlRst.close();   
        //关闭SQL语句对象   
        sqlStmt.close();   
        //关闭数据库   
        sqlCon.close();   
    %>
</body>
</html>
