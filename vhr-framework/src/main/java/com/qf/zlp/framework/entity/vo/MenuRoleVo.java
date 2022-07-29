package com.qf.zlp.framework.entity.vo;


import com.qf.zlp.framework.entity.Role;

import java.util.List;

/*
* 继承MenuVO 方能使用MenuVO 参数
* */
public class MenuRoleVo extends MenuVO {

    /**
     * 一对多需要封装集合
     */

    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
