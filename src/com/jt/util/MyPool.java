package com.jt.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alibaba.druid.pool.DruidDataSource;

public class MyPool {
	
	//����Դ : �������ݿ������ṩ���ݿ����Ӷ���ļ���
	private static DruidDataSource dataSource = new DruidDataSource();
	
	static{
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/talking?characterEncoding=utf-8");
		dataSource.setUsername("root");
		dataSource.setPassword("hanchun123");
	}

	//��ȡ��������
	public static Connection getConnection(){
		
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//�ر�����
	public static void release(Connection cn,Statement sm,ResultSet rs){
		if(cn!=null){
			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(sm!=null){
			try {
				sm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
