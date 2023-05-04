package com.qfedu.mtlms.dao;

import com.qfedu.mtlms.dto.Brand;
import com.qfedu.mtlms.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 对品牌信息的数据库操作
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class BrandDAO {

    /**
     * 根据分类ID查询品牌列表
     * @param categoryId
     * @return
     */
    public List<Brand> selectBrandsByCategoryId(int categoryId){
        List<Brand> brandList = new ArrayList<>();
        try {
            String sql = "select b.brand_id brandId,b.brand_name brandName,b.brand_logo brandLogo,b.brand_desc brandDesc,b.create_time createTime,b.brand_status brandStatus from tb_category_brand cb INNER JOIN tb_brand b ON cb.fk_brand_id = b.brand_id where cb.fk_category_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            brandList = queryRunner.query(sql,new BeanListHandler<Brand>(Brand.class),categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brandList;
    }

    /**
     * 保存品牌信息
     * @param brand
     * @return 返回生成的品牌的ID
     */
    public int insertBrand(Brand brand){
        int brandId = 0;
        try {
            String sql = "insert into tb_brand(brand_name,brand_logo,brand_desc,create_time,brand_status) values(?,?,?,?,?)";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            BigInteger object = queryRunner.insert(sql,new ScalarHandler<>(),brand.getBrandName(),brand.getBrandLogo()
                    ,brand.getBrandDesc(), brand.getCreateTime(),brand.getBrandStatus());
            brandId = object.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brandId;
    }

    /**
     * 保存分类与品牌的关联关系
     * @param cid
     * @param bid
     * @return
     */
    public int insertCategoryAndBrand(int cid,int bid){
        int i = 0;
        try {
            String sql = "insert into tb_category_brand(fk_category_id,fk_brand_id) values(?,?)";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql,cid,bid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 根据id查询品牌信息
     * @param brandId
     * @return
     */
    public Brand selectBrandByBrandId(int brandId){
        Brand brand = null;
        try {
            String sql = "select brand_id brandId,brand_name brandName,brand_logo brandLogo,brand_desc brandDesc,create_time createTime,brand_status brandStatus from tb_brand where brand_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            brand = queryRunner.query(sql,new BeanHandler<Brand>(Brand.class),brandId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brand;
    }

    /**
     * 修改品牌信息
     * @param brand
     * @return
     */
    public int updateBrand(Brand brand){
        int i = 0;
        try {
            String sql = "update tb_brand set brand_name=?,brand_logo=?,brand_desc=?,create_time=?,brand_status=? where brand_id=?";
            Object[] params = {brand.getBrandName(),brand.getBrandLogo(),brand.getBrandDesc(),brand.getCreateTime(),brand.getBrandStatus(),brand.getBrandId()};
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 删除品牌与分类的关联
     * @param brandId
     * @return
     */
    public int deleteCategoryAndBrand(int brandId){
        int i = 0;
        try {
            String sql = "delete from tb_category_brand where fk_brand_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql,brandId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 根据品牌ID删除品牌信息
     * @param brandId
     * @return
     */
    public int deleteBrand(int brandId){
        int i = 0;
        try {
            String sql = "delete from tb_brand where brand_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql,brandId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

}
