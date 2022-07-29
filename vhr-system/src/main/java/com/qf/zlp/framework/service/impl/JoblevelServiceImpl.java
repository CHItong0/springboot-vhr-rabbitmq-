package com.qf.zlp.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.zlp.framework.entity.Joblevel;
import com.qf.zlp.framework.mapper.JoblevelMapper;
import com.qf.zlp.framework.service.IJoblevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class JoblevelServiceImpl extends ServiceImpl<JoblevelMapper, Joblevel> implements IJoblevelService {

    @Override
    public int saveIJoblevel(Joblevel joblevel) {

        QueryWrapper<Joblevel> qw = new QueryWrapper<>();

        qw.lambda().eq(Joblevel::getName,joblevel.getName());
        Joblevel one = getOne(qw);

        if (one!=null){
            //已存在
            return -1;
        }
        joblevel.setCreateDate(LocalDateTime.now());
        joblevel.setEnabled(true);

        return save(joblevel)?1:-2;

    }
}
