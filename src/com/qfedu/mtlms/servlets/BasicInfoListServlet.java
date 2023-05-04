package com.qfedu.mtlms.servlets;

import com.qfedu.mtlms.dto.BasicInfo;
import com.qfedu.mtlms.dto.Brand;
import com.qfedu.mtlms.dto.Category;
import com.qfedu.mtlms.service.BasicInfoService;
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
 * @Description 查询评估类目列表
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/BasicInfoListServlet")
public class BasicInfoListServlet extends HttpServlet {

    private BasicInfoService basicInfoService = new BasicInfoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.查询所有评估类目列表
        List<BasicInfo> basicInfoList = basicInfoService.listBasicInfos();

        //2.将类目列表信息传递到‘basic_info_list.jsp’
        request.setAttribute("basicInfoList",basicInfoList);
        request.getRequestDispatcher("basic_info_list.jsp").forward(request,response);
    }
}
