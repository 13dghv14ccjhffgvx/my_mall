package com.qfedu.mtlms.servlets;

import com.qfedu.mtlms.dto.BasicInfo;
import com.qfedu.mtlms.dto.Goods;
import com.qfedu.mtlms.service.BasicInfoService;
import com.qfedu.mtlms.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description 添加商品信息
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
@WebServlet("/GoodsAddServlet")
public class GoodsAddServlet extends HttpServlet {

    private GoodsService goodsService = new GoodsService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.接收商品信息
        String goodsName = request.getParameter("goodsName");
        String goodsImg = request.getParameter("goodsImgPath");
        int goodsCost = Integer.parseInt(request.getParameter("goodsCost"));
        int goodsMinPrice = Integer.parseInt(request.getParameter("goodsMinPrice"));
        Goods goods = new Goods(0, goodsName, goodsImg, goodsCost, goodsMinPrice);

        //2.接收品牌ID
        int brandId = Integer.parseInt( request.getParameter("brandId") );

        //【保存商品信息】
        int goodsId = goodsService.saveGoods(goods,brandId);

        //3.接收选择的评估选项的ID
        String[] infoDetailIds = request.getParameterValues("infoDetailId");
        //遍历每一个评估选项，获取价格
        for (int i = 0; i < infoDetailIds.length; i++) {
            int infoDetailId = Integer.parseInt( infoDetailIds[i] );
            int price = Integer.parseInt( request.getParameter("price_"+infoDetailId) );
            //【保存商品与选项的关系】
            boolean b = goodsService.saveGoodsAndInfoDetail(goodsId,infoDetailId,price);
        }

        //4.跳转到商品列表页面，并传递提示信息
        String tips = goodsId>0 ? "<label style='color:green'>添加商品成功！</label>":
                "<label style='color:green'>添加商品失败！</label>";
        request.setAttribute("tips",tips);
        request.getRequestDispatcher("products_list.jsp").forward(request,response);
    }
}
