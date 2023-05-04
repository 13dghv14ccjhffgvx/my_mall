package com.qfedu.mtlms.service;

import com.qfedu.mtlms.dao.GoodsDAO;
import com.qfedu.mtlms.dto.Goods;

import java.util.List;

/**
 * @Description 完成商品相关的业务
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class GoodsService {

    private GoodsDAO goodsDAO = new GoodsDAO();

    /**
     * 根据品牌ID查询商品列表
     * @param brandId
     * @return
     */
    public List<Goods> listGoodsByBrandId(int brandId) {
        return goodsDAO.selectGoodsByBrandId(brandId);
    }

    /**
     * 保存商品信息、以及与品牌的关联
     * @param goods
     * @param brandId
     * @return 生成的商品ID
     */
    public int saveGoods(Goods goods, int brandId) {
        //保存商品信息
        int goodsId = goodsDAO.insertGoods(goods);
        if(goodsId > 0){
            //保存商品与品牌的关联关系
            int i = goodsDAO.insertGoodsAndBrand(goodsId,brandId);
            if(i>0){
                return goodsId;
            }
        }
        return 0;
    }

    /**
     * 保存商品与选项的关联关系
     * @param goodsId
     * @param infoDetailId
     * @param price
     * @return
     */
    public boolean saveGoodsAndInfoDetail(int goodsId, int infoDetailId, int price) {
        int i  = goodsDAO.insertGoodsAndInfoDetail( goodsId,  infoDetailId,  price);
        return i>0;
    }
}
