package com.datdev.controller.admin;

import com.datdev.constant.SystemConstant;
import com.datdev.model.NewsModel;
import com.datdev.paging.PageRequest;
import com.datdev.paging.Pageble;
import com.datdev.service.INewsService;
import com.datdev.sort.Sorter;
import com.datdev.utils.FormUtils;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin-new")
public class NewsController extends HttpServlet {
    private static final long serialVersionUID = 241231231231232L;

    @Inject
    private INewsService iNewsService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsModel newsModel = FormUtils.toModel(NewsModel.class, req);
        Pageble pageble = new PageRequest(newsModel.getPage(),newsModel.getMaxPageItem(),new Sorter(newsModel.getSortName(),newsModel.getSortBy()));

        // Kiểm tra giá trị mặc định
        if (newsModel.getPage() == null) {
            newsModel.setPage(1);
        }
        if (newsModel.getMaxPageItem() == null) {
            newsModel.setMaxPageItem(5);
        }

        Integer offset = (newsModel.getPage() - 1) * newsModel.getMaxPageItem();
        newsModel.setListResult(iNewsService.findAll(pageble));
        newsModel.setTotalItem(iNewsService.getTotalItem());

        // Đảm bảo totalPage >= 1
        int totalPage = (int) Math.ceil((double) newsModel.getTotalItem() / newsModel.getMaxPageItem());
        newsModel.setTotalPage(totalPage > 0 ? totalPage : 1);

        req.setAttribute(SystemConstant.MODEL, newsModel);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/new/list.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
