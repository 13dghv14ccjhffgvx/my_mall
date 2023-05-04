package com.qfedu.mtlms.servlets;

import com.qfedu.mtlms.dto.Brand;
import com.qfedu.mtlms.dto.Category;
import com.qfedu.mtlms.service.BrandService;
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
 * @Description 品牌列表
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/BrandListServlet")
public class BrandListServlet extends HttpServlet {

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
        //1.接收分类ID
        String categoryId = request.getParameter("categoryId");
        //2.查询所有的分类列表
        List<Category> categoryList = categoryService.listCategories();
        //3.根据分类ID，查询当前分类下的品牌信息，如果categoryId==null,则默认查询分类列表中第一个分类下的品牌
        int cid = categoryId==null ? categoryList.get(0).getCategoryId() : Integer.parseInt( categoryId );
        List<Brand> brandList = brandService.listBrandsByCategoryId(cid);
        //4. 将分类列表、当前显示的分类ID、分类ID下的品牌列表传递到 brand_list.jsp
        request.setAttribute("categoryList",categoryList);
        request.setAttribute("cid",cid);
        request.setAttribute("brandList",brandList);
        request.getRequestDispatcher("brand_list.jsp").forward(request,response);
    }
}
