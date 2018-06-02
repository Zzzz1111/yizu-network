package com.huzijun.yizunetwork.core.dict.service;

import com.huzijun.yizunetwork.common.BaseService;
import com.huzijun.yizunetwork.core.dict.entity.DictGzSubStation;
import com.huzijun.yizunetwork.core.dict.mapper.DictGzSubStationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 广州地铁站点字典表 服务实现类
 * </p>
 *
 * @author hzj
 * @since 2018-05-30
 */
@Service
public class DictGzSubStationService extends BaseService<DictGzSubStationMapper, DictGzSubStation>{


    public List<String> selectSubStationList(String subwayCode){
        return this.baseMapper.selectSubStationList(subwayCode);
    }

    public static void main(String[] args) {

    }
	
}
