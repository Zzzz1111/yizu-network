package com.huzijun.yizunetwork.core.login.web;

import com.huzijun.yizunetwork.common.BaseReturnDTO;
import com.huzijun.yizunetwork.core.login.DTO.UserDTO;
import com.huzijun.yizunetwork.core.login.entity.UserInfo;
import com.huzijun.yizunetwork.core.login.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.huzijun.yizunetwork.common.BaseController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 易租网所有注册用户的信息(含管理员) 前端控制器
 * </p>
 *
 * @author hzj
 * @since 2018-05-15
 */
@Controller
@RequestMapping("/login/userInfo")
public class UserInfoController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    UserInfoService userInfoService;

    @ResponseBody
    @PostMapping(value = "/loginByLoginId")
    public BaseReturnDTO loginByLoginId(UserDTO userDTO,HttpSession session){
        UserInfo userInfo = userInfoService.loginByLoginId(userDTO);
        if (userInfo.getuId() != null)
            session.setAttribute("userInfo",userInfo);
        return BaseReturnDTO.ok("登录成功",userInfo);
    }

    @ResponseBody
    @PostMapping(value = "/signInByLoginId")
    public BaseReturnDTO signInByLoginId(UserDTO userDTO, HttpSession session){
        UserInfo userInfo = userInfoService.signInByLoginId(userDTO);
        if (userInfo.getuId() != null)
            session.setAttribute("userInfo",userInfo);
        return BaseReturnDTO.ok("注册成功",userInfo);
    }

    @ResponseBody
    @PostMapping(value = "/loginByPhone")
    public BaseReturnDTO loginByPhone(UserDTO userDTO,HttpSession session){
        UserInfo userInfo = userInfoService.loginByPhone(userDTO);
        if (userInfo.getuId() != null)
            session.setAttribute("userInfo",userInfo);
        return BaseReturnDTO.ok("登录成功",userInfo);
    }

    @ResponseBody
    @PostMapping(value = "/signInByPhone")
    public BaseReturnDTO signInByPhone(UserDTO userDTO,HttpSession session){
        UserInfo userInfo = userInfoService.signInByPhone(userDTO);
        if (userInfo.getuId() != null)
            session.setAttribute("userInfo",userInfo);
        return BaseReturnDTO.ok("注册成功",userInfo);
    }

    @ResponseBody
    @GetMapping(value = "/sendCheckMsg")
    public BaseReturnDTO sendCheckMsg(String phone){
        return BaseReturnDTO.ok("发送成功",userInfoService.sendCheckMsg(phone));
    }


}
