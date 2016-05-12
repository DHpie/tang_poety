package com.jkxy.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import com.jkxy.dao.PoetDao;
import com.jkxy.dao.impl.PoetDaoImpl;
import com.jkxy.jdbc.JDBCCon;
import com.jkxy.model.Poet;

public class SearchPoetService {

	private PoetDao poetDao = new PoetDaoImpl();
	
	/***
	 * RSתLIST
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
//	将查询的结果存入数据库实体poet
	private static List convertList(ResultSet rs) throws SQLException {
        List list = new ArrayList();
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        while (rs.next()) {
            Poet poet = new Poet();
            poet.setId(rs.getLong(1));
            poet.setName(rs.getString(2));
            poet.setTitle(rs.getString(3));
            poet.setContent(rs.getString(4));
            list.add(poet);
        }
        return list;
	}
	
//	返回查询的结果
	public List check(String search_info, String select_info){
		Poet poet = new Poet();
		Connection conn = null;
		ResultSet resultSet = null;
		List poets = null;
		try{
			conn = JDBCCon.getInstance().makeConnection();
			conn.setAutoCommit(false);
			
			if(select_info.equals("author")){
				poet.setName(search_info);
				resultSet = poetDao.getFromName(conn, poet);
			}else if(select_info.equals("poetname")){
				poet.setTitle(search_info);
				resultSet = poetDao.getFromPoetName(conn, poet);
			}else if(select_info.equals("quotes")){
				poet.setContent(search_info);
				resultSet = poetDao.getFromQuotes(conn, poet);
			}else{
				resultSet = null;
			}
			poets = convertList(resultSet);
			
		}catch(Exception e){
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			try {
				conn.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}		
		}
		return poets;
	}
}
