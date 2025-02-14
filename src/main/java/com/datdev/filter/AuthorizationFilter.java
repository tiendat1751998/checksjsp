package com.datdev.filter;

import com.datdev.constant.SystemConstant;
import com.datdev.model.NewsModel;
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
//        if (url.startsWith("/"))
//        {
//            UserModel model = (UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
//            if(model!=null && model.getRoleModel() != null)
//            {
//                if (model.getRoleModel().getCode().equals(SystemConstant.ADMIN))
//                {
//                    filterChain.doFilter(servletRequest,servletResponse);
//
//                }else if (model.getRoleModel().getCode().equals(SystemConstant.USER))
//                {
//                    response.sendRedirect(request.getContextPath()+"/login?action=login&message=not-permission&alert=danger");
//
//                }
//
//            }else {
//                response.sendRedirect(request.getContextPath()+"/login?action=login&message=not-login&alert=danger");
//            }
//
//        }else {
//            filterChain.doFilter(servletRequest,servletResponse);
//        }
//    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();

        // Bỏ qua kiểm tra đăng nhập cho các trang này
        if (url.endsWith("/login") || url.endsWith("/register") || url.contains("/assets/") || url.matches(".*\\.(css|js|png|jpg|jpeg|gif|svg|woff|woff2|ttf|otf|eot)$")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // Kiểm tra nếu user đã đăng nhập
        UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");

        // Lưu URL yêu cầu trước đó để sau khi đăng nhập sẽ quay lại
        if (user == null) {
            SessionUtil.getInstance().putValue(request, "REDIRECT_URL", url);
            response.sendRedirect(request.getContextPath() + "/login?action=login&message=not-login&alert=danger");
            return;
        }

        // Chặn USER vào ADMIN
        if (url.startsWith(request.getContextPath() + "/home-admin") && !SystemConstant.ADMIN.equals(user.getRoleModel().getCode())) {
            response.sendRedirect(request.getContextPath() + "/login?action=login&message=not-permission&alert=danger");
            return;
        }
    }

    @Override
    public void destroy() {

    }
}
