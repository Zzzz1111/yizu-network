package com.huzijun.yizunetwork.core.house.web;

import com.huzijun.yizunetwork.common.BaseReturnDTO;
import com.huzijun.yizunetwork.core.house.entity.UserComment;
import com.huzijun.yizunetwork.core.house.service.UserCommentService;
import com.huzijun.yizunetwork.core.user.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Controller
@RequestMapping("/dict/userComment")
public class UserCommentController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserCommentController.class);

    @Autowired
    UserCommentService userCommentService;

    @PostMapping(value = "/commentHouseInfo")
    public BaseReturnDTO commentHouseInfo(HttpSession session,UserComment userComment){
        UserInfo user= (UserInfo) session.getAttribute("user");
        return BaseReturnDTO.ok("评论成功",userCommentService.commentHouse(user.getuId(),userComment));
    }

    @GetMapping(value = "/delMyComment")
    public BaseReturnDTO delMyComment(HttpSession session,Integer commentId){
        UserInfo user= (UserInfo) session.getAttribute("user");
        return BaseReturnDTO.ok("评论成功",userCommentService.delMyComment(user.getuId(),commentId));
    }

}
