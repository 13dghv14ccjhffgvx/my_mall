package com.qfedu.mtlms.servlets;

import com.qfedu.mtlms.dto.Brand;
import com.qfedu.mtlms.dto.Category;
import com.qfedu.mtlms.service.BrandService;
import com.qfedu.mtlms.service.CategoryService;
import com.qfedu.mtlms.vo.ResultVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Description 添加品牌信息
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/BrandAddServlet")
public class BrandAddServlet extends HttpServlet {

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
        //1.接收品牌信息
        String brandName = request.getParameter("brandName");
        String brandLogoPath = request.getParameter("brandLogoPath");
        String brandDesc = request.getParameter("brandDesc");
        int brandStatus = Integer.parseInt( request.getParameter("brandStatus") );
        Brand brand = new Brand(0, brandName, brandLogoPath, brandDesc, new Date(), brandStatus);
        //2.接收选择的分类ID
        int categoryId = Integer.parseInt( request.getParameter("categoryId") );
        //3.执行保存
        boolean b = brandService.addBrand(brand, categoryId);
        String tips = b?"<label style='color:green'>品牌添加成功！</label>":
                "<label style='color:red'>品牌添加失败！</label>";

        //4.跳转到 brand_list.jsp
        List<Category> categoryList = categoryService.listCategories();
        int cid = categoryList.get(0).getCategoryId() ;
        List<Brand> brandList = brandService.listBrandsByCategoryId(cid);

        request.setAttribute("tips",tips);
        request.setAttribute("categoryList",categoryList);
        request.setAttribute("cid",cid);
        request.setAttribute("brandList",brandList);
        request.getRequestDispatcher("brand_list.jsp").forward(request,response);
    }
}
