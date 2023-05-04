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
 * @Description 添加评估选项
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/InfoDetailAddServlet")
public class InfoDetailAddServlet extends HttpServlet {

    private InfoDetailService infoDetailService = new InfoDetailService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.接收ajax提交过来的评估选项信息
        int basicInfoId = Integer.parseInt( request.getParameter("basicInfoId") );
        String infoDetailName = request.getParameter("infoDetailName");
        String infoDetailDesc = request.getParameter("infoDetailDesc");
        InfoDetail infoDetail = new InfoDetail(0,infoDetailName,infoDetailDesc);

        //2.调用InfoDetailService保存评估选项信息
        boolean b = infoDetailService.saveInfoDetail(infoDetail, basicInfoId);

        //3.响应ajax请求
        ResultVO vo = b ? new ResultVO(1000,"success"): new ResultVO(1001,"fail");
        String jsonStr = new Gson().toJson(vo);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }
}
