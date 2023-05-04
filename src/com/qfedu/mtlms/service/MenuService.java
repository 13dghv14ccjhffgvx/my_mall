package com.qfedu.mtlms.service;

import com.qfedu.mtlms.dao.MenuDAO;
import com.qfedu.mtlms.dto.Menu1;
import com.qfedu.mtlms.dto.Menu2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 菜单相关业务逻辑
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class MenuService {

    private MenuDAO menuDAO = new MenuDAO();

    /**
     * 根据管理员ID查询当前管理员的菜单列表（一级菜单中需要包含对应的二级菜单）
     * @param mgrId
     * @return
     */
    public List<Menu1> listMenusByMgrId(String mgrId){
        //1.根据管理ID查询此管理员所有的一级菜单
        List<Menu1> menu1List = menuDAO.selectFirstLevelMenusByMgrId(mgrId);
        //2.查询每个一次菜单中的二级菜单
        for (int i = 0; i <menu1List.size() ; i++) {
            Menu1 menu1 = menu1List.get(i);
            //查询此一级菜单下的二级菜单
            List<Menu2> menu2List = menuDAO.selectMenu2ByMgrIdAndParentCode(mgrId, menu1.getMenuCode());
            //将二级菜单集合，设置给一级菜单对象
            menu1.setChildMenus(menu2List);
        }
        return menu1List;
    }

    /**
     * 查询一级菜单和二级菜单
     * @return map集合中包含一级菜单和二级菜单
     */
    public Map<String,List> listMenus(){
        //1.查询一级菜单
        List<Menu1> menu1List = menuDAO.selectMenu1();
        //2.查询二级菜单
        List<Menu2> menu2List = menuDAO.selectMenu2();
        //3.将menu1List和menu2List存储到map中
        Map<String,List> menus = new HashMap<>();
        menus.put("menu1List",menu1List);
        menus.put("menu2List",menu2List);
        return menus;
    }

    /**
     * 根据一级菜单menuCode查询二级菜单
     * @param menu1Code
     * @return
     */
    public List<Menu2> listMenu2ByMenu1Code(String menu1Code){
        List<Menu2> menu2List = menuDAO.selectMenu2ByMenu1Code(menu1Code);
        return  menu2List;
    }

    /**
     * 启用菜单
     */
    public boolean enableMenu(String menuCode){
        return changeMenuState(menuCode,1);
    }

    /**
     * 停用菜单
     */
    public boolean disableMenu(String menuCode){
        return changeMenuState(menuCode,0);
    }

    private boolean changeMenuState(String menuCode,int state){
        int i = menuDAO.updateMenuState(menuCode, state);
        boolean b = i>0;
        return b;
    }

    /**
     * 查询所有的一级菜单，并且在每个一级菜单中包含其对应的二级菜单
     * @return
     */
    public List<Menu1> listAllMenus(){
        //1.查询到所有的一级菜单（此时的一级菜单中是不包含二级菜单的）
        List<Menu1> menu1List = menuDAO.selectMenu1();

        //2.遍历已经查询到的一级菜单，依次查询每个一级菜单中的二级菜单
        for (int i = 0; i < menu1List.size(); i++) {
            Menu1 menu1 = menu1List.get(i);
            //根据一级菜单的menuCode查询二级菜单
            List<Menu2> menu2List = menuDAO.selectMenu2ByMenu1Code(menu1.getMenuCode());
            //将查询到二级菜单设置到当前一级菜单对象中
            menu1.setChildMenus(menu2List);
        }
        return menu1List;
    }


}
