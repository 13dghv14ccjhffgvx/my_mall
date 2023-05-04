package com.qfedu.test.service;

import com.qfedu.mtlms.dto.InfoDetail;
import com.qfedu.mtlms.service.InfoDetailService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class InfoDetailServiceTest {

    private InfoDetailService infoDetailService = new InfoDetailService();

    @Test
    public void listInfoDetailByBasicInfo() {
        List<InfoDetail> infoDetailList = infoDetailService.listInfoDetailByBasicInfo(1);
        System.out.println(infoDetailList);
    }

    @Test
    public void testSaveInfoDetail(){
        InfoDetail infoDetail = new InfoDetail(0, "32G", "流程运行各种游戏");
        boolean b = infoDetailService.saveInfoDetail(infoDetail, 4);
        System.out.println(b);

    }
}