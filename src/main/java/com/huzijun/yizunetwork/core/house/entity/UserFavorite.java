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
 * 用户收藏记录表
 * </p>
 *
 * @author hzj
 * @since 2018-05-30
 */
@TableName("user_favorite")
public class UserFavorite extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 收藏表主键
     */
	@TableId("u_f_id")
	private Integer uFId;
    /**
     * 用户id
     */
	@TableField("u_id")
	private Integer uId;
    /**
     * 收藏房源id
     */
	@TableField("h_favorite")
	private Integer hFavorite;



	public Integer getuFId() {
		return uFId;
	}

	public void setuFId(Integer uFId) {
		this.uFId = uFId;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public Integer gethFavorite() {
		return hFavorite;
	}

	public void sethFavorite(Integer hFavorite) {
		this.hFavorite = hFavorite;
	}


}
