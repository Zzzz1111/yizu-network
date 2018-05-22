package com.huzijun.yizunetwork.core.login.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.huzijun.yizunetwork.common.BaseReturnDTO;
import com.huzijun.yizunetwork.common.BaseService;
import com.huzijun.yizunetwork.common.BusinessBaseException;
import com.huzijun.yizunetwork.core.login.DTO.UserDTO;
import com.huzijun.yizunetwork.core.login.entity.UserInfo;
import com.huzijun.yizunetwork.core.login.mapper.UserInfoMapper;
import com.huzijun.yizunetwork.utils.MyStringUtil;
import com.huzijun.yizunetwork.utils.ValidataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 易租网所有注册用户的信息(含管理员) 服务实现类
 * </p>
 *
 * @author hzj
 * @since 2018-05-15
 */
@Service
public class UserInfoService extends BaseService<UserInfoMapper, UserInfo>{

    @Autowired
    JpushService jpushService;

    private static final Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    //共有注册验证
    private UserInfo signIn(UserInfo userInfo, String paramName) {
        EntityWrapper wrapper = new EntityWrapper<UserInfo>();
        wrapper.setEntity(userInfo);
        if (selectOne(wrapper) != null)
            throw BusinessBaseException.fail("该" + paramName + "已存在");
        if (!insert(userInfo))
            throw BusinessBaseException.fail("注册失败");
        return userInfo;
    }

    public UserInfo loginByLoginId(UserDTO userDTO) {
        if (MyStringUtil.isNull(userDTO.getPhone()))
            throw BusinessBaseException.fail("参数错误");
        if (MyStringUtil.isNull(userDTO.getMsgId()))
            throw BusinessBaseException.fail("参数错误");
        if (MyStringUtil.isNull(userDTO.getCode()))
            throw BusinessBaseException.fail("参数错误");
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginId(userDTO.getLoginId());
        userInfo.setPwd(userDTO.getPwd());
        EntityWrapper wrapper = new EntityWrapper<UserInfo>();
        wrapper.setEntity(userInfo);
        userInfo = selectOne(wrapper);
        if (userInfo == null)
            throw BusinessBaseException.fail("用户名或者密码错误");
        return userInfo;
    }

    public UserInfo loginByPhone(UserDTO userDTO) {
        if (MyStringUtil.isNull(userDTO.getLoginId()))
            throw BusinessBaseException.fail("参数错误");
        if (MyStringUtil.isNull(userDTO.getPwd()))
            throw BusinessBaseException.fail("参数错误");
        boolean flag = jpushService.validCheckMsg(userDTO.getMsgId(),userDTO.getCode());
        if (flag == false)
            throw BusinessBaseException.fail("验证码错误");
        UserInfo userInfo = new UserInfo();
        userInfo.setPhoneNo(userDTO.getPhone());
        EntityWrapper wrapper = new EntityWrapper<UserInfo>();
        wrapper.eq("phone_no", userInfo.getPhoneNo());
        userInfo = selectOne(wrapper);
        if (userInfo == null)
            throw BusinessBaseException.fail("手机号不存在");
        return userInfo;
    }


    public UserInfo signInByPhone(UserDTO userDTO) {
        if (MyStringUtil.isNull(userDTO.getPhone()))
            throw BusinessBaseException.fail("手机号不能为空");
        if (!ValidataUtil.isPhoneNumber(userDTO.getPhone()))
            throw BusinessBaseException.fail("手机号不合法");
        if (MyStringUtil.isNull(userDTO.getMsgId()))
            throw BusinessBaseException.fail("验证码不能为空");
        boolean flag = jpushService.validCheckMsg(userDTO.getMsgId(),userDTO.getCode());
        if (flag == true) {
            UserInfo userInfo = new UserInfo();
            userInfo.setPhoneNo(userDTO.getPhone());
            return signIn(userInfo, "手机号");
        }
        throw BusinessBaseException.fail("验证码错误");
    }

    public UserInfo signInByLoginId(UserDTO userDTO) {
        if (MyStringUtil.isNull(userDTO.getLoginId()))
            throw BusinessBaseException.fail("用户名不能为空");
        if (MyStringUtil.isNull(userDTO.getPwd()))
            throw BusinessBaseException.fail("密码不能为空");
        if (!ValidataUtil.isValidPwd(userDTO.getPwd()))
            throw BusinessBaseException.fail("密码最少为6位字母数字混合");
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginId(userDTO.getLoginId());
        userInfo.setPwd(userDTO.getPwd());
        return signIn(userInfo, "用户名");
    }

    public String sendCheckMsg(String phone){
        if (MyStringUtil.isNull(phone))
            throw BusinessBaseException.fail("手机号不能为空");
        if (!ValidataUtil.isPhoneNumber(phone))
            throw BusinessBaseException.fail("手机号不合法");
        return jpushService.sendCheckMsg(phone);
    }

}
