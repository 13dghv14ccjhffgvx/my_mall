package com.qfedu.mtlms.servlets;

import com.qfedu.mtlms.dto.Category;
import com.qfedu.mtlms.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @Description 根据ID查询分类信息
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/CategoryQueryServlet")
public class CategoryQueryServlet extends HttpServlet {

    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1、接收点击【修改】之后传递过来的分类ID
        int categoryId = Integer.parseInt(request.getParameter("cid"));

        //2、调用CategoryService中的方法，根据id查询分类的信息
        Category category = categoryService.getCategoryById(categoryId);

        //3、将查询的分类信息传递到修改页面catetory_modify.jsp，显示出来
        request.setAttribute("category",category);
        request.getRequestDispatcher("category_modify.jsp").forward(request,response);
    }
}
