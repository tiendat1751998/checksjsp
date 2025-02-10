package com.datdev.controller.web.api;

import com.datdev.model.NewsModel;
import com.datdev.service.INewsService;
import com.datdev.utils.HttpUtils;
import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-web-new/"})
public class NewApi extends HttpServlet {
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
    private String removeAccent(String s) {
        String[][] map = {
                {"à", "á", "ạ", "ả", "ã", "â", "ầ", "ấ", "ậ", "ẩ", "ẫ", "ă", "ằ", "ắ", "ặ", "ẳ", "ẵ"},
                {"a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"},
                {"è", "é", "ẹ", "ẻ", "ẽ", "ê", "ề", "ế", "ệ", "ể", "ễ"},
                {"e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e"},
                {"ì", "í", "ị", "ỉ", "ĩ"},
                {"i", "i", "i", "i", "i"},
                {"ò", "ó", "ọ", "ỏ", "õ", "ô", "ồ", "ố", "ộ", "ổ", "ỗ", "ơ", "ờ", "ớ", "ợ", "ở", "ỡ"},
                {"o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o"},
                {"ù", "ú", "ụ", "ủ", "ũ", "ư", "ừ", "ứ", "ự", "ử", "ữ"},
                {"u", "u", "u", "u", "u", "u", "u", "u", "u", "u", "u"},
                {"ỳ", "ý", "ỵ", "ỷ", "ỹ"},
                {"y", "y", "y", "y", "y"},
                {"đ"},
                {"d"}
        };

        for (int i = 0; i < map[0].length; i++) {
            for (int j = 0; j < map[0][i].length(); j++) {
                s = s.replace(map[0][i].charAt(j), map[1][i].charAt(0));
            }
        }

        return s;
    }
}

