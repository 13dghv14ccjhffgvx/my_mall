package com.qfedu.mtlms.utils;

import com.qfedu.mtlms.dto.Manager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Description 登录过滤器
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.获取用户请求的资源的url(用户请求的资源可能是非受限资源，也可能是受限资源)
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURL().toString();  // http://localhost/mtlms/login.jsp

        //2.从url中截取请求的资源名称（url字符串中最后一个/后面的部分）
        String path = url.substring( url.lastIndexOf("/")+1 ); // path = "login.jsp";
        // 放行路径 可以配置在web.xml中通过加载来判断并放行
        if("login.jsp".equals(path) || "ManagerLoginServlet".equals(path) || "CheckCodeServlet".equals(path) || path.endsWith(".js")
            || path.endsWith(".css") || path.endsWith(".jpg") || path.endsWith(".png") || path.endsWith(".bmp")){
            //如果url中的资源是非受限资源（不登陆也可以访问的资源）,则放行
            // 非受限资源：登录页面，登录页面的静态资源（js\css\image）,验证码的Servlet类路径，对登录进行校验的servlet类
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            //如果url中的资源是受限资源，则需要验证管理员是否登录（检查session'中是否有管理员信息）
            HttpSession session = request.getSession();
            Manager manager = (Manager) session.getAttribute("mgr");
            if(manager == null ){
                //跳转到登录页面，提示登录
                request.setAttribute("tips","<label style='color:purple'>小老弟，请先登录！</label>");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }else{
                //如果管理员登录则放行
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
