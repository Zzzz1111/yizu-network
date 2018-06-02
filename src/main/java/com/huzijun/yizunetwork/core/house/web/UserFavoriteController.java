package com.huzijun.yizunetwork.core.house.web;

import com.baomidou.mybatisplus.plugins.Page;
import com.huzijun.yizunetwork.common.BaseController;
import com.huzijun.yizunetwork.common.BaseReturnDTO;
import com.huzijun.yizunetwork.core.house.DTO.UserFavoriteDTO;
import com.huzijun.yizunetwork.core.house.service.UserFavoriteService;
import com.huzijun.yizunetwork.core.user.entity.UserInfo;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户收藏记录表 前端控制器
 * </p>
 *
 * @author hzj
 * @since 2018-05-30
 */
@Api
@RestController
@RequestMapping("/house/userFavorite")
public class UserFavoriteController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserFavoriteController.class);

    @Autowired
    private UserFavoriteService userFavoriteService;

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

    @PostMapping(value = "/selectMyFavorites")
    public BaseReturnDTO selectMyFavorites(HttpSession session,@RequestBody Page<UserFavoriteDTO> page){
        UserInfo user= (UserInfo) session.getAttribute("user");
        return BaseReturnDTO.ok("查询成功",userFavoriteService.selectMyFavorites(page,user.getuId()));
    }

}
