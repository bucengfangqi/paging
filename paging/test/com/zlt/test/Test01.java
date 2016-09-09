package com.zlt.test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.zlt.bean.Users;
import com.zlt.utils.UserUtil;

public class Test01 {
	@Test
	public void addtxt(){
		String statement="com.zlt.mapper.UserMapper.addAdvice";
		SqlSession session=UserUtil.getSession(true);
		Users user=new Users();
		user.setTxt("idea~");
		for(int i=0;i<=100;i++){
		session.insert(statement, user);
		}
		session.close();
		System.out.println("添加成功！");
	}
	@Test
	public void getPageLimit0(){
		String statement1="com.zlt.mapper.UserMapper.getPageLimit0";
		SqlSession session=UserUtil.getSession();
		Users user=new Users();
		List list=session.selectList(statement1,user.getNowpage());
		for(int i=0;i<list.size();i++){
			System.out.println("_________________第"+(i+1)+"条:"+list.get(i));
		}
	}
	@Test
	public void getPageLimit1(){
		String statement="com.zlt.mapper.UserMapper.getPageLimit1";
		SqlSession session=UserUtil.getSession();
		int list=session.selectOne(statement);
		Users user=new Users();
		System.out.println("一共"+list+"条记录，分成"+(list+3)/4+"页,"+"当前第"+(user.getNowpage()+1)+"页");
		
		String statement1="com.zlt.mapper.UserMapper.getPageLimit0";
		
		List list1=session.selectList(statement1,list);
		for(int i=0;i<list1.size();i++){
			System.out.println("_________________第"+(i+1)+"条:"+list1.get(i));
		}
		
	}
	@Test
	public void getPageLimit2(){
		String statement="com.zlt.mapper.UserMapper.getPageLimit2";
		SqlSession session=UserUtil.getSession();
		List list=session.selectList(statement);
		for(int i=0;i<list.size();i++){
			System.out.println("_________________第"+(i+1)+"条:"+list.get(i));
		}
	}
}
