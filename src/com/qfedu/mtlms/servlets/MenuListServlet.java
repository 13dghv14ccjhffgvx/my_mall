package com.qfedu.mtlms.servlets;

import com.qfedu.mtlms.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/MenuListServlet")
public class MenuListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.查询一级菜单和二级菜单
        MenuService menuService = new MenuService();
        Map<String, List> menus = menuService.listMenus();
        //2.将一级菜单和二级菜单传递到 admin_menu_list.jsp
        request.setAttribute("menu1List",menus.get("menu1List"));
        request.setAttribute("menu2List",menus.get("menu2List"));
        request.getRequestDispatcher("admin_menu_list.jsp").forward(request,response);
    }
}
