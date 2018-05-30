package com.huzijun.yizunetwork.core.house.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.huzijun.yizunetwork.common.BaseService;
import com.huzijun.yizunetwork.common.BusinessBaseException;
import com.huzijun.yizunetwork.common.PageDTO;
import com.huzijun.yizunetwork.core.house.entity.HouseInfo;
import com.huzijun.yizunetwork.core.house.entity.UserFavorite;
import com.huzijun.yizunetwork.core.house.mapper.HouseInfoMapper;
import com.huzijun.yizunetwork.core.user.service.FileService;
import com.huzijun.yizunetwork.utils.MyStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 易租网房源信息 服务实现类
 * </p>
 *
 * @author hzj
 * @since 2018-05-29
 */
@Service
@Transactional
public class HouseInfoService extends BaseService<HouseInfoMapper,HouseInfo>{

    @Autowired
    FileService fileService;


    //共有房源建议
    private void commonCheckHouseInfo(HouseInfo houseInfo) {
        if (houseInfo.getDkHException() != null)
            throw BusinessBaseException.fail("更新参数非法");
        if (houseInfo.getCtr() != null)
            throw BusinessBaseException.fail("更新参数非法");
        if (houseInfo.getTipOff() != null)
            throw BusinessBaseException.fail("更新参数非法");
        if (houseInfo.getDkPubStatus() != null)
            throw BusinessBaseException.fail("更新参数非法");
        if (houseInfo.getPublishTime() != null)
            throw BusinessBaseException.fail("更新参数非法");
        if (houseInfo.getFavorite() != null)
            throw BusinessBaseException.fail("更新参数非法");
    }

    //发布房源
    public boolean publishHouseInfo(HouseInfo houseInfo){
        if(houseInfo.gethId() != null)
            throw BusinessBaseException.fail("不能设置Id");
        commonCheckHouseInfo(houseInfo);
        if (MyStringUtil.isSize(houseInfo.getIntroduce(),10,300))
            throw BusinessBaseException.fail("简介字数过少或者超出");
        return insert(houseInfo);
    }

    public boolean updateHouseInfo(HouseInfo houseInfo){
        commonCheckHouseInfo(houseInfo);
        commonExistsCheck(houseInfo.gethId());
        if (houseInfo.getuId() != null)
            throw BusinessBaseException.fail("不能修改发布人");
        if (MyStringUtil.isSize(houseInfo.getIntroduce(),10,300))
            throw BusinessBaseException.fail("简介字数过少或者超出");
        return updateById(houseInfo);
    }

    public boolean delHouseInfo(Integer hId){
        HouseInfo houseInfo = commonExistsCheck(hId);
        if (houseInfo.getDkHException() != 0)
            throw BusinessBaseException.fail("该房源异常，无法操作");
        return deleteById(hId);
    }

    public HouseInfo selectDetail(Integer hId){
        HouseInfo houseInfo = commonExistsCheck(hId);
        if (houseInfo.getDkHException() != 0)
            throw BusinessBaseException.fail("该房源异常，无法展示");
        return houseInfo;
    }

    public Page<HouseInfo> getPage(PageDTO<HouseInfo> houseInfoPageDTO){
        Page<HouseInfo> houseInfoPage = new Page<>();
        HouseInfo model;
        //判断排序当前页面是否为空
        if (houseInfoPageDTO.getCurrent() != null)
            houseInfoPage.setCurrent(houseInfoPageDTO.getCurrent());
        //判断排序页面大小是否为空
        if (houseInfoPageDTO.getSize() != null)
            houseInfoPage.setSize(houseInfoPageDTO.getSize());
        //判断排序字段是否为空
        if (MyStringUtil.isNull(houseInfoPageDTO.getOrderBy()))
            houseInfoPage.setOrderByField(houseInfoPageDTO.getOrderBy());
        //初始化查询条件
        EntityWrapper<HouseInfo> ew = new EntityWrapper<>();
        model = houseInfoPageDTO.getModel();
        if (model != null)
            //如果查询条件不为空，将其设置进Wrapper
            model.setDkHException(0);
        model = new HouseInfo();
        model.setDkHException(0);
        ew.setEntity(model);
        if (model.getSubway() != null){
//            ew.in("dk_sub_station",);
        }


        houseInfoPage = selectPage(houseInfoPage,ew);
        return houseInfoPage;
    }

    public List<String> uploadHouseImg(HttpServletRequest request){
        return fileService.upload(request);
    }

}
