package com.qfedu.mtlms.servlets;

import com.qfedu.mtlms.dto.Role;
import com.qfedu.mtlms.service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description 删除角色信息
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/RoleDeleteServlet")
public class RoleDeleteServlet extends HttpServlet {

    private RoleService roleService = new RoleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收角色ID
        int roleId = Integer.parseInt( request.getParameter("roleId") );
        //2.调用RoleService执行删除
        boolean b = roleService.deleteRole(roleId);
        //3.删除成功之后，响应前端的ajax请求
        String str = b?"{\"code\":1000,\"msg\":\"success\"}" : "{\"code\":1001,\"msg\":\"fail\"}";
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println(str);
        out.flush();
        out.close();
    }

}
