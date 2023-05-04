package com.qfedu.mtlms.servlets;

import com.qfedu.mtlms.dto.Manager;
import com.qfedu.mtlms.service.ManagerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Description 用于完成管理员登录验证的servlet
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/ManagerLoginServlet")
public class ManagerLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("--------------ManagerLoginServlet");
        //1.接收帐号、密码、验证码
        String loginName = request.getParameter("loginName");
        String loginPwd = request.getParameter("loginPwd");
        String checkCode = request.getParameter("checkCode");
        //a.从session中取出生成的正确的验证码
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        //b.将取出的正确的验证码和用户输入的验证码进行比较
        if( checkCode.equals(code)){
            //验证码正确：当验证码正确之后再校验账号和密码
            //2.调用ManagerService进行校验
            ManagerService managerService = new ManagerService();
            Manager manager = managerService.checkLogin(loginName, loginPwd);
            //3.判断验证结果
            if(manager == null){
                // 登录失败：跳转到登录页面并进行错误提示
                request.setAttribute("tips","<label style='color:red'>登录失败，账号或密码错误！</label>");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }else{
                // 登录成功，跳转到管理系统的首页
                session.setAttribute("mgr",manager);
                //response.sendRedirect("index.jsp");
                response.sendRedirect("index");
            }
        }else{
            //验证码错误：
            request.setAttribute("tips","<label style='color:red'>验证码输入错误！</label>");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }
    }
}
