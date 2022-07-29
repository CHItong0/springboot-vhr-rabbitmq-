package com.qf.zlp.admin.controller.system.basic;

import com.alibaba.excel.EasyExcel;
import com.qf.zlp.framework.entity.Position;
import com.qf.zlp.framework.entity.RespBean;
import com.qf.zlp.framework.entity.RespPageBean;
import com.qf.zlp.framework.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
@RestController
@RequestMapping("/system/basic/position")
public class PositionController {

        @Autowired
        IPositionService positionService;

        @GetMapping("/")
        public RespPageBean getAllPosition(Integer page, Integer size) {
            return positionService.getPositionsByPage(page,size);
        }


        @PostMapping("/")
        public RespBean addPosition(@RequestBody Position position){


        // 添加
            int i = positionService.savePosition(position);
            if (i==1){
                   //添加成功
                   return RespBean.ok("添加成功");
                }else if (i==-1){
                    return  RespBean.error("职位存在，添加失败");
            }
            return RespBean.error("添加失败");
        }

        @DeleteMapping("/{id}")
        public RespBean deletePosition(@PathVariable Integer id){
            int i = positionService.deletePosition(id);
            if (i==1){
                return  RespBean.ok("删除成功");
            }else if (i==-1){
                return RespBean.error("删除的数据不存在,删除失败");
            }
            return RespBean.error("删除失败");


        }

        @PutMapping("/")
        public RespBean upDataPosition(@RequestBody Position position){

            if (positionService.updateById(position)){

                return RespBean.ok("更新成功");
            }
            return RespBean.error("更新失败");


        }

     //下载文件
    @GetMapping("/Excel")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("员工资料", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Position.class).sheet("员工资料").doWrite(positionService.list());
    }
    //上传文件
    @PostMapping("/Upload")
    public RespBean Upload(MultipartFile file){
            return positionService.importPositionData(file);
    }
}
