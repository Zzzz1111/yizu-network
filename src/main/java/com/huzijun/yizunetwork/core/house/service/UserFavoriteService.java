package com.huzijun.yizunetwork.core.house.service;

import com.huzijun.yizunetwork.common.BaseService;
import com.huzijun.yizunetwork.common.BusinessBaseException;
import com.huzijun.yizunetwork.core.house.entity.HouseInfo;
import com.huzijun.yizunetwork.core.house.entity.UserFavorite;
import com.huzijun.yizunetwork.core.house.mapper.UserFavoriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户收藏记录表 服务实现类
 * </p>
 *
 * @author hzj
 * @since 2018-05-30
 */
@Service
@Transactional
public class UserFavoriteService extends BaseService<UserFavoriteMapper, UserFavorite>{

    @Autowired
    HouseInfoService houseInfoService;

    public boolean collectHouse(Integer uId,Integer hId){
        HouseInfo houseInfo = houseInfoService.commonExistsCheck(hId);
        if (uId == null)
            throw BusinessBaseException.fail("收藏用户不能为空");
        if (hId == null)
            throw BusinessBaseException.fail("收藏房源不能为空");
        UserFavorite userFavorite = new UserFavorite();
        userFavorite.setuId(uId);
        userFavorite.sethFavorite(hId);
        houseInfo.setFavorite(houseInfo.getFavorite() + 1);
        houseInfoService.updateById(houseInfo);
        return insert(userFavorite);
    }

    public boolean cancelCollectHouse(Integer uId,Integer userFavoriteId){
        UserFavorite userFavorite = commonExistsCheck(userFavoriteId);
        HouseInfo houseInfo = houseInfoService.commonExistsCheck(userFavorite.gethFavorite());
        houseInfo.setFavorite(houseInfo.getFavorite() - 1);
        houseInfoService.updateById(houseInfo);
        if (!userFavorite.getuId().equals(uId))
            throw BusinessBaseException.fail("不能取消非本人收藏的房源");
        return deleteById(userFavoriteId);
    }
	
}
