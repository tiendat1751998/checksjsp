package com.datdev.filter;

import com.datdev.constant.SystemConstant;
import com.datdev.model.UserModel;
import com.datdev.utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String url = request.getRequestURI();
//        if (url.startsWith("/dat")) {
//            UserModel model = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
//            if (model != null && model.getRoleModel() != null) {
//                if (url.startsWith(request.getContextPath() + "/home-admin") && !SystemConstant.ADMIN.equals(model.getRoleModel().getCode())) {
//                    response.sendRedirect(request.getContextPath() + "/login?action=login&message=not-permission&alert=danger");
//                    return;
//
//                } else if (model.getRoleModel().getCode().equals(SystemConstant.USER)) {
//                    response.sendRedirect(request.getContextPath() + "/login?action=login&message=not-permission&alert=danger");
//
//                }
//
//            } else {
//                SessionUtil.getInstance().putValue(request, "REDIRECT_URL", url);
//                response.sendRedirect(request.getContextPath() + "/login?action=login&message=not-login&alert=danger");
//                return;
//            }
//
//        } else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        String contextPath = request.getContextPath();
        String loginPage = contextPath + "/login";

        // 🔹 Whitelist các URL không cần xác thực
        if (url.equals(loginPage)
                || url.startsWith(loginPage + "?")
                || url.endsWith("/register")
                || url.contains("/api/")
                || url.contains("/assets/")
                || url.matches(".*\\.(css|js|png|jpg|jpeg|gif|svg|woff|woff2|ttf|otf|eot)(\\?.*)?$")) {
            System.out.println("✅ Bypass filter for: " + url);
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 🔹 Kiểm tra đăng nhập
        UserModel model = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        if (model == null || model.getRoleModel() == null) {
            SessionUtil.getInstance().putValue(request, "REDIRECT_URL", url);
            response.sendRedirect(loginPage + "?action=login&message=not-login&alert=danger");
            return; // 🔴 Dừng ngay sau khi redirect
        }

        // 🔹 Kiểm tra phân quyền
        if (url.startsWith(contextPath + "/home-admin/")) {
            if (!SystemConstant.ADMIN.equals(model.getRoleModel().getCode())) {
                response.sendRedirect(loginPage + "?action=login&message=not-permission&alert=danger");
                return; // 🔴 Dừng ngay sau khi redirect
            }
        } else if (SystemConstant.USER.equals(model.getRoleModel().getCode())) {
            response.sendRedirect(loginPage + "?action=login&message=not-permission&alert=danger");
            return; // 🔴 Dừng ngay sau khi redirect
        }

        // 🔹 Cho phép request tiếp tục
        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {

    }
}
