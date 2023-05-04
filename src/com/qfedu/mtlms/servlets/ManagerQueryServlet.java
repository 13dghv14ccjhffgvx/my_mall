package com.qfedu.mtlms.servlets;

import com.google.gson.Gson;
import com.qfedu.mtlms.dto.Manager;
import com.qfedu.mtlms.dto.Role;
import com.qfedu.mtlms.service.ManagerService;
import com.qfedu.mtlms.service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * @Description 查询管理员
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/ManagerQueryServlet")
public class ManagerQueryServlet extends HttpServlet {

    private ManagerService managerService = new ManagerService();
    private RoleService roleService = new RoleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.获取管理员ID
        String mgrId = request.getParameter("managerId");

        //2.调用managerService查询管理员原始信息
        Manager manager = managerService.getManagerById(mgrId);
        String jsonStr = new Gson().toJson(manager);

        //3.响应ajax请求
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }
}
