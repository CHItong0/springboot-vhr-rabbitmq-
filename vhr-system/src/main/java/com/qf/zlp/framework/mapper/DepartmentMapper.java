package com.qf.zlp.framework.mapper;

import com.qf.zlp.framework.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.zlp.framework.entity.vo.AddDepartmentVO;
import com.qf.zlp.framework.entity.vo.DepartmentChildrenVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 庄林普
 * @since 2022-07-26
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    List<DepartmentChildrenVO> allDepartment(int pid);
    // 自定义策略
    void addDepartment(AddDepartmentVO addDepartmentVO);


    void deleteDepartmentById(AddDepartmentVO addDepartmentVO);
}
