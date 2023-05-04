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
 * @Description 修改品牌信息
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/BrandUpdateServlet")
public class BrandUpdateServlet extends HttpServlet {

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
        //1.接收修改页面修改后的品牌信息
        request.setCharacterEncoding("utf-8");
        int brandId = Integer.parseInt( request.getParameter("brandId") );  //隐藏域
        String brandName = request.getParameter("brandName");
        String brandLogoPath = request.getParameter("brandLogoPath");
        String brandDesc = request.getParameter("brandDesc");
        int brandStatus = Integer.parseInt( request.getParameter("brandStatus") );
        Brand brand = new Brand(brandId, brandName, brandLogoPath, brandDesc, new Date(), brandStatus);

        //2.执行修改
        boolean b = brandService.updateBrand(brand);
        String tips = b?"<label style='color:green'>品牌修改成功！</label>":
                "<label style='color:red'>品牌修改失败！</label>";

        //3.跳转到 brand_list.jsp
        //a.查询分类列表
        List<Category> categoryList = categoryService.listCategories();
        //b.第一个分类ID
        int cid = categoryList.get(0).getCategoryId() ;
        //c.第一个分类ID下的品牌列表
        List<Brand> brandList = brandService.listBrandsByCategoryId(cid);

        request.setAttribute("tips",tips);
        request.setAttribute("categoryList",categoryList);
        request.setAttribute("cid",cid);
        request.setAttribute("brandList",brandList);
        request.getRequestDispatcher("brand_list.jsp").forward(request,response);
    }
}
