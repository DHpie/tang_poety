package com.jkxy.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jkxy.model.Poet;

public interface PoetDao {

	public ResultSet getFromName(Connection conn, Poet poet) throws SQLException;
	
	public ResultSet getFromPoetName(Connection conn, Poet poet) throws SQLException;
	
	public ResultSet getFromQuotes(Connection conn, Poet poet) throws SQLException;
}
