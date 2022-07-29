package com.qf.zlp.admin.controller.system.basic;
import com.qf.zlp.framework.entity.Department;
import com.qf.zlp.framework.entity.RespBean;
import com.qf.zlp.framework.entity.vo.AddDepartmentVO;
import com.qf.zlp.framework.entity.vo.DepartmentChildrenVO;
import com.qf.zlp.framework.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    IDepartmentService departmentService;

    @GetMapping("/")
    public List<DepartmentChildrenVO> allDepartment(){

        return departmentService.allDepartment();

    }
    @PostMapping("/")
    public RespBean addDepartment(@RequestBody AddDepartmentVO addDepartmentVO){

        return departmentService.addDepartment(addDepartmentVO);


    }

    @GetMapping("/list")
    public List<Department> departmentsList(){

        return departmentService.list();
    }
    @DeleteMapping("/{id}")
    public RespBean deleteDepartmentById(@PathVariable Integer id){

        return departmentService.deleteDepartmentById(id);

    }



}
