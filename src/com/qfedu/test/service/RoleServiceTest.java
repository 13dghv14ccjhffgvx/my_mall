package com.qfedu.test.service;

import com.qfedu.mtlms.dto.Role;
import com.qfedu.mtlms.service.RoleService;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Description
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class RoleServiceTest {

    private RoleService roleService = new RoleService();

    @Test
    public void addRole() {
        String[] menuIds = {"1","2","3"};
        boolean b = roleService.addRole(new Role(0, "客服12", "999"), menuIds);
        assertTrue(b);
    }

    @Test
    public void testDeleteRole(){
        boolean b = roleService.deleteRole(23);
        System.out.println(b);
    }
}