package com.qf.zlp.framework.service;

import com.qf.zlp.framework.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.zlp.framework.entity.RespBean;
import com.qf.zlp.framework.entity.vo.AddDepartmentVO;
import com.qf.zlp.framework.entity.vo.DepartmentChildrenVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 庄林普
 * @since 2022-07-26
 */
public interface IDepartmentService extends IService<Department> {


    List<DepartmentChildrenVO> allDepartment();


    //自定义策略
    RespBean addDepartment(AddDepartmentVO addDepartmentVO);


    RespBean deleteDepartmentById(Integer id);
}
