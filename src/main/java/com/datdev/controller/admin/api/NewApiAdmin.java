package com.datdev.controller.admin.api;

import com.datdev.model.NewsModel;
import com.datdev.model.UserModel;
import com.datdev.service.INewsService;
import com.datdev.utils.HttpUtils;
import com.datdev.utils.SessionUtil;
import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewApiAdmin extends HttpServlet {
    private final static long  serialVersionUID  = 1231245123514L;
     @Inject
     private INewsService iNewsService;

    protected void  doPost(HttpServletRequest request , HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
//        conver json sang new model
        NewsModel newsModel = HttpUtils.Of(request.getReader()).toModel(NewsModel.class);
//        UserModel userModel = ((UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL")).getUserName();
        newsModel.setCreateBy( ((UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL")).getUserName());
        newsModel = iNewsService.save(newsModel);
//         convert data sang json
        objectMapper.writeValue(response.getOutputStream(),newsModel);

        System.out.println(newsModel);

        doPost(request,response);
    }
    protected void  doPut(HttpServletRequest request , HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        NewsModel updateNews = HttpUtils.Of(request.getReader()).toModel(NewsModel.class);
        updateNews.setModifireBy( ((UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL")).getUserName());
        updateNews = iNewsService.update(updateNews);

        objectMapper.writeValue(response.getOutputStream(),updateNews);

        doPut(request,response);
    }
    protected void  doDelete(HttpServletRequest request , HttpServletResponse response)
            throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        NewsModel deleteNews = HttpUtils.Of(request.getReader()).toModel(NewsModel.class);
       iNewsService.delete(deleteNews.getIds());

        objectMapper.writeValue(response.getOutputStream(),deleteNews);
        doDelete(request,response);
    }
}
