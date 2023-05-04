package com.qfedu.mtlms.service;

import com.qfedu.mtlms.dao.BrandDAO;
import com.qfedu.mtlms.dao.GoodsDAO;
import com.qfedu.mtlms.dto.Brand;
import com.qfedu.mtlms.dto.Goods;

import java.util.List;

/**
 * @Description 完成品牌的业务处理
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class BrandService {

    private BrandDAO brandDAO = new BrandDAO();
    private GoodsDAO goodsDAO = new GoodsDAO();

    /**
     * 根据分类ID查询当前分类下的品牌信息
     * @return
     */
    public List<Brand> listBrandsByCategoryId(int categoryId){
        List<Brand> brandList = brandDAO.selectBrandsByCategoryId(categoryId);
        return brandList;
    }

    /**
     * 保存品牌信息
     * @param brand
     * @param categoryId
     * @return
     */
    public boolean addBrand(Brand brand,int categoryId){
        //保存品牌信息，获取生成的品牌ID
        int brandId = brandDAO.insertBrand(brand);
        //保存品牌与分类之间的关联关系
        int i = brandDAO.insertCategoryAndBrand(categoryId, brandId);
        //返回执行结果
        return i>0;
    }

    /**
     * 根据品牌ID查询品牌信息
     * @param brandId
     * @return
     */
    public Brand getBrandById(int brandId){
        Brand brand = brandDAO.selectBrandByBrandId(brandId);
        return brand;
    }

    /**
     * 修改品牌信息（不允许修改当前品牌所属的分类）
     * @param brand
     * @return
     */
    public boolean updateBrand(Brand brand){
        int i = brandDAO.updateBrand(brand);
        return i>0;
    }

    /**
     * 根据品牌ID删除品牌信息，需要保证这个品牌下没有商品信息方可删除
     * @param brandId
     * @return
     */
    public boolean deleteBrandById(int brandId){
        //1.根据当前品牌ID查询商品信息
        List<Goods> goodsList = goodsDAO.selectGoodsByBrandId(brandId);
        //2.如果goodsList为空
        if(goodsList.size() == 0){
            //a.删除品牌和分类的关联关系
            int j = brandDAO.deleteCategoryAndBrand(brandId);
            if(j>0){
                //b.删除品牌信息
                int i = brandDAO.deleteBrand(brandId);
                return i>0;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

}
