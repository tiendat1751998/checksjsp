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
        CategoryModel category = FormUtils.toModel(CategoryModel.class, req);

        if (newsModel.getType().equals(SystemConstant.LIST)) {
            Pageble pageble = new PageRequest(newsModel.getPage(), newsModel.getMaxPageItem(), new Sorter(newsModel.getSortName(), newsModel.getSortBy()));

            // Kiểm tra giá trị mặc định
            if (newsModel.getPage() == null) {
                newsModel.setPage(1);
            }
            if (newsModel.getMaxPageItem() == null) {
                newsModel.setMaxPageItem(5);
            }

            newsModel.setListResult(iNewsService.findAll(pageble));
            newsModel.setTotalItem(iNewsService.getTotalItem());
            System.out.println(newsModel.toString());
            // Đảm bảo totalPage >= 1
//            int totalPage = (int) Math.ceil((double) newsModel.getTotalItem() / newsModel.getMaxPageItem());
//            newsModel.setTotalPage(totalPage > 0 ? totalPage : 1);
            int totalItem = iNewsService.getTotalItem();
            int totalPage = (int) Math.ceil((double) totalItem / newsModel.getMaxPageItem());
            newsModel.setTotalItem(totalItem);
            newsModel.setTotalPage(Math.max(totalPage, 1));

            req.setAttribute(SystemConstant.MODEL, newsModel);
            view = "/views/admin/new/list.jsp";

        } else if (newsModel.getType().equals(SystemConstant.EDIT)) {
//            newsModel.setCategoryid(Long.parseLong(req.getParameter("")));

            newsModel.setId(Long.parseLong(req.getParameter("id")));
            if (newsModel.getId() != null) {
                // Make sure this returns a non-null object
                newsModel = iNewsService.findOne(newsModel.getId());

            }
            req.setAttribute(SystemConstant.MODEL, newsModel);
            List<CategoryModel> categories = categoryService.findAll();
            System.out.println("Categories loaded: " + categories.size()); // Debugging

            req.setAttribute("categories", categories);
            view = "/views/admin/new/edit.jsp";

        } else if (newsModel.getType().equals(SystemConstant.DELETE)) {
            String[] ids = req.getParameterValues("ids"); // Get the array of IDs
            if (ids != null && ids.length > 0) {
                long[] longIds = new long[ids.length];
                for (int i = 0; i < ids.length; i++) {
                    longIds[i] = Long.parseLong(ids[i]); // Convert to long
                }
                iNewsService.delete(longIds); // Call the delete method
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"status\":\"success\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"status\":\"error\",\"message\":\"No IDs provided\"}");
            }
            return;
        }

        req.setAttribute(SystemConstant.MODEL, newsModel);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(view);
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        NewsModel newsModel = FormUtils.toModel(NewsModel.class, req);
        String idStr = req.getParameter("id");
        if (SystemConstant.EDIT.equals(newsModel.getType())) {
            newsModel= iNewsService.findOne(Long.parseLong(idStr));
            iNewsService.update(newsModel);
            resp.sendRedirect(req.getContextPath() + "/admin-new?type=EDIT&id="+idStr);
        } else if (SystemConstant.DELETE.equals(newsModel.getType())) {
            long id = newsModel.getId();
            iNewsService.delete(new long[]{id});
            resp.sendRedirect(req.getContextPath() + "/admin-new?type=delete");
        } else {
            iNewsService.save(newsModel);
            resp.sendRedirect(req.getContextPath() + "/admin-new?type=add");
        }
    }
}
