package com.qf.zlp.framework.entity.vo;

import com.qf.zlp.framework.entity.Menu;

import java.util.List;
/*
* 一对多需要封装 一个集合
* */
public class MenuVO extends Menu {

    private List<Menu> Children;

    public List<Menu> getChildren() {
        return Children;
    }

    public void setChildren(List<Menu> children) {
        Children = children;
    }
}
