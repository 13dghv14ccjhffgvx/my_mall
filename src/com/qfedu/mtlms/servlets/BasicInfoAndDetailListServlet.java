package com.qfedu.mtlms.servlets;

import com.qfedu.mtlms.dto.BasicInfo;
import com.qfedu.mtlms.dto.InfoDetail;
import com.qfedu.mtlms.service.BasicInfoService;
import com.qfedu.mtlms.service.InfoDetailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description 查询类目以及类目下的选项信息
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/BasicInfoAndDetailListServlet")
public class BasicInfoAndDetailListServlet extends HttpServlet {

    private BasicInfoService basicInfoService = new BasicInfoService();
    private InfoDetailService infoDetailService = new InfoDetailService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.查询所有的评估类目
        List<BasicInfo> basicInfoList = basicInfoService.listBasicInfos();

        //2.遍历所有类目
        for (int i = 0; i < basicInfoList.size() ; i++) {
            BasicInfo basicInfo = basicInfoList.get(i);
            //根据评估类目的ID查询此评估类目下的选项
            List<InfoDetail> infoDetailList = infoDetailService.listInfoDetailByBasicInfo(basicInfo.getBasicInfoId());
            //将此评估类目下的选项存储到basicInfo对象中
            basicInfo.setInfoDetailList(infoDetailList);
        }

        //3.将评估类目集合传递到商品添加页面  products_add.jsp
        request.setAttribute("basicInfoList",basicInfoList);
        request.getRequestDispatcher("products_add.jsp").forward(request,response);
    }
}
