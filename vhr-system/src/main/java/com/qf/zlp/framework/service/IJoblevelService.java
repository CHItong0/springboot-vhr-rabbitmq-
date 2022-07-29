package com.qf.zlp.framework.service;

import com.qf.zlp.framework.entity.Joblevel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.zlp.framework.entity.Position;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 庄林普
 * @since 2022-07-26
 */
public interface IJoblevelService extends IService<Joblevel> {


    int saveIJoblevel(Joblevel joblevel);

}
