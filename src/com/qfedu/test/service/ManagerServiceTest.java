package com.qfedu.test.service;

import com.qfedu.mtlms.dto.Manager;
import com.qfedu.mtlms.service.ManagerService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description ManagerService测试类
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class ManagerServiceTest {

    private ManagerService managerService = new ManagerService();

    @Test
    public void checkLogin() {
        Manager manager = managerService.checkLogin("admin", "123123");
        assertNotNull(manager);
    }

    @Test
    public void testListManagers(){
        List<Manager> managerList = managerService.listManagers();
        System.out.println(managerList);
    }

}