package com.qf.zlp.framework.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.zlp.framework.entity.Position;
import com.qf.zlp.framework.entity.RespBean;
import com.qf.zlp.framework.entity.RespPageBean;
import com.qf.zlp.framework.execl.PositionListener;
import com.qf.zlp.framework.mapper.PositionMapper;
import com.qf.zlp.framework.service.IPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 庄林普
 * @since 2022-07-26
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

    //增加职位方法
    @Override
    public int savePosition(Position position) {

        QueryWrapper<Position> qw = new QueryWrapper<>();

        qw.lambda().eq(Position::getName,position.getName());

        Position one = getOne(qw);

        if (one!=null){
            //说明职位已存在
        return  -1;

        }
        position.setCreateDate(LocalDateTime.now());
        position.setEnabled(true);
        return save(position) ? 1 : -2;

    }

    //删除职位方法
    @Override
    public int deletePosition(Integer id) {
        //根据id 查找 的方法
        Position byId = getById(id);

        if (byId==null){
            //说明要删除的数据不存在
            return -1;
        }
        return removeById(id)?1 :-2;
    }

    @Override
    public RespBean importPositionData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),Position.class,new PositionListener(this)).sheet().doRead();
            RespBean.ok("上传成功");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return RespBean.error("上传失败");
    }

    @Override
    public RespPageBean getPositionsByPage(Integer page, Integer size) {

        Page<Position> p = page(Page.of(page,size));
        return new RespPageBean(p.getTotal(), p.getRecords());
    }


}
