package com.qfedu.test.dao;

import com.qfedu.mtlms.dao.MenuDAO;
import com.qfedu.mtlms.dto.Menu1;
import com.qfedu.mtlms.dto.Menu2;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class MenuDAOTest {

    private MenuDAO menuDAO = new MenuDAO();

    @Test
    public void selectFirstLevelMenusByMgrId() {
        List<Menu1> menu1List = menuDAO.selectFirstLevelMenusByMgrId("10000001");
        assertEquals(9,menu1List.size());
    }

    @Test
    public void testSelectMenu2ByMgrIdAndParentCode(){
        List<Menu2> menu2List = menuDAO.selectMenu2ByMgrIdAndParentCode("10000001", "01");
        assertEquals(3,menu2List.size());
    }

    @Test
    public void testSelectMenu1(){
        List<Menu1> menu1List = menuDAO.selectMenu1();
        System.out.println(menu1List);
    }

    @Test
    public void testSelectMenu2(){
        List<Menu2> menu2List = menuDAO.selectMenu2();
        System.out.println(menu2List);
    }

    @Test
    public void testSelectMenu2ByMenu1Code(){
        List<Menu2> menu2List = menuDAO.selectMenu2ByMenu1Code("02");
        System.out.println(menu2List);
    }

    @Test
    public void testupdateMenuState(){
        int i = menuDAO.updateMenuState("0101", 1);
        assertEquals(1,i);
    }
}