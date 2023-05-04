package com.qfedu.mtlms.servlets;

import com.qfedu.mtlms.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/MenuStateChangeServlet")
public class MenuStateChangeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收要修改的menuCode 和 要做的操作
        String menuCode = request.getParameter("menuCode");
        String oper = request.getParameter("oper");
        //2.根据oper执行停用/启用
        MenuService menuService = new MenuService();
        boolean b = false;
        if("stop".equals(oper)){
            //停用
            b = menuService.disableMenu(menuCode);
        }else if("start".equals(oper)){
            //启用
            b = menuService.enableMenu(menuCode);
        }
        //3.返回操作结果(响应ajax请求)
        // 构造JSON字符串来响应
        String jsonStr = b?"{\"code\":1000,\"msg\":\"success\"}":"{\"code\":1001,msg:\"fail\"}";

        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println(jsonStr);
        out.flush();
        out.close();
    }
}
