package com.qf.zlp.admin.controller.system.basic;

import com.qf.zlp.framework.entity.Joblevel;
import com.qf.zlp.framework.entity.RespBean;
import com.qf.zlp.framework.service.IJoblevelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/JobLevel")
public class JobLevelController {

    @Autowired
    IJoblevelService JobLevelService;
    //查询
    @GetMapping("/")
    public List<Joblevel> allJobLevel(){return JobLevelService.list();}

    @PostMapping("/")
    public  RespBean addJobLevel(@RequestBody Joblevel joblevel){

        int i = JobLevelService.saveIJoblevel(joblevel);

        if (i==1){

            return   RespBean.ok("增加成功");

        }else if (i==-1){

            return  RespBean.error("该用户存在，添加失败");
        }

        return RespBean.error("添加失败");

    }

}
