package com.huzijun.yizunetwork.core.house.web;

import com.huzijun.yizunetwork.common.BaseReturnDTO;
import com.huzijun.yizunetwork.core.house.service.UserFavoriteService;
import com.huzijun.yizunetwork.core.user.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.huzijun.yizunetwork.common.BaseController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户收藏记录表 前端控制器
 * </p>
 *
 * @author hzj
 * @since 2018-05-30
 */
@Controller
@RequestMapping("/house/userFavorite")
public class UserFavoriteController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserFavoriteController.class);

    @Autowired
    UserFavoriteService userFavoriteService;

    @GetMapping(value = "/collectHouseInfo")
    public BaseReturnDTO collectHouseInfo(HttpSession session,Integer hId){
        UserInfo user= (UserInfo) session.getAttribute("user");
        return BaseReturnDTO.ok("收藏成功",userFavoriteService.collectHouse(user.getuId(),hId));
    }

    @GetMapping(value = "/cancelCollectHouse")
    public BaseReturnDTO cancelCollectHouse(HttpSession session,Integer hId){
        UserInfo user= (UserInfo) session.getAttribute("user");
        return BaseReturnDTO.ok("收藏成功",userFavoriteService.cancelCollectHouse(user.getuId(),hId));
    }

}
