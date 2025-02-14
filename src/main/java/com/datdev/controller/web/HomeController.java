package com.datdev.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datdev.service.impl.CategoryService;
import com.datdev.service.impl.NewsService;

@WebServlet(urlPatterns = {"/web-home/"})
public class HomeController  extends HttpServlet {

	/**
	 *
	 */
	@Inject
	private  NewsService newsService;

	@Inject
	private CategoryService categoryService;

	private static final long serialVersionUID = 7347462573004462778L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

//		req.setAttribute("model",newsService.findAll() );
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/home.jsp");
		rd.forward(req, resp);
		doGet(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
