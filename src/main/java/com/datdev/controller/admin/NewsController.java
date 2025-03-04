package com.datdev.controller.admin;

import com.datdev.constant.SystemConstant;
import com.datdev.model.CategoryModel;
import com.datdev.model.NewsModel;
import com.datdev.paging.PageRequest;
import com.datdev.paging.Pageble;
import com.datdev.service.ICategoryService;
import com.datdev.service.INewsService;
import com.datdev.sort.Sorter;
import com.datdev.utils.FormUtils;
import com.datdev.utils.MessageUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin-new")
public class NewsController extends HttpServlet {
    private static final long serialVersionUID = 241231231231232L;

    @Inject
    private INewsService iNewsService;
    @Inject
    private ICategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String view = "";
        NewsModel newsModel = FormUtils.toModel(NewsModel.class, req);
        if (SystemConstant.LIST.equals(newsModel.getType())) {
            Pageble pageble = new PageRequest(newsModel.getPage(), newsModel.getMaxPageItem(), new Sorter(newsModel.getSortName(), newsModel.getSortBy()));
            if (newsModel.getPage() == null) {
                newsModel.setPage(1);
            }
            if (newsModel.getMaxPageItem() == null) {
                newsModel.setMaxPageItem(5);
            }
            newsModel.setListResult(iNewsService.findAll(pageble));
            newsModel.setTotalItem(iNewsService.getTotalItem());

            int totalItem = iNewsService.getTotalItem();
            int totalPage = (int) Math.ceil((double) totalItem / newsModel.getMaxPageItem());
            newsModel.setTotalItem(totalItem);
            newsModel.setTotalPage(Math.max(totalPage, 1));

            req.setAttribute(SystemConstant.MODEL, newsModel);
            view = "/views/admin/new/list.jsp";

        } else if (SystemConstant.EDIT.equals(newsModel.getType())) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                try {
                    newsModel.setId(Long.parseLong(idStr));
                    newsModel = iNewsService.findOne(newsModel.getId());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            req.setAttribute(SystemConstant.MODEL, newsModel);
            List<CategoryModel> categories = categoryService.findAll();
            req.setAttribute("categories", categories);
            view = "/views/admin/new/edit.jsp";
        } else if (SystemConstant.ADD.equals(newsModel.getType())) {
            req.setAttribute(SystemConstant.MODEL, newsModel);
            List<CategoryModel> categories = categoryService.findAll();
            req.setAttribute("categories", categories);
            view = "/views/admin/new/edit.jsp";

        }
        MessageUtil.showMessage(req);
        req.setAttribute(SystemConstant.MODEL, newsModel);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(view);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
