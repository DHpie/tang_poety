package com.jkxy.jdbc;

import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;
import java.sql.Connection;

public class JDBCCon {
	
	private static String driver;
	private static String dburl;
	private static String user;
	private static String password;
	
	private static final JDBCCon jdbccon = new JDBCCon();
	
	private Connection conn;
//从配置文件读取数据库信息
	static{
		Properties prop = new Properties();
		try{
			InputStream in = JDBCCon.class.getClassLoader().getResourceAsStream("dbconfig.properties");
			prop.load(in);
		}catch(Exception e){
			System.out.println("读取配置文件异常！");
		}
		
		driver = prop.getProperty("driver");
		dburl = prop.getProperty("dburl");
		user = prop.getProperty("user");
		password = prop.getProperty("password");
	}
//	连接数据库	
	public Connection makeConnection(){
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(dburl, user, password);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	private JDBCCon(){
		
	}
//工厂类，取得连接类的一个实例化对象
	public static JDBCCon getInstance(){
		return jdbccon;
	}

}
	
