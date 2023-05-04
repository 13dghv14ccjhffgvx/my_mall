package com.qfedu.mtlms.dao;

import com.qfedu.mtlms.dto.Goods;
import com.qfedu.mtlms.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 完成商品的数据库操作
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class GoodsDAO {

    /**
     * 根据品牌ID，查询此品牌下的商品信息
     * @param brandId
     * @return
     */
    public List<Goods> selectGoodsByBrandId(int brandId){
        List<Goods> goodsList = new ArrayList<>();
        try {
            String sql = "select good_id goodsId,good_name goodsName,good_img goodsImg,good_cost goodsCost,good_min_price goodsMinPrice from tb_good INNER JOIN tb_brand_good on tb_good.good_id = tb_brand_good.fk_good_id where fk_brand_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            goodsList = queryRunner.query(sql,new BeanListHandler<Goods>(Goods.class),brandId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsList;
    }

    /**
     * 保存商品信息
     * @param goods
     * @return 返回添加的商品生成的ID
     */
    public int insertGoods(Goods goods) {
        int goodsId = 0;
        try {
            String sql = "insert into tb_good(good_name,good_img,good_cost,good_min_price) values(?,?,?,?)";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            BigInteger object = queryRunner.insert(sql, new ScalarHandler<>(), goods.getGoodsName(), goods.getGoodsImg(), goods.getGoodsCost(), goods.getGoodsMinPrice());
            goodsId = object.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsId;
    }

    /**
     * 保存商品与品牌的关联关系
     * @param goodsId
     * @param brandId
     * @return
     */
    public int insertGoodsAndBrand(int goodsId, int brandId) {
        int i = 0;
        try {
            String sql = "insert into tb_brand_good(fk_brand_id,fk_good_id) values(?,?)";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql,brandId,goodsId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 保存商品与选项的关联关系
     * @param goodsId
     * @param infoDetailId
     * @param price
     * @return
     */
    public int insertGoodsAndInfoDetail(int goodsId, int infoDetailId, int price) {
        int i = 0;
        try {
            String sql = "insert into tb_good_detail(fk_good_id,fk_info_detail_id,good_discount) values(?,?,?)";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql,goodsId,infoDetailId,price);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
