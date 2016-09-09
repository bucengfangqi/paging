package com.zlt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.zlt.bean.Users;
import com.zlt.utils.UserUtil;

public class Submit_servlet extends HttpServlet{
	String statement=null;//声明要利用到的mapper
	SqlSession session=null;//声明工厂实例
	String message=null;//声明缠到网页得知
	String value=null;//声明网页传来的值
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//value接住网页传过来的值
		value=request.getParameter("txt");
		//在这里判断输入框是否为空
		if("".equals(value.replaceAll(" ",""))){
			message="麻烦亲输入建议哦~";
			request.setAttribute("message",message);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else{
			//因为编码不同~因此先将编码统一成UTF-8
			value=new String(value.getBytes("iso8859-1"),"UTF-8");
			//定义要利用到的mapper
			statement="com.zlt.mapper.UserMapper.addAdvice";
			//开启事务
			session=UserUtil.getSession(true);
			Users user=new Users();
			//设置
			user.setTxt(value);
			//提交保存
			session.insert(statement, user);
			//关闭资源
			session.close();
			//定义message;
			message="反馈成功！";
			request.setAttribute("message",message);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			System.out.println("添加成功！");
		}
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
