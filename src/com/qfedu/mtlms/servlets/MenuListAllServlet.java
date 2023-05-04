package com.qfedu.mtlms.servlets;

import com.qfedu.mtlms.dto.Menu1;
import com.qfedu.mtlms.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description 角色管理-添加新角色-查询菜单列表
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/MenuListAllServlet")
public class MenuListAllServlet extends HttpServlet {

    private MenuService menuService = new MenuService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.查询所有的系统菜单
        List<Menu1> menu1List = menuService.listAllMenus();
        //2.将查询到的系统菜单集合传递到 admin_role_add.jsp
        request.setAttribute("menu1List",menu1List);
        request.getRequestDispatcher("admin_role_add.jsp").forward(request,response);
    }
}
