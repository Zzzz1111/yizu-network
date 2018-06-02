package com.huzijun.yizunetwork.core.dict.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.huzijun.yizunetwork.common.BaseEntity;



/**
 * <p>
 * 广州地铁站点字典表
 * </p>
 *
 * @author hzj
 * @since 2018-05-30
 */
@TableName("dict_gz_sub_station")
public class DictGzSubStation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 站点字典代码
     */
	private String code;
    /**
     * 线路代码
     */
	@TableField("line_code")
	private String lineCode;
    /**
     * 站点字典名称
     */
	private String name;


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLineCode() {
		return lineCode;
	}

	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
