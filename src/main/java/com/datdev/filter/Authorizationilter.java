package com.datdev.filter;

import com.datdev.constant.SystemConstant;
import com.datdev.model.NewsModel;
import com.datdev.model.UserModel;
import com.datdev.utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Authorizationilter implements Filter {

    private  ServletContext context;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        if (url.startsWith("/admin"))
        {
            UserModel model = (UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
            if(model!=null)
            {
                if (model.getRoleModel().getCode().equals(SystemConstant.ADMIN))
                {
                    filterChain.doFilter(servletRequest,servletResponse);

                }else if (model.getRoleModel().getCode().equals(SystemConstant.USER))
                {
                    response.sendRedirect(request.getContextPath()+"/login?action=login&&message=not-permission&&alert=danger");

                }

            }else {
                response.sendRedirect(request.getContextPath()+"/login?action=login&&message=not-login&&alert=danger");
            }

        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
