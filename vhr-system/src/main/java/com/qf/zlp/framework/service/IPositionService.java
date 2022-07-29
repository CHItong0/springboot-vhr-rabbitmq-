package com.qf.zlp.framework.service;

import com.qf.zlp.framework.entity.Position;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.zlp.framework.entity.RespBean;
import com.qf.zlp.framework.entity.RespPageBean;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 庄林普
 * @since 2022-07-26
 */
public interface IPositionService extends IService<Position> {


    int savePosition(Position position);

    int deletePosition(Integer id);

    RespBean importPositionData(MultipartFile file);

    RespPageBean getPositionsByPage(Integer page,Integer size);

}
