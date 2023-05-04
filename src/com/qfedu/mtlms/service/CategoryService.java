package com.qfedu.mtlms.service;

import com.qfedu.mtlms.dao.BrandDAO;
import com.qfedu.mtlms.dao.CategoryDAO;
import com.qfedu.mtlms.dto.Brand;
import com.qfedu.mtlms.dto.Category;

import java.util.List;

/**
 * @Description 分类信息管理的业务处理
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class CategoryService {

    private CategoryDAO categoryDAO = new CategoryDAO();
    private BrandDAO brandDAO = new BrandDAO();

    /**
     * 查询分类列表
     * @return
     */
    public List<Category> listCategories(){
        List<Category> categoryList = categoryDAO.selectCategories();
        return categoryList;
    }

    /**
     * 删除分类逻辑
     * @param categoryId
     * @return
     */
    public boolean deleteCategory(int categoryId){
        //当执行一个分类信息删除操作时，需要先检查这个分类中是否存在品牌，如果存在品牌，则不能删除
        //根据分类ID查询 品牌信息列表，如果列表为空，则执行删除
        List<Brand> brandList = brandDAO.selectBrandsByCategoryId(categoryId);
        if(brandList.size()>0){
            return false;
        }else{
            int i = categoryDAO.deleteCategoryById(categoryId);
            if(i>0){
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * 根据ID查询一个分类信息
     * @param categoryId
     * @return
     */
    public Category getCategoryById(int categoryId){
        return categoryDAO.selectCategoryById(categoryId);
    }

    /**
     * 修改类别信息
     * @param category
     * @return
     */
    public boolean updateCategory(Category category){
        int i = categoryDAO.updateCategory(category);
        return i>0;
    }

    /**
     * 添加类别信息
     * @param category
     * @return
     */
    public boolean saveCategory(Category category){
        int i = categoryDAO.insertCategory(category);
        return i>0;
    }

}
