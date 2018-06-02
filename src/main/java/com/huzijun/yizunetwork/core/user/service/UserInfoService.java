package com.huzijun.yizunetwork.core.user.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.huzijun.yizunetwork.common.BaseService;
import com.huzijun.yizunetwork.common.BusinessBaseException;
import com.huzijun.yizunetwork.core.user.DTO.UserDTO;
import com.huzijun.yizunetwork.core.user.entity.UserInfo;
import com.huzijun.yizunetwork.core.user.mapper.UserInfoMapper;
import com.huzijun.yizunetwork.utils.MyStringUtil;
import com.huzijun.yizunetwork.utils.ValidataUtil;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * 易租网所有注册用户的信息(含管理员) 服务实现类
 * </p>
 *
 * @author hzj
 * @since 2018-05-15
 */
@Service
@Transactional
public class UserInfoService extends BaseService<UserInfoMapper, UserInfo>{

    private final static String DEFAULT_ICON = "/static/img/user-default.6aa5c4f.png";

    @Autowired
    private JpushService jpushService;

    @Autowired
    private FileService fileService;

    private static final Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    //共有注册验证
    private UserInfo signIn(UserInfo userInfo, String paramName) {
        userInfo.setuImgPath(DEFAULT_ICON);
        EntityWrapper wrapper = new EntityWrapper<UserInfo>();
        wrapper.setEntity(userInfo);
        if (selectOne(wrapper) != null)
            throw BusinessBaseException.fail("该" + paramName + "已存在");
        if (!insert(userInfo))
            throw BusinessBaseException.fail("注册失败");
        return userInfo;
    }

    //共有手机号验证
    private void checkPhone(String phone){
        if (MyStringUtil.isNull(phone))
            throw BusinessBaseException.fail("手机号不能为空");
        if (!ValidataUtil.isPhoneNumber(phone))
            throw BusinessBaseException.fail("手机号不合法");
    }

    //共有密码验证
    private void checkPwd(String pwd){
        if (MyStringUtil.isNull(pwd))
            throw BusinessBaseException.fail("密码不能为空");
        if (!ValidataUtil.isValidPwd(pwd))
            throw BusinessBaseException.fail("密码最少为6位字母数字混合");
    }

    public String upLoadUserIcon(HttpServletRequest request){
        String userIconUrl = fileService.upload(request).get(0);
        return userIconUrl;
    }

    public UserInfo loginByLoginId(UserDTO userDTO) {
        if (!MyStringUtil.isNull(userDTO.getMsgId()))
            throw BusinessBaseException.fail("参数错误");
        if (!MyStringUtil.isNull(userDTO.getCode()))
            throw BusinessBaseException.fail("参数错误");
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginId(userDTO.getLoginId() == null ? null: userDTO.getLoginId());
        userInfo.setLoginId(userDTO.getPhone() == null ? null: userDTO.getPhone());
        userInfo.setPwd(userDTO.getPwd());
        EntityWrapper wrapper = new EntityWrapper<UserInfo>();
        wrapper.setEntity(userInfo);
        userInfo = selectOne(wrapper);
        if (userInfo == null)
            throw BusinessBaseException.fail("用户名或者密码错误");
        if (!userInfo.getDkUException().equals(0))
            throw BusinessBaseException.fail("该用户已被封禁，请联系管理员");
        return userInfo;
    }

    public UserInfo loginByPhone(UserDTO userDTO) {
        if (!MyStringUtil.isNull(userDTO.getLoginId()))
            throw BusinessBaseException.fail("参数错误");
        if (!MyStringUtil.isNull(userDTO.getPwd()))
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
        if (!userInfo.getDkUException().equals(0))
            throw BusinessBaseException.fail("该用户已被封禁，请联系管理员");
        return userInfo;
    }


    public UserInfo signInByPhone(UserDTO userDTO) {
        checkPhone(userDTO.getPhone());
        if (MyStringUtil.isNull(userDTO.getMsgId()))
            throw BusinessBaseException.fail("验证码不能为空");
        checkPwd(userDTO.getPwd());
        boolean flag = jpushService.validCheckMsg(userDTO.getMsgId(),userDTO.getCode());
        if (flag == true) {
            UserInfo userInfo = new UserInfo();
            userInfo.setPhoneNo(userDTO.getPhone());
            userInfo.setPwd(userDTO.getPwd());
            return signIn(userInfo, "手机号");
        }
        throw BusinessBaseException.fail("验证码错误");
    }

    public UserInfo signInByLoginId(UserDTO userDTO) {
        if (MyStringUtil.isNull(userDTO.getLoginId()))
            throw BusinessBaseException.fail("用户名不能为空");
        checkPwd(userDTO.getPwd());
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


    //完善个人信息
    public Boolean completeUserInfo (UserInfo userInfo){
        UserInfo oldUser = selectById(userInfo.getuId());
        if (oldUser == null)
            throw BusinessBaseException.fail("该用户不存在");
        if (!ValidataUtil.isPhoneNumber(userInfo.getPhoneNo()))
            throw BusinessBaseException.fail("手机号不合法");
        if (!MyStringUtil.isNull(oldUser.getLoginId()) && !MyStringUtil.isNull(userInfo.getLoginId()))
            throw BusinessBaseException.fail("参数非法");
        if(!MyStringUtil.isNull(oldUser.getPhoneNo()) && !MyStringUtil.isNull(userInfo.getPhoneNo()))
            throw BusinessBaseException.fail("请先完善个人信息后，在修改登录手机号");
        if (MyStringUtil.isNull(oldUser.getLoginId()) && MyStringUtil.isNull(userInfo.getLoginId()))
            throw BusinessBaseException.fail("用户名不能为空");
        if (MyStringUtil.isNull(oldUser.getPhoneNo()) && MyStringUtil.isNull(userInfo.getPhoneNo()))
            throw BusinessBaseException.fail("联系电话不能为空");
        if (MyStringUtil.isNull(userInfo.getEmail()))
            throw BusinessBaseException.fail("E-mail不能为空");
        if(userInfo.getDkSex() == null)
            throw BusinessBaseException.fail("性别不能为空");
        if(userInfo.getAddress() == null)
            throw BusinessBaseException.fail("地址不能为空");
        Boolean flag = updateById(userInfo);
        return flag;
    }


    //更新个人信息
    public Boolean updateUserInfo(UserInfo userInfo) {
        if(selectById(userInfo.getuId()) == null)
            throw BusinessBaseException.fail("该用户不存在");
        if (!MyStringUtil.isNull(userInfo.getLoginId()))
            throw BusinessBaseException.fail("用户名不可以修改");
        if (!MyStringUtil.isNull(userInfo.getPhoneNo()))
            throw BusinessBaseException.fail("不可以使用该方式变更手机号");
        return updateById(userInfo);
    }

    //修改手机号
    public boolean changePhone(UserDTO userDTO){
        checkPhone(userDTO.getPhone());
        if (!jpushService.validCheckMsg(userDTO.getMsgId(),userDTO.getCode()))
            throw BusinessBaseException.fail("验证码有误");
        if (selectById(userDTO.getuId()) == null)
            throw BusinessBaseException.fail("该用户不存在");
        UserInfo userInfo = new UserInfo();
        userInfo.setuId(userDTO.getuId());
        userInfo.setPhoneNo(userDTO.getPhone());
        return updateById(userInfo);

    }

    public boolean changePwd(UserDTO userDTO) {
        UserInfo userInfo = selectById(userDTO.getuId());
        if (userInfo == null)
            throw BusinessBaseException.fail("该用户不存在");
        if (MyStringUtil.isNull(userInfo.getPwd()))
            throw BusinessBaseException.fail("请先完善个人信息");
        if (userInfo.getPwd().equals(userDTO.getPwd()))
            throw BusinessBaseException.fail("原密码错误");
        checkPwd(userDTO.getNewPwd());
        userInfo.setPwd(userDTO.getNewPwd());
        return updateById(userInfo);
    }

    public UserInfo getUserInfo(Integer uId){
        return  selectById(uId);
    }
}
