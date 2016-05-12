package com.jkxy.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jkxy.service.SearchPoetService;

public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private SearchPoetService sps = new SearchPoetService();

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String select_info = req.getParameter("select_info");
		String search_info = req.getParameter("search_info");
		
		HttpSession session = req.getSession();
//	session中的poets，是一个由check()方法返回的结果集，该结果集在bottm.jsp中会存入Iterator中
		session.setAttribute("poets", sps.check(search_info, select_info));
		
		String forward = "/poet_web/result.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(forward);
		rd.forward(req, resp);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
