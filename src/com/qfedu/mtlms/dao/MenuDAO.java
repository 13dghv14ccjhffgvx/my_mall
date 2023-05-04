package com.qfedu.mtlms.dao;

import com.qfedu.mtlms.dto.Menu1;
import com.qfedu.mtlms.dto.Menu2;
import com.qfedu.mtlms.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.management.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 实现菜单的数据库操作
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class MenuDAO {

    /**
     * 根据管理员ID查询对应的一级菜单
     * @param mgrId
     * @return
     */
    public List<Menu1> selectFirstLevelMenusByMgrId(String mgrId){
        List<Menu1> menu1List = new ArrayList<>();
        try {
            String sql = "select c.menu_id menuId,menu_code menuCode,menu_name menuName,menu_order menuOrder,menu_level menuLevel,menu_icon menuIcon " +
                    "from tb_mgr_role a inner join tb_role_menu b inner join tb_menus c " +
                    "on a.role_id = b.role_id and b.menu_id = c.menu_id " +
                    "where a.mgr_id=? and c.menu_level=1 order by c.menu_order";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            menu1List = queryRunner.query(sql,new BeanListHandler<Menu1>(Menu1.class),mgrId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu1List;
    }

    /**
     * 根据管理员ID及一级菜单ID，查询此管理员在这个一级菜单下拥有的二级菜单
     * @param mgrId
     * @param parentCode
     * @return
     */
    public List<Menu2> selectMenu2ByMgrIdAndParentCode(String mgrId,String parentCode){
        List<Menu2> menu2List = new ArrayList<>();
        try {
            String sql = "select c.menu_id menuId,menu_code menuCode,menu_name menuName,menu_order menuOrder,menu_level menuLevel,parent_menu_code parentMenuCode,menu_url menuUrl,menu_state menuState " +
                    "from tb_mgr_role a inner join tb_role_menu b inner join tb_menus c " +
                    "on a.role_id = b.role_id and b.menu_id = c.menu_id " +
                    "where a.mgr_id=? and c.menu_level=2 and c.parent_menu_code=? order by c.menu_order";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            menu2List = queryRunner.query(sql, new BeanListHandler<Menu2>(Menu2.class),mgrId,parentCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu2List;
    }

    /**
     * 查询系统中所有的一级菜单
     * @return
     */
    public List<Menu1> selectMenu1(){
        List<Menu1> menu1List = new ArrayList<>();
        try {
            String sql = "select menu_id menuId,menu_code menuCode,menu_name menuName,menu_order menuOrder,menu_level menuLevel,menu_icon menuIcon " +
                    "from tb_menus where menu_level=1 order by menu_order;";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            menu1List = queryRunner.query(sql,new BeanListHandler<Menu1>(Menu1.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu1List;
    }

    /**
     * 查询系统中所有的二级菜单
     * @return
     */
    public List<Menu2> selectMenu2(){
        List<Menu2> menu2List = new ArrayList<>();
        try {
            String sql = "select menu_id menuId,menu_code menuCode,menu_name menuName,menu_order menuOrder,menu_level menuLevel,parent_menu_code parentMenuCode,menu_url menuUrl,menu_state menuState " +
                    "from tb_menus where menu_level=2 order by menu_order";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            menu2List = queryRunner.query(sql, new BeanListHandler<Menu2>(Menu2.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu2List;
    }

    /**
     * 根据一级菜单的menuCode查询当前一级菜单下的二级菜单
     * @param parentCode
     * @return
     */
    public List<Menu2> selectMenu2ByMenu1Code(String parentCode){
        List<Menu2> menu2List = new ArrayList<>();
        try {
            String sql = "select menu_id menuId,menu_code menuCode,menu_name menuName,menu_order menuOrder,menu_level menuLevel," +
                    "parent_menu_code parentMenuCode,menu_url menuUrl,menu_state menuState " +
                    "from tb_menus where parent_menu_code=? order by menu_order";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            menu2List = queryRunner.query(sql, new BeanListHandler<Menu2>(Menu2.class), parentCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu2List;
    }

    /**
     * 根据menuCode修改菜单状态
     * @param menuCode
     * @param state
     * @return
     */
    public int updateMenuState(String menuCode,int state){
        int i = 0;
        try {
            String sql ="update tb_menus set menu_state=? where menu_code=?";
            Object[] params = {state,menuCode};
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

}
