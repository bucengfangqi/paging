package com.zlt.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.zlt.bean.Users;
import com.zlt.utils.UserUtil;

public class LastPage extends HttpServlet{
	SqlSession session=null;//声明工厂实例
	String value=null;//声明网页传来的值
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置全局变量的值
			Look_servlet.nowpage=(Look_servlet.nowpage-1);
			Look_servlet.limitpage=(Look_servlet.limitpage-Look_servlet.howManyPages);
			
			String statement="com.zlt.mapper.UserMapper.getPageLimit0";
			String statement1="com.zlt.mapper.UserMapper.getPageLimit1";
			
			session=UserUtil.getSession();
			Users user=new Users();
			user.setNowpage(Look_servlet.nowpage);
			user.setLimitpage(Look_servlet.limitpage);
			user.setHowManyPages(Look_servlet.howManyPages);
			List list=session.selectList(statement,user);
			int count=session.selectOne(statement1);
			//把值放到map集合以方便循环输出到页面
			Map message=new HashMap();
			for(int i=0;i<list.size();i++){
				message.put((i+1),list.get(i));
			}
			
			//利用for循环迭代出网页序号，再根据序号传值到servlet确定跳转的页面
			Map message1=new HashMap();
			for(int i=0;i<(count+(Look_servlet.howManyPages-1))/Look_servlet.howManyPages;i++){
				message1.put((i+1),(i+1));
			}
			
			request.setAttribute("message1",message1);
			request.setAttribute("message",message);
			request.setAttribute("count", count);
			request.setAttribute("pagenumber",(count+(Look_servlet.howManyPages-1))/Look_servlet.howManyPages);
			request.setAttribute("pageNow",user.getNowpage()+1);
			request.setAttribute("howManyPages",Look_servlet.howManyPages);
			request.getRequestDispatcher("/test.jsp").forward(request, response);
			session.close();
	}
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
