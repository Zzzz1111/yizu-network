package com.huzijun.yizunetwork.core.house.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.huzijun.yizunetwork.core.house.DTO.UserFavoriteDTO;
import com.huzijun.yizunetwork.core.house.entity.UserFavorite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 用户收藏记录表 Mapper 接口
 * </p>
 *
 * @author hzj
 * @since 2018-05-30
 */
public interface UserFavoriteMapper extends BaseMapper<UserFavorite> {

    List<UserFavoriteDTO> selectMyHouseFavorites(Page<UserFavoriteDTO> page, @Param("ew")EntityWrapper<UserFavoriteDTO> ew);
}