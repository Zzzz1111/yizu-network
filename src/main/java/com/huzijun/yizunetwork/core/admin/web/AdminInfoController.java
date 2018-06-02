package com.huzijun.yizunetwork.core.admin.web;

import com.baomidou.mybatisplus.plugins.Page;
import com.huzijun.yizunetwork.common.BaseReturnDTO;
import com.huzijun.yizunetwork.core.admin.entity.AdminInfo;
import com.huzijun.yizunetwork.core.admin.service.AdminInfoService;
import com.huzijun.yizunetwork.core.house.entity.HouseInfo;
import com.huzijun.yizunetwork.core.house.entity.UserTipOff;
import com.huzijun.yizunetwork.core.user.entity.UserInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.huzijun.yizunetwork.common.BaseController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 管理员信息表 前端控制器
 * </p>
 *
 * @author hzj
 * @since 2018-06-01
 */
@Api
@Controller
@RequestMapping("/admin")
public class AdminInfoController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminInfoController.class);

    @Autowired
    private AdminInfoService adminInfoService;

    @PostMapping(value = "/login")
    public BaseReturnDTO login(@RequestBody AdminInfo adminInfo){
        return BaseReturnDTO.ok("登录成功",adminInfoService.login(adminInfo));
    }

    @PostMapping(value = "/getHousePage")
    public BaseReturnDTO getHousePage(@RequestBody Page<HouseInfo> page,@RequestBody HouseInfo houseInfo){
        return BaseReturnDTO.ok("查询成功",adminInfoService.getHousePage(page,houseInfo));
    }

    @PostMapping(value = "/getUserPage")
    public BaseReturnDTO getUserPage(@RequestBody Page<UserInfo> page,@RequestBody UserInfo userInfo){
        return BaseReturnDTO.ok("查询成功",adminInfoService.getUserPage(page,userInfo));
    }

    @PostMapping(value = "/getTipOffPage")
    public BaseReturnDTO getTipOffPage(@RequestBody Page<UserTipOff> page, @RequestBody UserTipOff userTipOff){
        return BaseReturnDTO.ok("查询成功",adminInfoService.getTipOffPage(page,userTipOff));
    }

    @GetMapping(value = "/updateHousePubStatus")
    public BaseReturnDTO updateHousePubStatus(Integer hId,Integer pubStatus){
        return BaseReturnDTO.ok("更新成功",adminInfoService.updateHousePubStatus(hId,pubStatus));
    }

    @GetMapping(value = "/updateUserEx")
    public BaseReturnDTO updateUserEx(Integer uId,Integer ex){
        return BaseReturnDTO.ok("更新成功",adminInfoService.updateUserEx(uId,ex));
    }

    @GetMapping(value = "/updateTipStatus")
    public BaseReturnDTO updateTipStatus(Integer tId,Integer status){
        return BaseReturnDTO.ok("更新成功",adminInfoService.updateTipStatus(tId,status));
    }
}
