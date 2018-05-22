package com.huzijun.yizunetwork.core.login.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.huzijun.yizunetwork.common.BaseEntity;
import java.io.Serializable;


/**
 * <p>
 * 易租网所有注册用户的信息(含管理员)
 * </p>
 *
 * @author hzj
 * @since 2018-05-15
 */
@TableName("user_info")
public class UserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id号
     */
	@TableId("u_id")
	private Integer uId;
    /**
     * 登录账号
     */
	@TableField("login_id")
	private String loginId;
    /**
     * 登录密码
     */
	private String pwd;
    /**
     * 姓名
     */
	private String name;
    /**
     * 用户头像图片地址
     */
	@TableField("u_img_path")
	private String uImgPath;
    /**
     * 性别：1：男，2：女
     */
	@TableField("dk_sex")
	private Integer dkSex;
    /**
     * 实名认证状态：N：否，Y：是
     */
	@TableField("is_certification")
	private String isCertification;
    /**
     * 证件类型：0：未知，1：身份证，2：港澳居民来往内地通行证，3：中国护照，4：外籍护照，5：户口本，6：士兵证，7：军官证，8：台胞证，9：其他
            
            
     */
	@TableField("dk_identity_type")
	private Integer dkIdentityType;
    /**
     * 证件号码
     */
	@TableField("idcard_no")
	private String idcardNo;
    /**
     * 手机号码
     */
	@TableField("phone_no")
	private String phoneNo;
    /**
     * 电子邮件
     */
	private String email;
    /**
     * 居住地址
     */
	private String address;
    /**
     * 用户异常标识：0：正常
     */
	@TableField("dk_u_exception")
	private Integer dkUException;


	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getuImgPath() {
		return uImgPath;
	}

	public void setuImgPath(String uImgPath) {
		this.uImgPath = uImgPath;
	}

	public Integer getDkSex() {
		return dkSex;
	}

	public void setDkSex(Integer dkSex) {
		this.dkSex = dkSex;
	}

	public String getIsCertification() {
		return isCertification;
	}

	public void setIsCertification(String isCertification) {
		this.isCertification = isCertification;
	}

	public Integer getDkIdentityType() {
		return dkIdentityType;
	}

	public void setDkIdentityType(Integer dkIdentityType) {
		this.dkIdentityType = dkIdentityType;
	}

	public String getIdcardNo() {
		return idcardNo;
	}

	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getDkUException() {
		return dkUException;
	}

	public void setDkUException(Integer dkUException) {
		this.dkUException = dkUException;
	}



}
