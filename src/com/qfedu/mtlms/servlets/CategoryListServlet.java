package com.qfedu.mtlms.servlets;

import com.qfedu.mtlms.dto.Category;
import com.qfedu.mtlms.service.CategoryService;
import com.qfedu.mtlms.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Description 分类列表
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/CategoryListServlet")
public class CategoryListServlet extends HttpServlet {

    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.查询分类列表
        List<Category> categoryList = categoryService.listCategories();
        //2.将分类表传递到 category_list.jsp
        request.setAttribute("categoryList",categoryList);
        request.getRequestDispatcher("category_list.jsp").forward(request,response);
    }
}
