package com.qfedu.test.service;

import com.qfedu.mtlms.dto.BasicInfo;
import com.qfedu.mtlms.service.BasicInfoService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description 实现关于评估类目的业务处理
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class BasicInfoServiceTest {

    private BasicInfoService basicInfoService = new BasicInfoService();

    @Test
    public void listBasicInfos() {

        List<BasicInfo> basicInfoList = basicInfoService.listBasicInfos();
        System.out.println(basicInfoList);

    }
}