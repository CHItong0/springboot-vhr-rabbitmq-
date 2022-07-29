package com.qf.zlp.framework.entity.vo;

import com.qf.zlp.framework.entity.Department;

import java.util.List;

public class DepartmentChildrenVO extends Department {


    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }

    List<Department> children;

}
