package com.qfedu.test.dao;

import com.qfedu.mtlms.dao.ManagerDAO;
import com.qfedu.mtlms.dto.Manager;
import com.qfedu.mtlms.utils.MD5Utils;
import org.junit.Test;
import org.omg.CORBA.MARSHAL;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Description ManagerDAO的测试类
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class ManagerDAOTest {

    private ManagerDAO managerDAO = new ManagerDAO();

    @Test
    public void selectManagerByLoginName() {
        Manager manager = managerDAO.selectManagerByLoginName("admin");
        assertNotNull(manager);
    }

    @Test
    public void testInsertManager(){
        String s = MD5Utils.md5Encode("123123");
        Manager manager = new Manager("10000004","wangwu",s,"王五","男","13333333333","aaa@sohu.com","122332",new Date());
        int i = managerDAO.insertManager(manager);
        assertEquals(i,1);
    }

    @Test
    public void testInsertMgrAndRole(){
        int i = managerDAO.insertMgrAndRole("10000004", 1);
        assertEquals(i,1);
    }
}