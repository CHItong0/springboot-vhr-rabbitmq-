package com.qf.zlp.framework.service.impl;

import com.qf.zlp.framework.entity.Hr;
import com.qf.zlp.framework.entity.Menu;
import com.qf.zlp.framework.entity.vo.MenuRoleVo;
import com.qf.zlp.framework.entity.vo.MenuVO;
import com.qf.zlp.framework.mapper.MenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.zlp.framework.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 庄林普
 * @since 2022-07-26
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    MenuMapper menuMapper;
    @Override
    public List<MenuVO> getAllMenus() {
        // 从Security 中获取登录对象
        Hr hr = (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return menuMapper.getAllMenuseByHrid(hr.getId());
    }

    @Override
    public List<MenuRoleVo> getMenuAllRole() {
        return menuMapper.getMenuAllRole();
    }


}
