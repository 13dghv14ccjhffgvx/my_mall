package com.qfedu.mtlms.servlets;

import com.google.gson.Gson;
import com.qfedu.mtlms.dto.BasicInfo;
import com.qfedu.mtlms.service.BasicInfoService;
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
 * @Description 接收ajax请求，查询评估类目列表，响应json格式的数据
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/BasicInfoLoadServlet")
public class BasicInfoLoadServlet extends HttpServlet {

    private BasicInfoService basicInfoService = new BasicInfoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.查询评估类目信息
        List<BasicInfo> basicInfoList = basicInfoService.listBasicInfos();

        //2.响应ajax请求，将basicInfoList转换成JSON格式响应给页面的ajax请求
        ResultVO vo =  basicInfoList!=null ? new ResultVO(1000,"success",basicInfoList):
                new ResultVO(1001,"fail") ;

        // 查询成功： {code:1000, msg:success, data:[{},{},{}]}
        // 查询失败： {code:1001, msg:fail, data:null}
        String jsonStr = new Gson().toJson(vo);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }
}
