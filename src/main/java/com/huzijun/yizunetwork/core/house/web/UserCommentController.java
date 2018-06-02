package com.huzijun.yizunetwork.core.house.web;

import com.baomidou.mybatisplus.plugins.Page;
import com.huzijun.yizunetwork.common.BaseReturnDTO;
import com.huzijun.yizunetwork.core.house.entity.UserComment;
import com.huzijun.yizunetwork.core.house.service.UserCommentService;
import com.huzijun.yizunetwork.core.user.entity.UserInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.huzijun.yizunetwork.common.BaseController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户评论记录表 前端控制器
 * </p>
 *
 * @author hzj
 * @since 2018-05-31
 */
@Api
@Controller
@RequestMapping("/house/userComment")
public class UserCommentController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserCommentController.class);

    @Autowired
    private  UserCommentService userCommentService;

    @ResponseBody
    @PostMapping(value = "/commentHouseInfo")
    public BaseReturnDTO commentHouseInfo(HttpSession session,@RequestBody UserComment userComment){
        UserInfo user= (UserInfo) session.getAttribute("user");
        return BaseReturnDTO.ok("评论成功",userCommentService.commentHouse(user.getuId(),userComment));
    }

    @ResponseBody
    @GetMapping(value = "/delMyComment")
    public BaseReturnDTO delMyComment(HttpSession session,Integer commentId){
        UserInfo user= (UserInfo) session.getAttribute("user");
        return BaseReturnDTO.ok("评论成功",userCommentService.delMyComment(user.getuId(),commentId));
    }

    @ResponseBody
    @GetMapping(value = "/getHouseCommentPage")
    public BaseReturnDTO getHouseCommentPage(@RequestBody Page<UserComment> userCommentPage, Integer hId){
        return BaseReturnDTO.ok("查询成功",userCommentService.getHouseCommentPage(userCommentPage,hId));
    }

}
