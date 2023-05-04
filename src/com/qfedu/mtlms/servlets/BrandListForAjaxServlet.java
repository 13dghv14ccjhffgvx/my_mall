package com.qfedu.mtlms.servlets;

import com.google.gson.Gson;
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
import java.io.PrintWriter;
import java.util.List;

/**
 * @Description 查询品牌信息，响应ajax请求
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/BrandListForAjaxServlet")
public class BrandListForAjaxServlet extends HttpServlet {

    private BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.接收分类ID，根据分类ID查询品牌信息
        int categoryId = Integer.parseInt( request.getParameter("categoryId") );
        List<Brand> brandList = brandService.listBrandsByCategoryId(categoryId);

        //2.转换成json格式
        ResultVO resultVO = brandList != null ?
                new ResultVO(1000,"success",brandList) :
                new ResultVO(1001,"fail");
        String jsonStr = new Gson().toJson(resultVO);

        //3.响应ajax请求
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }
}
