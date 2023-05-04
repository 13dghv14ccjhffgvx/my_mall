package com.qfedu.mtlms.service;

import com.qfedu.mtlms.dao.RoleDAO;
import com.qfedu.mtlms.dto.Role;

import java.util.List;

/**
 * @Description 角色信息的业务处理
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class RoleService {

    private RoleDAO roleDAO = new RoleDAO();

    /**
     * 调用RoleDAO查询角色列表
     * @return
     */
    public List<Role> getRoles(){
        List<Role> roleList = roleDAO.selectRoles();
        return roleList;
    }

    /**
     * 添加角色信息
     * @param role
     * @param menuIds
     * @return
     */
    public boolean addRole(Role role,String[] menuIds){
        boolean b = true;
        //1.保存角色信息，获取生成的角色的ID
        int roleId = roleDAO.insertRole(role);
        //2.保存角色和菜单的关联
        if(menuIds != null){
            for (int i = 0; i < menuIds.length; i++) {
                int meunId = Integer.parseInt(menuIds[i]);
                int j = roleDAO.insertRoleAndMeun(roleId, meunId);
                b = b&& j>0;
            }
        }
        return b;
    }

    /**
     * 根据角色ID删除角色信息
     * @param roleId
     * @return
     */
    public boolean deleteRole(int roleId){
        int i = roleDAO.deleteRoleAndMenuByRoleId(roleId);
        int j = roleDAO.deleteRoleByRoldId(roleId);
        boolean b = j>0;
        return b;
    }

    /**
     * 根据角色ID查询角色信息
     * @param roleId
     * @return
     */
    public Role getRoleById(int roleId){
        return roleDAO.selectRoleById(roleId);
    }

    /**
     * 根据角色ID查询角色拥有的权限的id
     * @param roleId
     * @return
     */
    public List<Integer> getMenuIdsByRole(int roleId){
        return roleDAO.selectMenuIdsByRoleId(roleId);
    }

    /**
     * 修改角色信息
     * @param role
     * @param menuIds
     * @return
     */
    public boolean updateRole(Role role,String[] menuIds){
        //1.修改角色信息
        int i = roleDAO.updateRole(role);

        //2.删除当前角色的原始权限
        int j = roleDAO.deleteRoleAndMenuByRoleId(role.getRoleId());

        //3.新增选择的所有权限
        for (int k = 0; k < menuIds.length ; k++) {
            int menuId = Integer.parseInt( menuIds[k] );
            int m = roleDAO.insertRoleAndMeun(role.getRoleId(), menuId);
        }
        //对于修改角色而言，角色是可以没有权限菜单的，因此只要i>0就表示角色修改成功
        return i>0;
    }
}
