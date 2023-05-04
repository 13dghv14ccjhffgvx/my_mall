package com.qfedu.mtlms.servlets;

import com.qfedu.mtlms.dto.Role;
import com.qfedu.mtlms.service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description 角色管理
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/RoleListServlet")
public class RoleListServlet extends HttpServlet {

    private RoleService roleService = new RoleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.查询所有的角色信息
        List<Role> roleList = roleService.getRoles();
        //2.将角色列表传递到 admin_role_list.jsp
        request.setAttribute("roleList",roleList);
        request.getRequestDispatcher("admin_role_list.jsp").forward(request,response);
    }
}
