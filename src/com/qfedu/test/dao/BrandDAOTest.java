package com.qfedu.test.dao;

import com.qfedu.mtlms.dao.BrandDAO;
import com.qfedu.mtlms.dto.Brand;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class BrandDAOTest {

    private BrandDAO brandDAO = new BrandDAO();

    @Test
    public void selectBrandsByCategoryId() {
        List<Brand> brandList = brandDAO.selectBrandsByCategoryId(3);
        System.out.println(brandList);
    }

    @Test
    public void method(){

    }


    @Test
    public void deleteBrand(){
        int j = brandDAO.deleteCategoryAndBrand(17);
        int i = brandDAO.deleteBrand(17);
        System.out.println(i);
    }

}