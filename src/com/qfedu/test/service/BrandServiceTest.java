package com.qfedu.test.service;

import com.qfedu.mtlms.dto.Brand;
import com.qfedu.mtlms.service.BrandService;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Description
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class BrandServiceTest {

    private BrandService brandService = new BrandService();

    @Test
    public void addBrand() {
        Brand brand = new Brand(0, "surface", "upload/aaa.png", "haha", new Date(1938123767887L), 1);
        boolean b = brandService.addBrand(brand, 4);
        System.out.println(b);
    }

    @Test
    public void testGetBrand(){
        Brand brand = brandService.getBrandById(1);
        System.out.println(brand);
    }

    @Test
    public void testUpdateBrand(){
        Brand brand1 = new Brand(15, "微软", "upload/ccc.png", "Microsoft", new Date(1234123412341L), 1);
        boolean b = brandService.updateBrand(brand1);
        System.out.println(b);
    }
}