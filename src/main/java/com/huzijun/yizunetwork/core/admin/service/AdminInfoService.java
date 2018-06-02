package com.huzijun.yizunetwork.core.admin.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.huzijun.yizunetwork.common.BusinessBaseException;
import com.huzijun.yizunetwork.core.admin.entity.AdminInfo;
import com.huzijun.yizunetwork.core.admin.mapper.AdminInfoMapper;
import com.huzijun.yizunetwork.common.BaseService;
import com.huzijun.yizunetwork.core.house.entity.HouseInfo;
import com.huzijun.yizunetwork.core.house.entity.UserComment;
import com.huzijun.yizunetwork.core.house.entity.UserTipOff;
import com.huzijun.yizunetwork.core.house.service.HouseInfoService;
import com.huzijun.yizunetwork.core.house.service.UserTipOffService;
import com.huzijun.yizunetwork.core.user.entity.UserInfo;
import com.huzijun.yizunetwork.core.user.service.UserInfoService;
import com.huzijun.yizunetwork.utils.MyStringUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 管理员信息表 服务实现类
 * </p>
 *
 * @author hzj
 * @since 2018-06-01
 */
@Service
@Transactional
public class AdminInfoService extends BaseService<AdminInfoMapper, AdminInfo>{

    @Autowired
    private HouseInfoService houseInfoService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserTipOffService userTipOffService;

    public AdminInfo login(AdminInfo adminInfo){
        if(MyStringUtil.isNull(adminInfo.getAdminId()))
            throw BusinessBaseException.fail("登录名不能为空");
        if(MyStringUtil.isNull(adminInfo.getAdminPwd()))
            throw BusinessBaseException.fail("密码不能为空");
        EntityWrapper<AdminInfo> ew = new EntityWrapper<>();
        adminInfo = selectOne(ew);
        if (adminInfo == null)
            throw BusinessBaseException.fail("登录名或者密码错误");
        return adminInfo;
    }

    public Page<HouseInfo> getHousePage(Page<HouseInfo> page,HouseInfo houseInfo){
        EntityWrapper<HouseInfo> ew = new EntityWrapper<>();
        ew.setEntity(houseInfo);
        return houseInfoService.selectPage(page,ew);
    }

    public Page<UserInfo> getUserPage(Page<UserInfo> page,UserInfo userInfo){
        EntityWrapper<UserInfo> ew = new EntityWrapper<>();
        ew.setEntity(userInfo);
        return userInfoService.selectPage(page,ew);
    }

    public Page<UserTipOff> getTipOffPage (Page<UserTipOff> page,UserTipOff userTipOff){
        EntityWrapper<UserTipOff> ew = new EntityWrapper<>();
        ew.setEntity(userTipOff);
        return userTipOffService.selectPage(page,ew);
    }

    public boolean updateHousePubStatus(Integer hId,Integer pubStatus){
        HouseInfo houseInfo = houseInfoService.commonExistsCheck(hId);
        houseInfo.setDkPubStatus(pubStatus);
        if (pubStatus.equals(2))
            houseInfo.setPublishTime(new Date());
        return  houseInfoService.updateById(houseInfo);
    }


    public boolean updateUserEx(Integer uId,Integer ex){
        UserInfo userInfo = userInfoService.commonExistsCheck(uId);
        userInfo.setDkUException(ex);
        return userInfoService.updateById(userInfo);
    }

    public boolean updateTipStatus(Integer tipId,Integer status){
        UserTipOff userTipOff = userTipOffService.commonExistsCheck(tipId);
        userTipOff.setDkTipStatus(status);
        if (status.equals(4)){
            HouseInfo houseInfo = houseInfoService.selectById(userTipOff.gethTipOff());
            if (houseInfo == null)
                throw BusinessBaseException.fail("房源信息不存在");
            houseInfo.setDkHException(1);
            houseInfoService.updateById(houseInfo);
            if(houseInfoService.selectExCount(houseInfo.getuId()) >= 3){
                UserInfo userInfo = userInfoService.commonExistsCheck(houseInfo.getuId());
                if (userInfo == null)
                    throw BusinessBaseException.fail("用户不存在");
                userInfo.setDkUException(1);
                userInfoService.updateById(userInfo);
            }
        }
        return userTipOffService.updateById(userTipOff);
    }


}
