package com.qfedu.mtlms.servlets;

import com.google.gson.Gson;
import com.qfedu.mtlms.dto.BasicInfo;
import com.qfedu.mtlms.dto.InfoDetail;
import com.qfedu.mtlms.service.BasicInfoService;
import com.qfedu.mtlms.service.InfoDetailService;
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
 * @Description 根据类目ID查询类目下的评估选项
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/InfoDetailListByBasicServlet")
public class InfoDetailListByBasicServlet extends HttpServlet {

    private InfoDetailService infoDetailService = new InfoDetailService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.接收ajax请求传递过来类目ID
        int basicInfoId = Integer.parseInt( request.getParameter("basicInfoId") );
        //2.根据类目ID查询选项
        List<InfoDetail> infoDetailList = infoDetailService.listInfoDetailByBasicInfo(basicInfoId);
        //3.响应ajax请求
        ResultVO vo = null;
        if(infoDetailList != null){
            vo = new ResultVO(1000,"success",infoDetailList);
        }else{
            vo = new ResultVO(1001,"fail");
        }
        String jsonStr = new Gson().toJson(vo);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }
}
