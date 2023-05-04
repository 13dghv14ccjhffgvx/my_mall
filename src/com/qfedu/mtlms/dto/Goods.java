package com.qfedu.mtlms.dto;

/**
 * @Description 商品信息实体类
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class Goods {

    private int goodsId;
    private String goodsName;
    private String goodsImg;
    private int goodsCost;
    private int goodsMinPrice;

    public Goods() {
    }

    public Goods(int goodsId, String goodsName, String goodsImg, int goodsCost, int goodsMinPrice) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsImg = goodsImg;
        this.goodsCost = goodsCost;
        this.goodsMinPrice = goodsMinPrice;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public int getGoodsCost() {
        return goodsCost;
    }

    public void setGoodsCost(int goodsCost) {
        this.goodsCost = goodsCost;
    }

    public int getGoodsMinPrice() {
        return goodsMinPrice;
    }

    public void setGoodsMinPrice(int goodsMinPrice) {
        this.goodsMinPrice = goodsMinPrice;
    }
}
