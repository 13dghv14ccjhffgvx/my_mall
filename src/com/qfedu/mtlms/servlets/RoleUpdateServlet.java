package com.qfedu.mtlms.servlets;

import com.qfedu.mtlms.dto.Menu1;
import com.qfedu.mtlms.dto.Menu2;
import com.qfedu.mtlms.dto.Role;
import com.qfedu.mtlms.service.MenuService;
import com.qfedu.mtlms.service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description 修改角色信息
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/RoleUpdateServlet")
public class RoleUpdateServlet extends HttpServlet {

    private RoleService roleService = new RoleService();
    private MenuService menuService = new MenuService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //1.接收修改后的角色信息
        request.setCharacterEncoding("UTF-8");
        int roleId = Integer.parseInt( request.getParameter("roleId") );
        String roleName = request.getParameter("roleName");
        String roleDesc = request.getParameter("roleDesc");
        Role role = new Role(roleId, roleName, roleDesc);

        //2.获取传递过来的选择的菜单的ID
        String[] menuIds = request.getParameterValues("menuId");

        //3.执行修改
        boolean b = roleService.updateRole(role, menuIds);

        //4.跳转到提示页面进行提示
        String tips = b?"<label style='color:green'>修改角色信息成功！</label>"
                :"<label style='color:red'>修改角色信息失败！</label>";
        request.setAttribute("tips",tips);
        request.getRequestDispatcher("prompt.jsp").forward(request,response);
    }
}
