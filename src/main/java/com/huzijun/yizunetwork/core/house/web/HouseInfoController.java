package com.huzijun.yizunetwork.core.house.web;

import com.huzijun.yizunetwork.common.BaseReturnDTO;
import com.huzijun.yizunetwork.common.PageDTO;
import com.huzijun.yizunetwork.core.house.entity.HouseInfo;
import com.huzijun.yizunetwork.core.house.service.HouseInfoService;
import com.huzijun.yizunetwork.core.user.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.huzijun.yizunetwork.common.BaseController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 易租网房源信息 前端控制器
 * </p>
 *
 * @author hzj
 * @since 2018-05-29
 */
@RestController
@RequestMapping("/houseInfo")
public class HouseInfoController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(HouseInfoController.class);

    @Autowired
    HouseInfoService houseInfoService;

    @PostMapping(value = "/publishHouseInfo")
    public BaseReturnDTO publishHouseInfo(HouseInfo houseInfo){
        return BaseReturnDTO.ok("操作成功",houseInfoService.publishHouseInfo(houseInfo));
    }

    @PostMapping(value = "/updateHouseInfo")
    public BaseReturnDTO updateHouseInfo(HouseInfo houseInfo){
        return BaseReturnDTO.ok("更新成功",houseInfoService.updateHouseInfo(houseInfo));
    }

    @GetMapping(value = "/selectDetail")
    public BaseReturnDTO selectDetail(Integer hId){
        return  BaseReturnDTO.ok("查询成功",houseInfoService.selectDetail(hId));
    }

    @PostMapping(value = "/getPage")
    public BaseReturnDTO selectPage(PageDTO<HouseInfo> pageDTO){
        return  BaseReturnDTO.ok("查询成功",houseInfoService.getPage(pageDTO));
    }

    @PostMapping(value = "/uploadHouseImg")
    public BaseReturnDTO uploadHouseImg(HttpServletRequest request){
        return BaseReturnDTO.ok("上传成功",houseInfoService.uploadHouseImg(request));
    }

    @GetMapping(value = "/delHouseInfo")
    public BaseReturnDTO delHouseInfo(Integer hId){
        return BaseReturnDTO.ok("操作成功",houseInfoService.delHouseInfo(hId));
    }


}
