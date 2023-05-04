package com.qfedu.mtlms.dao;

import com.qfedu.mtlms.dto.Role;
import com.qfedu.mtlms.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 完成对角色信息的数据库访问操作
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class RoleDAO {

    /**
     * 查询所有的角色信息
     * @return
     */
    public List<Role> selectRoles(){
        List<Role> roleList = new ArrayList<>();
        try {
            String sql = "select role_id roleId,role_name roleName,role_desc roleDesc from tb_roles";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            roleList = queryRunner.query(sql, new BeanListHandler<Role>(Role.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleList;
    }

    /**
     * 添加角色信息(获取添加的数据自动生成的主键)
     * @param role
     * @return
     */
    public int insertRole(Role role){
        int i = 0;
        try {
            String sql = "insert into tb_roles(role_name,role_desc) values(?,?)";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            //返回的生成的主键存储在一个BigInterger对象中
            BigInteger object = queryRunner.insert(sql, new ScalarHandler<>(), role.getRoleName(), role.getRoleDesc());
            //将BigInteger转换成int类型，赋值给i
            i = object.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 添加角色和菜单的关联关系
     * @return
     */
    public int insertRoleAndMeun(int roleId,int menuId){
        int i = 0;
        try {
            String sql = "insert into tb_role_menu(role_id,menu_id) values(?,?)";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql, roleId, menuId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 根据角色ID删除这个角色与菜单的映射
     * @param roleId
     * @return
     */
    public int deleteRoleAndMenuByRoleId(int roleId){
        int i = 0;
        try {
            String sql = "delete from tb_role_menu where role_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql, roleId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 根据角色ID删除角色信息
     * @param roleId
     * @return
     */
    public int deleteRoleByRoldId(int roleId){
        int i = 0;
        try {
            String sql = "delete from tb_roles where role_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql, roleId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 根据角色ID查询角色信息
     * @param roleId
     * @return
     */
    public Role selectRoleById(int roleId){
        Role role = null;
        try {
            String sql = "select role_id roleId,role_name roleName,role_desc roleDesc from tb_roles where role_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            role = queryRunner.query(sql, new BeanHandler<Role>(Role.class),roleId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    /**
     * 根据角色ID查询当前角色所拥有的权限ID
     * @param roleId
     * @return
     */
    public List<Integer> selectMenuIdsByRoleId(int roleId){
        List<Integer> mids = new ArrayList<>();
        try {
            String sql = "select menu_id from tb_role_menu where role_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            //自定义结果集处理器
            ResultSetHandler<List<Integer>> resultSetHandler = new ResultSetHandler<List<Integer>>() {
                @Override
                public List<Integer> handle(ResultSet resultSet) throws SQLException {
                    List<Integer> list = new ArrayList<>();
                    while(resultSet.next()){
                        int mid = resultSet.getInt("menu_id");
                        list.add(mid);
                    }
                    return list;
                }
            };
            mids = queryRunner.query(sql,resultSetHandler,roleId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mids;
    }

    /**
     * 根据角色id修改角色名称及角色描述
     * @param role
     * @return
     */
    public int updateRole(Role role){
        int i = 0;
        try {
            String sql = "update tb_roles set role_name=?,role_desc=? where role_id=?";
            Object[] params = {role.getRoleName(),role.getRoleDesc(),role.getRoleId()};
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }


}
