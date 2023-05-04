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
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 批量删除角色信息
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/RoleMutilDeleteServlet")
public class RoleMutilDeleteServlet extends HttpServlet {

    private RoleService roleService = new RoleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.接收点击“批量删除”提交的角色ID
        String[] roleIds = request.getParameterValues("roleId");
        //2.遍历roleIds，依次执行删除
        // failIds存储的是删除失败的角色ID
        List<Integer> failIds = new ArrayList<>();
        for (int i = 0; i <roleIds.length ; i++) {
            int roleId = Integer.parseInt( roleIds[i] );
            boolean b = roleService.deleteRole(roleId);
            if(b == false){
                failIds.add(roleId);
            }
        }

        //提示信息
        String ids = "";
        for (Integer id: failIds) {
            ids += ","+id;
        }
        String tips = failIds.size()==0?"多个角色删除成功！": ids+"删除失败！";
        request.setAttribute("tips",tips);
        //角色列表
        List<Role> roleList = roleService.getRoles();
        request.setAttribute("roleList",roleList);

        request.getRequestDispatcher("admin_role_list.jsp").forward(request,response);
    }

}
