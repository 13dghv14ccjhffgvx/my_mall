package com.qfedu.mtlms.servlets;

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
 * @Description 删除管理员
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/ManagerDeleteServlet")
public class ManagerDeleteServlet extends HttpServlet {

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
        //1.接收管理员ID
        String mgrId = request.getParameter("mgrId");
        //2.执行删除
        boolean b = managerService.deleteManager(mgrId);
        //3.响应ajax请求（响应json格式数据）
        String jsonStr = b?"{\"code\":1000,\"msg\":\"success\"}"
                :"{\"code\":1001,\"msg\":\"fail\"}";
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println(jsonStr);
        out.flush();
        out.close();
    }
}
