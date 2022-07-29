package com.qf.zlp.framework.execl;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.qf.zlp.framework.entity.Position;
import com.qf.zlp.framework.service.IPositionService;

import java.util.ArrayList;
import java.util.List;

//上传文件
public class PositionListener implements ReadListener<Position> {

    private List<Position> positions = new ArrayList<>();

    private IPositionService positionService;

    public PositionListener(IPositionService positionService) {
        this.positionService = positionService;
    }

    @Override
    public void invoke(Position position, AnalysisContext analysisContext) {
       //id唯一性当文件上传到数据库会报错，id且为自增长所以不用管他。
        position.setId(null);
       positions.add(position);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        positionService.saveBatch(positions);

    }
}
