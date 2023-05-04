package com.qfedu.mtlms.dao;

import com.qfedu.mtlms.dto.Manager;
import com.qfedu.mtlms.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.management.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 用于完成管理员信息的数据库访问
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class ManagerDAO {

    /**
     * 根据管理员登录名查询管理员信息
     * @param loginName 管理员登录名
     * @return 如果查询成功则返回管理员对象，如果查询没有结果则返回null
     */
    public Manager selectManagerByLoginName(String loginName){
        Manager manager = null;
        try {
            //1.SQL指令
            String sql = "select mgr_id mgrId,login_name loginName,login_pwd loginPwd,mgr_name mgrName,mgr_gender mgrGender,mgr_tel mgrTel,mgr_email mgrEmail,mgr_qq mgrQQ,create_time createTime from tb_managers where login_name=?";
            //2.QueryRunner
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            //3.通过QueryRunner执行SQL
            manager = queryRunner.query(sql, new BeanHandler<Manager>(Manager.class),loginName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manager;
    }

    /**
     * 查询所有管理员信息
     * @return
     */
    public List<Manager> selectManagers(){
        List<Manager> managerList = new ArrayList<>();
        try {
            String sql = "select mgr_id mgrId,login_name loginName,login_pwd loginPwd,mgr_name mgrName,mgr_gender mgrGender,mgr_tel mgrTel,mgr_email mgrEmail,mgr_qq mgrQQ,create_time createTime from tb_managers";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            managerList = queryRunner.query(sql, new BeanListHandler<Manager>(Manager.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return managerList;
    }

    /**
     * 添加管理员
     * @param manager
     * @return 添加操作影响的数据行数
     */
    public int insertManager(Manager manager){
        int i = 0;
        try {
            String sql = "insert into tb_managers(mgr_id,login_name,login_pwd,mgr_name,mgr_gender,mgr_tel,mgr_email,mgr_qq,create_time) values(?,?,?,?,?,?,?,?,?)";
            Object[] params = {manager.getMgrId(),manager.getLoginName(),manager.getLoginPwd(),manager.getMgrName(),manager.getMgrGender(),manager.getMgrTel(),manager.getMgrEmail(),manager.getMgrQQ(),manager.getCreateTime()};
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 保存管理员与角色的关联关系
     * @param mgrId
     * @param roleId
     * @return
     */
    public int insertMgrAndRole(String mgrId,int roleId){
        int i = 0;
        try {
            String sql = "insert into tb_mgr_role(mgr_id,role_id) values(?,?)";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql, mgrId, roleId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 根据管理员id删除与角色的关联
     * @param mgrId
     * @return
     */
    public int deleteMgrAndRole(String mgrId){
        int i = 0;
        try {
            String sql ="delete from tb_mgr_role where mgr_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql, mgrId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 根据管理员ID删除管理员信息
     * @param mgrId
     * @return
     */
    public int deleteManager(String mgrId){
        int i = 0;
        try {
            String sql = "delete from tb_managers where mgr_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql, mgrId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 根据管理员ID查询管理员信息
     * @param mgrId
     * @return
     */
    public Manager selectManagerById(String mgrId){
        Manager manager = null;
        try {
            String sql = "select mgr_id mgrId,login_name loginName,login_pwd loginPwd,mgr_name mgrName,mgr_gender mgrGender,mgr_tel mgrTel,mgr_email mgrEmail,mgr_qq mgrQQ,create_time createTime from tb_managers where mgr_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            manager = queryRunner.query(sql,new BeanHandler<Manager>(Manager.class),mgrId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manager;
    }

    /**
     * 根据管理员ID修改管理员信息
     * @param manager
     * @return
     */
    public int updateManager(Manager manager){
        int i = 0;
        try {
            String sql = "update tb_managers set login_name=?,login_pwd=?,mgr_name=?,mgr_gender=?,mgr_tel=?,mgr_email=?,mgr_qq=? where mgr_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            Object[] params = {manager.getLoginName(),manager.getLoginPwd(),manager.getMgrName(),manager.getMgrGender(),
                    manager.getMgrTel(),manager.getMgrEmail(),manager.getMgrQQ(),manager.getMgrId()};
            i = queryRunner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

}
