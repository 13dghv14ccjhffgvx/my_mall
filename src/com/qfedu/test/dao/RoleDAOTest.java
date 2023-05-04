package com.qfedu.test.dao;

import com.qfedu.mtlms.dao.RoleDAO;
import com.qfedu.mtlms.dto.Role;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class RoleDAOTest {

    private RoleDAO roleDAO = new RoleDAO();

    @Test
    public void selectRoles() {
        List<Role> roleList = roleDAO.selectRoles();
        assertEquals(3,roleList.size());
    }

    @Test
    public void testInsertRole(){
        int i = roleDAO.insertRole(new Role(0, "客服11", "8888"));
        System.out.println(i);
    }

    @Test
    public void testInsertRoleAndMeun(){
        int i = roleDAO.insertRoleAndMeun(19, 1);
        System.out.println(i);
    }

    @Test
    public void testDeleteRoleAndMenu(){
        int i = roleDAO.deleteRoleAndMenuByRoleId(24);
        System.out.println(i);
    }

    @Test
    public void  testDeleteRole(){
        int i = roleDAO.deleteRoleByRoldId(24);
        System.out.println(i);
    }

    @Test
    public void testSelectRoleById(){
        Role role = roleDAO.selectRoleById(1);
        System.out.println(role);
    }

    @Test
    public void testMenuIdsRoleById(){
        List<Integer> list = roleDAO.selectMenuIdsByRoleId(1);
        System.out.println(list);
    }

}