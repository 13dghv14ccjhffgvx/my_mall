package com.qfedu.mtlms.servlets;

import com.google.gson.Gson;
import com.qfedu.mtlms.dto.Menu2;
import com.qfedu.mtlms.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Description 接收ajax请求，根据一级菜单查询二级菜单
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/Menu2ListByMenu1Servlet")
public class Menu2ListByMenu1Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收一级菜单菜单编号
        String parentCode = request.getParameter("parentCode");
        //2.根据parentCode查询二级菜单
        MenuService menuService = new MenuService();
        List<Menu2> menu2List = menuService.listMenu2ByMenu1Code(parentCode);
        //3.将menu2List集合以JSON格式响应给页面
        // a.将menu2List集合转换成JSON格式（使用GSON，条件GSON的依赖）
        Gson gson = new Gson();
        String jsonStr = gson.toJson(menu2List);
        // b.在servlet类通过输出流响应ajax请求
        response.setContentType("application/json;charset=utf-8");
        response.setContentType("utf-8");
        PrintWriter out = response.getWriter();
        out.println(jsonStr);
        out.flush();
        out.close();
    }
}
