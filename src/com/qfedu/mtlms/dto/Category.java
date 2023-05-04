package com.qfedu.mtlms.dto;

/**
 * @Description 分类信息实体类
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class Category {

    private int categoryId;
    private String categoryName;
    private String categoryIcon;
    private String categoryStatus;  //实现分类启用和禁用

    public Category() {
    }

    public Category(int categoryId, String categoryName, String categoryIcon, String categoryStatus) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryIcon = categoryIcon;
        this.categoryStatus = categoryStatus;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public String getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(String categoryStatus) {
        this.categoryStatus = categoryStatus;
    }
}
