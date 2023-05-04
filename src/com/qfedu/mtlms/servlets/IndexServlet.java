package com.qfedu.mtlms.servlets;

import com.qfedu.mtlms.dto.Manager;
import com.qfedu.mtlms.dto.Menu1;
import com.qfedu.mtlms.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @Description
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    private MenuService menuService = new MenuService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.根据当前登录的管理员ID，查询管理员的菜单列表
        //a.从session中获取当前管理员的id
        HttpSession session = request.getSession();
        Manager manager = (Manager) session.getAttribute("mgr");
        String mgrId = manager.getMgrId();
        //b.调用MenuService查询当前管理员的菜单
        List<Menu1> menu1List = menuService.listMenusByMgrId(mgrId);

        //2.将菜单的集合传递到index.jsp
        request.setAttribute("menu1List",menu1List);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
