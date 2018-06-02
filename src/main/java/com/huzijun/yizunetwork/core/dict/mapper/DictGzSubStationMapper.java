package com.huzijun.yizunetwork.core.dict.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.huzijun.yizunetwork.core.dict.entity.DictGzSubStation;

import java.util.List;

/**
 * <p>
  * 广州地铁站点字典表 Mapper 接口
 * </p>
 *
 * @author hzj
 * @since 2018-05-30
 */
public interface DictGzSubStationMapper extends BaseMapper<DictGzSubStation> {

    List<String> selectSubStationList(String subwayCode);



}