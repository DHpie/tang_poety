package com.jkxy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jkxy.dao.PoetDao;
import com.jkxy.model.Poet;

public class PoetDaoImpl implements PoetDao {
//这里的每个具体的数据库操作都传入了一个   Connecion  conn参数，很大程度上简洁了代码
	@Override
	public ResultSet getFromName(Connection conn, Poet poet) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("select b.id, a.name, b.title, b.content from poets a join poetries b on a.id = b.poet_id where a.name = ? ");
		
		ps.setString(1, poet.getName());
		return ps.executeQuery();
	}

	@Override
	public ResultSet getFromPoetName(Connection conn, Poet poet) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("select b.id, a.name, b.title, b.content from poets a join poetries b on a.id = b.poet_id where b.title like ?");
//		使用like 为模糊查询，ps的数据库操作要用到  %
		ps.setString(1, "%" + poet.getTitle() + "%");
		return ps.executeQuery();
	}

	@Override
	public ResultSet getFromQuotes(Connection conn, Poet poet) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("select b.id, a.name, b.title, b.content from poets a join poetries b on a.id = b.poet_id where b.content like ?");
		
		ps.setString(1, "%" + poet.getContent() + "%");
		return ps.executeQuery();
	}

}
