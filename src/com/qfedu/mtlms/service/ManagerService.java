package com.qfedu.mtlms.service;

import com.qfedu.mtlms.dao.ManagerDAO;
import com.qfedu.mtlms.dto.Manager;
import com.qfedu.mtlms.utils.MD5Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @Description 用于实现管理员相关业务
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class ManagerService {

    private ManagerDAO managerDAO = new ManagerDAO();

    /**
     * 根据管理员输入的登录账号和密码验证登录是否成功
     * @param loginName
     * @param loginPwd
     * @return 如果登录成功则返回manager对象，否则返回null
     */
    public Manager checkLogin(String loginName,String loginPwd){
        //1.根据loginName查询管理员信息
        Manager manager = managerDAO.selectManagerByLoginName(loginName);
        //2. 如果查询的结果不为null，则表示登录名是正确的
        if(manager != null ){
            //判断密码  (如果输入的密码 和从数据库查询出来的密码 不相等，表示密码错误,返回null)
            //对在登录页面中输入的密码进行加密
            String s = MD5Utils.md5Encode(loginPwd);
            //使用加密后的密文，和数据库中查询出来的密码（密文）进行比较
            if(!s.equals( manager.getLoginPwd())){
                return null;
            }
        }
        return manager;
    }

    /**
     * 查询所有管理员信息
     * @return
     */
    public List<Manager> listManagers(){
        List<Manager> managerList = managerDAO.selectManagers();
        return managerList;
    }

    /**
     * 保存管理员
     * @param manager
     * @param roldId
     * @return
     */
    public boolean saveManager(Manager manager,int roldId){
        //1.对manager对象中的密码进行MD5加密处理（数据库中密码要保存密文）
        String s = MD5Utils.md5Encode(manager.getLoginPwd());
        manager.setLoginPwd( s );
        //2.保存管理员信息
        int i = managerDAO.insertManager(manager);
        //3.保存管理员和角色关联关系
        int j = managerDAO.insertMgrAndRole(manager.getMgrId(),roldId);
        return i>0;
    }

    /**
     * 删除管理员
     * @param mgrId
     * @return
     */
    public boolean deleteManager(String mgrId){
        //1.删除管理员与角色的关联关系
        int i = managerDAO.deleteMgrAndRole(mgrId);
        //2.删除管理员信息
        int j = managerDAO.deleteManager(mgrId);
        return j>0;
    }

    /**
     * 根据管理员ID查询管理员信息
     * @param mgrId
     * @return
     */
    public Manager getManagerById(String mgrId){
        Manager manager = managerDAO.selectManagerById(mgrId);
        return manager;
    }

    /**
     * 修改管理员信息
     * @param manager
     * @param roleId
     * @return
     */
    public boolean updateManager(Manager manager,int roleId){
        //1.修改管理员密码 （管理员有密码：如果没有修改密码，则密码为加密状态； 如果管理员修改了密码，则密码为明文）
        //  a.如果在修改页面没有输入密码，则认为不修改密码
        //  b.如果在修改页面输入了密码，则表示需要将密码修改到数据库
        if(manager.getLoginPwd() == null || "".equals(manager.getLoginPwd())){
            //将此管理员的原始密码设置到manager中
            Manager oldManager = managerDAO.selectManagerById(manager.getMgrId());
            manager.setLoginPwd( oldManager.getLoginPwd() );
        }else{
            //如果在修改页面输入了密码，则对新密码进行加密
            String s = MD5Utils.md5Encode(manager.getLoginPwd());
            manager.setLoginPwd(s);
        }

        //2.执行修改管理员
        int i = managerDAO.updateManager(manager);

        //3.修改管理员的角色
        if(i>0){
            //a.删除当前管理员的原始角色关系关联  tb_mgr_role
            int j = managerDAO.deleteMgrAndRole(manager.getMgrId());

            //b.添加新的管理员与角色的关联关系
            int k = managerDAO.insertMgrAndRole(manager.getMgrId(),roleId);
        }

        return i>0;
    }

}
