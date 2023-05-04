package com.qfedu.mtlms.servlets;

import com.google.gson.Gson;
import com.qfedu.mtlms.dto.Category;
import com.qfedu.mtlms.service.CategoryService;
import com.qfedu.mtlms.vo.ResultVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

/**
 * @Description 文件上传
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/ImageUploadServlet")
@MultipartConfig
public class ImageUploadServlet extends HttpServlet {

    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //接收并保存文件
        Part part = request.getPart("brandLogoImg");
        //  a.给图片重新命名
        String header = part.getHeader("Content-Disposition");
        int index1 = header.lastIndexOf(".");
        int index2 = header.lastIndexOf("\"");
        String ext = header.substring(index1,index2); // .jpg
        String imageName = UUID.randomUUID().toString().replace("-","")+ext;
        //  b.获取categoryImages目录的路径（部署到服务器之后的路径）
        String dir = getServletContext().getRealPath("/upload");
        //  c.存储图片
        part.write(dir+"/"+imageName);

        //返回文件的访问路径
        // a.拼接上传的图片的访问路径
        String path = "upload/"+imageName;
        // b.将图片的路径响应给前端ajax请求
        ResultVO resultVO = new ResultVO(1000, path);
        String jsonStr = new Gson().toJson(resultVO);  //  ｛code:1000,msg:upload/aaa.jpg｝
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }
}
