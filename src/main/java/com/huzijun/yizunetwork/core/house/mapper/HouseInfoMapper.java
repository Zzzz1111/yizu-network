package com.huzijun.yizunetwork.core.house.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.huzijun.yizunetwork.core.house.entity.HouseInfo;
import org.apache.ibatis.annotations.Param;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * <p>
  * 易租网房源信息 Mapper 接口
 * </p>
 *
 * @author hzj
 * @since 2018-05-29
 */
public interface HouseInfoMapper extends BaseMapper<HouseInfo> {

    List<HouseInfo> selectList(Page<HouseInfo> page, @Param("ew")Wrapper<HouseInfo> wrapper);

}