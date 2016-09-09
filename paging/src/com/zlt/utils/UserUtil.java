package com.zlt.utils;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class UserUtil {
	public static SqlSessionFactory getSqlSession(){
		String str="mybatis-config.xml";//把配置文件路径赋值给str
		InputStream in=UserUtil.class.getClassLoader().getResourceAsStream(str);//根据路径加载文件进入输入流中
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(in);//根据输入流中的信息建立SqlSessionFactory工厂
		return sessionFactory;//返回工厂实例
	}
	
	//开启事务处理
	public static SqlSession getSession(){
		return getSqlSession().openSession();
	}
	public static SqlSession getSession(boolean s){
		return getSqlSession().openSession(s);
	}
}
