package com.huzijun.yizunetwork.login.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huzijun.yizunetwork.common.BaseEntity;
import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author hzj
 * @since 2018-04-02
 */
@TableName("yz_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

	private Integer id;
	@TableField("del_flag")
	@TableLogic
	private Integer delFlag;

	@Version
	private transient Integer version;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	@JsonIgnore
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}



}
