package com.huzijun.yizunetwork.core.admin.entity;

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
 * 管理员信息表
 * </p>
 *
 * @author hzj
 * @since 2018-06-01
 */
@TableName("admin_info")
public class AdminInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员id
     */
	@TableId("a_id")
	private Integer aId;
    /**
     * 管理员账号
     */
	@TableField("admin_id")
	private String adminId;
    /**
     * 管理员密码
     */
	@TableField("admin_pwd")
	private String adminPwd;


	public Integer getaId() {
		return aId;
	}

	public void setaId(Integer aId) {
		this.aId = aId;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}



}
