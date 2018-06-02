package com.huzijun.yizunetwork.core.house.entity;

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
 * 用户举报记录表
 * </p>
 *
 * @author hzj
 * @since 2018-06-01
 */
@TableName("user_tip_off")
public class UserTipOff extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 举报记录主键
     */
	@TableId("u_t_id")
	private Integer uTId;
    /**
     * 用户id
     */
	@TableField("u_id")
	private Integer uId;
    /**
     * 举报的房源id
     */
	@TableField("h_tip_off")
	private Integer hTipOff;
    /**
     * 举报状态
     */
	@TableField("dk_tip_status")
	private Integer dkTipStatus;
    /**
     * 房源举报原因描述
     */
	@TableField("h_tip_off_desc")
	private String hTipOffDesc;


	public Integer getuTId() {
		return uTId;
	}

	public void setuTId(Integer uTId) {
		this.uTId = uTId;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public Integer gethTipOff() {
		return hTipOff;
	}

	public void sethTipOff(Integer hTipOff) {
		this.hTipOff = hTipOff;
	}

	public Integer getDkTipStatus() {
		return dkTipStatus;
	}

	public void setDkTipStatus(Integer dkTipStatus) {
		this.dkTipStatus = dkTipStatus;
	}

	public String gethTipOffDesc() {
		return hTipOffDesc;
	}

	public void sethTipOffDesc(String hTipOffDesc) {
		this.hTipOffDesc = hTipOffDesc;
	}



}
