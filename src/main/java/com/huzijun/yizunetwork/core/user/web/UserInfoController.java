package com.huzijun.yizunetwork.core.user.web;

import com.huzijun.yizunetwork.common.BaseReturnDTO;
import com.huzijun.yizunetwork.core.user.DTO.UserDTO;
import com.huzijun.yizunetwork.core.user.entity.UserInfo;
import com.huzijun.yizunetwork.core.user.service.UserInfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.huzijun.yizunetwork.common.BaseController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 易租网所有注册用户的信息(含管理员) 前端控制器
 * </p>
 *
 * @author hzj
 * @since 2018-05-15
 */
@Api
@Controller
@RequestMapping("/usr")
public class UserInfoController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    @ResponseBody
    @PostMapping(value = "/loginByLoginId")
    public BaseReturnDTO loginByLoginId(@RequestBody UserDTO userDTO,HttpSession session){
        UserInfo userInfo = userInfoService.loginByLoginId(userDTO);
        if (userInfo.getuId() != null)
            session.setAttribute("user",userInfo);
        return BaseReturnDTO.ok("登录成功",userInfo);
    }

    @ResponseBody
    @PostMapping(value = "/signInByLoginId")
    public BaseReturnDTO signInByLoginId(@RequestBody UserDTO userDTO, HttpSession session){
        UserInfo userInfo = userInfoService.signInByLoginId(userDTO);
        if (userInfo.getuId() != null)
            session.setAttribute("user",userInfo);
        return BaseReturnDTO.ok("注册成功",userInfo);
    }

    @ResponseBody
    @PostMapping(value = "/loginByPhone")
    public BaseReturnDTO loginByPhone(@RequestBody UserDTO userDTO,HttpSession session){
        UserInfo userInfo = userInfoService.loginByPhone(userDTO);
        if (userInfo.getuId() != null)
            session.setAttribute("user",userInfo);
        return BaseReturnDTO.ok("登录成功",userInfo);
    }

    @ResponseBody
    @PostMapping(value = "/signInByPhone")
    public BaseReturnDTO signInByPhone(@RequestBody UserDTO userDTO,HttpSession session){
        UserInfo userInfo = userInfoService.signInByPhone(userDTO);
        if (userInfo.getuId() != null)
            session.setAttribute("user",userInfo);
        return BaseReturnDTO.ok("注册成功",userInfo);
    }

    @ResponseBody
    @GetMapping(value = "/sendCheckMsg")
    public BaseReturnDTO sendCheckMsg(String phone){
        return BaseReturnDTO.ok(userInfoService.sendCheckMsg(phone));
    }

    @ResponseBody
    @PostMapping(value = "/completeUserInfo")
    public BaseReturnDTO completeUserInfo(@RequestBody UserInfo userInfo,HttpSession session){
        UserInfo user= (UserInfo) session.getAttribute("user");
        userInfo.setuId(user.getuId());
        return BaseReturnDTO.ok("更新成功",userInfoService.completeUserInfo(userInfo));
    }

    @ResponseBody
    @PostMapping(value = "/updateUserInfo")
    public BaseReturnDTO updateUserInfo(@RequestBody UserInfo userInfo,HttpSession session){
        UserInfo user= (UserInfo) session.getAttribute("user");
        userInfo.setuId(user.getuId());
        return BaseReturnDTO.ok("更新成功",userInfoService.updateUserInfo(userInfo));
    }

    @ResponseBody
    @PostMapping(value = "/changePhone")
    public BaseReturnDTO changPhone(@RequestBody UserDTO userDTO,HttpSession session){
        UserInfo user= (UserInfo) session.getAttribute("user");
        userDTO.setuId(user.getuId());
        return BaseReturnDTO.ok("更新成功",userInfoService.changePhone(userDTO));
    }

    @ResponseBody
    @PostMapping(value = "/changePwd")
    public BaseReturnDTO changePWD(@RequestBody UserDTO userDTO,HttpSession session){
        UserInfo user= (UserInfo) session.getAttribute("user");
        userDTO.setuId(user.getuId());
        return BaseReturnDTO.ok("更新成功",userInfoService.changePwd(userDTO));
    }

    @ResponseBody
    @PostMapping(value = "/uploadUserIcon")
    public BaseReturnDTO uploadUserIcon(HttpServletRequest request){
        return BaseReturnDTO.ok(userInfoService.upLoadUserIcon(request));
    }

    @ResponseBody
    @GetMapping(value = "/getUserInfo")
    public BaseReturnDTO getUserInfo(Integer uId){
        return BaseReturnDTO.ok("查询成功",userInfoService.getUserInfo(uId));
    }

}
