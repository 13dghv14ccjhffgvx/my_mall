package com.qfedu.test.dao;

import com.qfedu.mtlms.dao.CategoryDAO;
import com.qfedu.mtlms.dto.Category;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class CategoryDAOTest {

    private CategoryDAO categoryDAO = new CategoryDAO();

    @Test
    public void selectCategories() {
        List<Category> categories = categoryDAO.selectCategories();
        System.out.println(categories);
    }

    @Test
    public void deleteCategoryById() {
        int i = categoryDAO.deleteCategoryById(10);
        assertEquals(1,i);
    }

    @Test
    public void selectCategoryById() {
        Category category = categoryDAO.selectCategoryById(10);
        System.out.println(category);
    }

    @Test
    public void updateCategory() {
        Category category = new Category(10, "机车", "bbb.png", "1");
        int i = categoryDAO.updateCategory(category);
        assertEquals(1,i);
    }

    @Test
    public void insertCategory() {
        Category category = new Category(0, "摩托车", "aaa.png", "1");
        int i = categoryDAO.insertCategory(category);
        assertEquals(1,i);
    }
}