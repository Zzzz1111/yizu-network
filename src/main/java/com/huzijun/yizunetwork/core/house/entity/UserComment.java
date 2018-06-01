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
 * 用户评论记录表
 * </p>
 *
 * @author hzj
 * @since 2018-05-31
 */
@TableName("user_comment")
public class UserComment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 评论表主键
     */
	@TableId("u_c_id")
	private Integer uCId;
    /**
     * 用户id
     */
	@TableField("u_id")
	private Integer uId;
    /**
     * 评论的房源id
     */
	@TableField("h_comment")
	private Integer hComment;
    /**
     * 我的评论
     */
	@TableField("my_comment")
	private String myComment;


	public Integer getuCId() {
		return uCId;
	}

	public void setuCId(Integer uCId) {
		this.uCId = uCId;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public Integer gethComment() {
		return hComment;
	}

	public void sethComment(Integer hComment) {
		this.hComment = hComment;
	}

	public String getMyComment() {
		return myComment;
	}

	public void setMyComment(String myComment) {
		this.myComment = myComment;
	}

}
