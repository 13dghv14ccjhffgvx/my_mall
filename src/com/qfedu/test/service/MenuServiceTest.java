package com.qfedu.test.service;

import com.qfedu.mtlms.dto.Menu1;
import com.qfedu.mtlms.dto.Menu2;
import com.qfedu.mtlms.service.MenuService;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Description
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class MenuServiceTest {

    private MenuService menuService = new MenuService();

    @Test
    public void listMenusByMgrId() {

        List<Menu1> menu1List = menuService.listMenusByMgrId("10000002");

        System.out.println(menu1List);
    }


    @Test
    public void testListMenus(){
        Map<String, List> menus = menuService.listMenus();
        List<Menu1> menu1List = menus.get("menu1List");
        List<Menu2> menu2List = menus.get("menu2List");
        System.out.println();
    }

    @Test
    public void testListMenu2ByMenu1Code(){
        List<Menu2> menu2List = menuService.listMenu2ByMenu1Code("09");
        System.out.println(menu2List);
    }

    @Test
    public void testEnableOrDisableMenu(){
        boolean b1 = menuService.enableMenu("0101");
        boolean b2 = menuService.disableMenu("0101");
        assertTrue(b2);
    }

    @Test
    public void testListAllMenus(){
        List<Menu1> menu1List = menuService.listAllMenus();
        System.out.println(menu1List);
    }

}