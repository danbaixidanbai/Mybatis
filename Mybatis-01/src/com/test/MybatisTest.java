package com.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.dao.AccountMapper;
import com.entity.Account;

public class MybatisTest {
	
	/**
	 * 1、接口式编程
	 * 	原生：		Dao		====>  DaoImpl
	 * 	mybatis：	Mapper	====>  xxMapper.xml
	 * 2、SqlSession代表和数据库的一次会话；用完必须关闭；
	 * 3、SqlSession和connection一样都是非线程安全。每次使用都应该去获取新的对象。
	 * 4、mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。
	 * 		（将接口和xml进行绑定）
	 * 		AccountMapper accountMapper =	sqlSession.getMapper(AccountMapper.class);
	 * 5、两个重要的配置文件：
	 * 		mybatis的全局配置文件：包含数据库连接池信息，事务管理器信息等...系统运行环境信息
	 * 		sql映射文件：保存了每一个sql语句的映射信息：
	 * 					将sql抽取出来。	
	 **/
	
	// 1、获取sqlSessionFactory对象
	public SqlSessionFactory getSqlSessionFactory() throws IOException{
		String resource="mybatis-cofig.xml";
		InputStream is=Resources.getResourceAsStream(resource);
		SqlSessionFactory sf=new SqlSessionFactoryBuilder().build(is);
		return sf;
	}
	@Test
	public void testInface() throws IOException{
		// 2、获取sqlSession对象
		SqlSession ss=getSqlSessionFactory().openSession();
		try {
			// 3、获取接口的实现类对象
			//会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
			AccountMapper accountMapper=ss.getMapper(AccountMapper.class);
			System.out.println(accountMapper.getClass());
			Account account =accountMapper.getAccountById(1);
			System.out.println(account);
		} finally {
			ss.close();
		}
		
	}
	
	
	
	
	@Test
	public void show() throws IOException{
		String resource="mybatis-cofig.xml";
		InputStream is=Resources.getResourceAsStream(resource);
		// 1、获取sqlSessionFactory对象
		SqlSessionFactory sf=new SqlSessionFactoryBuilder().build(is);
		// 2、获取sqlSession实例，能直接执行已经映射的sql语句
		// sql的唯一标识：statement Unique identifier matching the statement to use.
		// 执行sql要用的参数：parameter A parameter object to pass to the statement.
		SqlSession ss=sf.openSession();
		try {
			Account account=ss.selectOne("com.dao.AccountMapper.selectAccount", 1);
			System.out.println(account);
		} finally {
			ss.close();
		}
	}
}
