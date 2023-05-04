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
 * @Description 修改分类
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/CategoryUpdateServlet")
@MultipartConfig
public class CategoryUpdateServlet extends HttpServlet {

    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.接收修改页面提交的修改后的分类信息
        request.setCharacterEncoding("utf-8");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String categoryName = request.getParameter("categoryName");
        String categoryStatus = request.getParameter("status");
        //2.分类的图片信息
        String categoryIcon = request.getParameter("oldCategoryIcon");
        //接收修改后的图片信息
        Part part = request.getPart("categoryIcon");
        if(part.getSize() > 0){
            // 如果part.size的值为0，表示没有提交新的图片，则分类信息依然使用“oldCategoryIcon”
            // 保存新图片
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
            categoryIcon = "categoryImages/"+iconName;
        }
        //3.修改到数据库
        Category category = new Category(categoryId, categoryName, categoryIcon, categoryStatus);
        boolean b = categoryService.updateCategory(category);

        //4. 修改成功之后，跳转到category_list.jsp页面，要显示分类的列表信息
        String tips = b?"<label style='color:green'>修改分类成功！</label>":"<label style='color:red'>修改分类失败！</label>";
        List<Category> categoryList = categoryService.listCategories();

        request.setAttribute("tips",tips);
        request.setAttribute("categoryList",categoryList);
        request.getRequestDispatcher("category_list.jsp").forward(request,response);
    }
}
