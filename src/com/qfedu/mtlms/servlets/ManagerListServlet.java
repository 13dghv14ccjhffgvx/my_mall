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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @Description 管理员列表
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/ManagerListServlet")
public class ManagerListServlet extends HttpServlet {

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
        //1.查询管理员信息列表
        List<Manager> managerList = managerService.listManagers();
        //2.查询角色信息列表
        List<Role> roleList = roleService.getRoles();
        //3.将管理员信息列表及角色信息列表传递到admin_manager_list.jsp
        request.setAttribute("managerList",managerList);
        request.setAttribute("roleList",roleList);
        request.getRequestDispatcher("admin_manager_list.jsp").forward(request,response);
    }
}
