package com.qf.zlp.framework.service;

import com.qf.zlp.framework.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.zlp.framework.entity.vo.MenuRoleVo;
import com.qf.zlp.framework.entity.vo.MenuVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 庄林普
 * @since 2022-07-26
 */
public interface IMenuService extends IService<Menu> {

    List<MenuVO> getAllMenus();

    List<MenuRoleVo> getMenuAllRole();
}
