package com.huzijun.yizunetwork.core.house.web;

import com.huzijun.yizunetwork.common.BaseReturnDTO;
import com.huzijun.yizunetwork.core.house.entity.UserTipOff;
import com.huzijun.yizunetwork.core.house.service.UserTipOffService;
import com.huzijun.yizunetwork.core.user.entity.UserInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.huzijun.yizunetwork.common.BaseController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户举报记录表 前端控制器
 * </p>
 *
 * @author hzj
 * @since 2018-06-01
 */
@Api
@RestController
@RequestMapping("/house/userTipOff")
public class UserTipOffController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserTipOffController.class);

    @Autowired
    private UserTipOffService userTipOffService;

    @PostMapping(value = "tipOffHouse")
    public BaseReturnDTO tipOffHouse(@RequestBody UserTipOff userTipOff, HttpSession session){
        UserInfo user= (UserInfo) session.getAttribute("user");
        return BaseReturnDTO.ok("举报成功",userTipOffService.tipOffHouse(userTipOff,user.getuId()));
    }

}
