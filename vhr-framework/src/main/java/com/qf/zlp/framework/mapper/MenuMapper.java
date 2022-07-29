package com.qf.zlp.framework.mapper;

import com.qf.zlp.framework.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.zlp.framework.entity.vo.MenuRoleVo;
import com.qf.zlp.framework.entity.vo.MenuVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 庄林普
 * @since 2022-07-26
 */
public interface MenuMapper extends BaseMapper<Menu> {



    List<MenuVO> getAllMenuseByHrid(Integer hrid);

    List<MenuRoleVo> getMenuAllRole();

}
