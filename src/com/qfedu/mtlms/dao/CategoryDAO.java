package com.qfedu.mtlms.dao;

import com.qfedu.mtlms.dto.Category;
import com.qfedu.mtlms.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.management.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 实现对分类信息的数据库操作
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class CategoryDAO {

    /**
     * 查询所有的分类信息列表
     * @return
     */
    public List<Category> selectCategories(){
        List<Category> list = new ArrayList<>();
        try {
            String sql = "select category_id categoryId,category_name categoryName, category_icon categoryIcon,category_status categoryStatus from tb_category";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            list = queryRunner.query(sql,new BeanListHandler<Category>(Category.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据分类ID删除一个分类
     * @param categoryId
     * @return
     */
    public int deleteCategoryById(int categoryId){
        int i = 0;
        try {
            String sql = "delete from tb_category where category_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql,categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }


    /**
     * 根据分类ID查询分类信息
     * @param categoryId
     * @return
     */
    public Category selectCategoryById(int categoryId){
        Category category = null;
        try {
            String sql = "select category_id categoryId,category_name categoryName, category_icon categoryIcon,category_status categoryStatus from tb_category where category_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            category = queryRunner.query(sql,new BeanHandler<Category>(Category.class),categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    /**
     * 根据分类ID修改分类信息
     * 此修改操作：根據分類id修改分类名称 和 图标  （对于分类的状态，通过启用/停用来实现）
     * @param category
     * @return
     */
    public int updateCategory(Category category){
        int i = 0;
        try {
            String sql = "update tb_category set category_name=?,category_icon=?,category_status=? where category_id=?";
            Object[] params = {category.getCategoryName(),category.getCategoryIcon(),category.getCategoryStatus(),category.getCategoryId()};
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 添加分类信息
     * @param category
     * @return
     */
    public int insertCategory(Category category){
        int i = 0;
        try {
            String sql = "insert into tb_category(category_name,category_icon,category_status) values(?,?,1)";
            Object[] params = {category.getCategoryName(),category.getCategoryIcon()};
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

}
