package com.qfedu.mtlms.servlets;

import com.google.gson.Gson;
import com.qfedu.mtlms.dto.Category;
import com.qfedu.mtlms.service.CategoryService;
import com.qfedu.mtlms.vo.ResultVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

/**
 * @Description 删除分类
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/CategoryDeleteServlet")
public class CategoryDeleteServlet extends HttpServlet {

    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.接收ajax请求发送过来的要删除的分类的ID
        int categoryId = Integer.parseInt( request.getParameter("cid") );
        //2.执行删除(分类下没有品牌信息才会被成功的删除)
        boolean b = categoryService.deleteCategory(categoryId);
        //3.返回结果(响应ajax请求)

        //【如果要响应ajax请求，需要拼接json字符串 —— 使用类对象转换JSON】
        ResultVO resultVO = b ? new ResultVO(1000,"success") : new ResultVO(1001,"fail");
        String jsonStr = new Gson().toJson(resultVO);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }
}
