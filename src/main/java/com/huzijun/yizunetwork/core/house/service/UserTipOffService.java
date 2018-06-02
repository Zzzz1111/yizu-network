package com.huzijun.yizunetwork.core.house.service;

import com.huzijun.yizunetwork.common.BaseReturnDTO;
import com.huzijun.yizunetwork.common.BaseService;
import com.huzijun.yizunetwork.common.BusinessBaseException;
import com.huzijun.yizunetwork.core.house.entity.HouseInfo;
import com.huzijun.yizunetwork.core.house.entity.UserTipOff;
import com.huzijun.yizunetwork.core.house.mapper.UserTipOffMapper;
import com.huzijun.yizunetwork.core.user.service.UserInfoService;
import com.huzijun.yizunetwork.utils.MyStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户举报记录表 服务实现类
 * </p>
 *
 * @author hzj
 * @since 2018-06-01
 */
@Service
@Transactional
public class UserTipOffService extends BaseService<UserTipOffMapper, UserTipOff>{

    @Autowired
    private HouseInfoService houseInfoService;

    private void commonCheckTipOff(UserTipOff userTipOff){
        if (userTipOff.getDkTipStatus() == null)
            throw BusinessBaseException.fail("参数非法");
    }

    public boolean tipOffHouse(UserTipOff userTipOff,Integer uId){
        userTipOff.setuId(uId);
        commonCheckTipOff(userTipOff);
        if (userTipOff.getuTId() == null)
            throw BusinessBaseException.fail("举报房源不能为空");
        if (MyStringUtil.isNull(userTipOff.gethTipOffDesc()))
            throw BusinessBaseException.fail("举报描述不能为空");
        HouseInfo houseInfo = houseInfoService.selectById(userTipOff.getuTId());
        if (houseInfo.getTipOff() >= 5)
            userTipOff.setDkTipStatus(1);
        else
            houseInfo.setTipOff(houseInfo.getTipOff() + 1);
        return insert(userTipOff);
    }



}
