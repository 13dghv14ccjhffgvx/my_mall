package com.qfedu.mtlms.servlets;

import com.qfedu.mtlms.dto.Category;
import com.qfedu.mtlms.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @Description 添加分类
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/CategoryAddServlet")
@MultipartConfig
public class CategoryAddServlet extends HttpServlet {

    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.接收分类名称
        request.setCharacterEncoding("utf-8");
        String categoryName = request.getParameter("categoryName");

        //2.接收分类图片
        Part part = request.getPart("categoryIcon");
        // 保存分类图片信息
        //  a.给图片重新命名
        String header = part.getHeader("Content-Disposition");
        int index1 = header.lastIndexOf(".");
        int index2 = header.lastIndexOf("\"");
        String ext = header.substring(index1,index2); // .jpg
        String iconName = UUID.randomUUID().toString().replace("-","")+ext;
        //  b.获取categoryImages目录的路径（部署到服务器之后的路径）
        String dir = getServletContext().getRealPath("/categoryImages");
        //  c.存储图片
        part.write(dir+"/"+iconName);

        //3.将分类信息保存到数据库
        Category category = new Category(0, categoryName, "categoryImages/" + iconName, "1");
        boolean b = categoryService.saveCategory(category);

        //4.当保存完成之后，跳转到category_list.jsp页面，要显示分类的列表信息
        String tips = b?"<label style='color:green'>添加分类成功！</label>":"<label style='color:red'>添加分类失败！</label>";
        List<Category> categoryList = categoryService.listCategories();

        request.setAttribute("tips",tips);
        request.setAttribute("categoryList",categoryList);
        request.getRequestDispatcher("category_list.jsp").forward(request,response);
    }
}
