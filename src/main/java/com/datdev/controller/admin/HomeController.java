package com.datdev.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datdev.constant.SystemConstant;
import com.datdev.model.NewsModel;
import com.datdev.model.UserModel;
import com.datdev.paging.PageRequest;
import com.datdev.paging.Pageble;
import com.datdev.service.INewsService;
import com.datdev.service.IUserService;
import com.datdev.sort.Sorter;
import com.datdev.utils.FormUtils;

@WebServlet(urlPatterns = {"/home-admin/", "/login"})
public class HomeController extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 7347462573004462778L;
    @Inject
    private INewsService iNewsService;
    @Inject
    private IUserService iUserService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        NewsModel newsModel = new NewsModel();
////		newsModel.setListResult(iNewsService.findAll());
        String action = req.getParameter("action");
        if (action != null && action.equals("login")) {
            RequestDispatcher rd = req.getRequestDispatcher("/views/admin/login.jsp");
            rd.forward(req, resp);
        } else if (action != null && action.equals("logout")) {

        } else {
            req.setAttribute(SystemConstant.MODEL, newsModel);
            RequestDispatcher rd = req.getRequestDispatcher("/views/admin/home.jsp");
            rd.forward(req, resp);
        }


        doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String action = req.getParameter("action");
        if (action != null && action.equals("login")) {
            UserModel userModel = FormUtils.toModel(UserModel.class, req);
            userModel = iUserService.findByUserNameAndPasswordAndStatus(userModel.getUserName(), userModel.getPassWord(), 1);
            if (userModel != null) {
                if (userModel.getRoleModel().getCode().equals("USER")) {
                    resp.sendRedirect(req.getContextPath() + "/home-web/");
                } else if (userModel.getRoleModel().getCode().equals("ADMIN")) {
                    resp.sendRedirect(req.getContextPath() + "/home-admin/");
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/login?action=login");
            }
        }
        doPost(req, resp);
    }

}
