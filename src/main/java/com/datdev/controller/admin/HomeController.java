package com.datdev.controller.admin;

import com.datdev.constant.SystemConstant;
import com.datdev.model.NewsModel;
import com.datdev.model.UserModel;
import com.datdev.service.INewsService;
import com.datdev.service.IUserService;
import com.datdev.utils.FormUtils;
import com.datdev.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

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
    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsModel model = FormUtils.toModel(NewsModel.class, req);
        String action = req.getParameter("action");

        if ("login".equals(action)) {
            String alert = req.getParameter("alert");
            String message = req.getParameter("message");
            if (message != null) {
                req.setAttribute("message", resourceBundle.getString(message));
                req.setAttribute("alert", alert);
            }
            RequestDispatcher rd = req.getRequestDispatcher("/views/admin/login.jsp");
            rd.forward(req, resp);
        } else if ("logout".equals(action)) {
            SessionUtil.getInstance().removeValue(req, "USERMODEL");
            resp.sendRedirect(req.getContextPath() + "/home-admin/");
        } else {
            req.setAttribute(SystemConstant.MODEL, model);
            RequestDispatcher rd = req.getRequestDispatcher("/views/admin/home.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("login".equals(action)) {
            UserModel model = FormUtils.toModel(UserModel.class, req);
            model = iUserService.findByUserNameAndPasswordAndStatus(model.getUserName(), model.getPassWord(), 1);

            if (model != null) {
                SessionUtil.getInstance().putValue(req, "USERMODEL", model);
                if ("USER".equals(model.getRoleModel().getCode())) {
                    resp.sendRedirect(req.getContextPath() + "/web-home/");
                } else if ("ADMIN".equals(model.getRoleModel().getCode())) {
                    resp.sendRedirect(req.getContextPath() + "/home-admin/");
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/login?action=login&message=invalid-user&alert=danger");
            }
        }
    }
}
