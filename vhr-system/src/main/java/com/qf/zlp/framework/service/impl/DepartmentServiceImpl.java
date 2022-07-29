package com.qf.zlp.framework.service.impl;

import com.qf.zlp.framework.entity.Department;
import com.qf.zlp.framework.entity.RespBean;
import com.qf.zlp.framework.entity.vo.AddDepartmentVO;
import com.qf.zlp.framework.entity.vo.DepartmentChildrenVO;
import com.qf.zlp.framework.mapper.DepartmentMapper;
import com.qf.zlp.framework.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {


    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentChildrenVO> allDepartment() {
        return departmentMapper.allDepartment(-1);
    }

    @Override
    public RespBean addDepartment(AddDepartmentVO addDepartmentVO) {

        //默认将这个字段这个为true
        addDepartmentVO.setEnabled(true);
        //默认将这个字段这个为false
        addDepartmentVO.setIsParent(false);
        departmentMapper.addDepartment(addDepartmentVO);
        if (addDepartmentVO.getResult()==1){
            return  RespBean.ok("添加成功",addDepartmentVO);

        }
        return RespBean.error("添加失败");


    }

    @Override
    public RespBean deleteDepartmentById(Integer id) {
        AddDepartmentVO addDepartmentVO = new AddDepartmentVO();
        //把id 放入封装好的对象
        addDepartmentVO.setId(id);
        departmentMapper.deleteDepartmentById(addDepartmentVO);


        //getResult mapper层返回的结果集
        if (addDepartmentVO.getResult()==-2){
            return RespBean.error("该部门有子部门或者部门不存在，删除失败");
        }else if (addDepartmentVO.getResult()==-1){

            return RespBean.error("该部门下有员工，删除失败");

        }else if(addDepartmentVO.getResult()==1){

            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");

    }
}
