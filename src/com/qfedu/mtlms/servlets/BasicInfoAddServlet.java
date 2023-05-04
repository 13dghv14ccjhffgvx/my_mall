package com.qfedu.mtlms.servlets;

import com.qfedu.mtlms.dto.BasicInfo;
import com.qfedu.mtlms.service.BasicInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description 添加评估类目
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/BasicInfoAddServlet")
public class BasicInfoAddServlet extends HttpServlet {

    private BasicInfoService basicInfoService = new BasicInfoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.接收类目名称
        request.setCharacterEncoding("utf-8");
        String basicInfoName = request.getParameter("basicInfoName");
        BasicInfo basicInfo = new BasicInfo(0, basicInfoName, 1);

        //2.保存类目信息
        boolean b = basicInfoService.saveBasicInfo(basicInfo);

        //3.查询所有评估类目列表、跳转到‘basic_info_list.jsp’
        //a.添加操作的提示信息
        String tips = b?"<label style='color:green'>添加评估类目成功！</label>":
                "<label style='color:red'>添加评估类目失败！</label>";
        request.setAttribute("tips",tips);
        //b.类目列表
        List<BasicInfo> basicInfoList = basicInfoService.listBasicInfos();
        request.setAttribute("basicInfoList",basicInfoList);
        //c.跳转
        request.getRequestDispatcher("basic_info_list.jsp").forward(request,response);

    }
}
