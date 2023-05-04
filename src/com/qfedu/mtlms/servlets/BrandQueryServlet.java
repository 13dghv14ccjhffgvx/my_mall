package com.qfedu.mtlms.servlets;

import com.qfedu.mtlms.dto.Brand;
import com.qfedu.mtlms.dto.Category;
import com.qfedu.mtlms.service.BrandService;
import com.qfedu.mtlms.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Description 查询品牌信息
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/BrandQueryServlet")
public class BrandQueryServlet extends HttpServlet {

    private CategoryService categoryService = new CategoryService();
    private BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.接收品牌ID
        int brandId = Integer.parseInt( request.getParameter("brandId") );
        //2.根据ID查询品牌信息（如果支持修改分类的话，也需要查询对应的分类列表）
        Brand brand = brandService.getBrandById(brandId);
        //3.传递到修改页面
        request.setAttribute("brand",brand);
        request.getRequestDispatcher("brand_modify.jsp").forward(request,response);
    }
}
