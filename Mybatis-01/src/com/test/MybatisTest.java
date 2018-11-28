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
	 * 1���ӿ�ʽ���
	 * 	ԭ����		Dao		====>  DaoImpl
	 * 	mybatis��	Mapper	====>  xxMapper.xml
	 * 2��SqlSession��������ݿ��һ�λỰ���������رգ�
	 * 3��SqlSession��connectionһ�����Ƿ��̰߳�ȫ��ÿ��ʹ�ö�Ӧ��ȥ��ȡ�µĶ���
	 * 4��mapper�ӿ�û��ʵ���࣬����mybatis��Ϊ����ӿ�����һ���������
	 * 		�����ӿں�xml���а󶨣�
	 * 		AccountMapper accountMapper =	sqlSession.getMapper(AccountMapper.class);
	 * 5��������Ҫ�������ļ���
	 * 		mybatis��ȫ�������ļ����������ݿ����ӳ���Ϣ�������������Ϣ��...ϵͳ���л�����Ϣ
	 * 		sqlӳ���ļ���������ÿһ��sql����ӳ����Ϣ��
	 * 					��sql��ȡ������	
	 **/
	
	// 1����ȡsqlSessionFactory����
	public SqlSessionFactory getSqlSessionFactory() throws IOException{
		String resource="mybatis-cofig.xml";
		InputStream is=Resources.getResourceAsStream(resource);
		SqlSessionFactory sf=new SqlSessionFactoryBuilder().build(is);
		return sf;
	}
	@Test
	public void testInface() throws IOException{
		// 2����ȡsqlSession����
		SqlSession ss=getSqlSessionFactory().openSession();
		try {
			// 3����ȡ�ӿڵ�ʵ�������
			//��Ϊ�ӿ��Զ��Ĵ���һ��������󣬴������ȥִ����ɾ�Ĳ鷽��
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
		// 1����ȡsqlSessionFactory����
		SqlSessionFactory sf=new SqlSessionFactoryBuilder().build(is);
		// 2����ȡsqlSessionʵ������ֱ��ִ���Ѿ�ӳ���sql���
		// sql��Ψһ��ʶ��statement Unique identifier matching the statement to use.
		// ִ��sqlҪ�õĲ�����parameter A parameter object to pass to the statement.
		SqlSession ss=sf.openSession();
		try {
			Account account=ss.selectOne("com.dao.AccountMapper.selectAccount", 1);
			System.out.println(account);
		} finally {
			ss.close();
		}
	}
}
