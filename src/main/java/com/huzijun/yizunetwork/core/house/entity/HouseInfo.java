package com.huzijun.yizunetwork.core.house.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.huzijun.yizunetwork.common.BaseEntity;
import com.huzijun.yizunetwork.common.enm.ValidatedGroup;
import com.huzijun.yizunetwork.utils.ValidataUtil;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * <p>
 * 易租网房源信息
 * </p>
 *
 * @author hzj
 * @since 2018-05-29
 */
@TableName("house_info")
public class HouseInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 房源id号
     */
	@TableId("h_id")
	private Integer hId;
    /**
     * 发布用户id
     */
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "发布用户不能为空")
	@TableField("u_id")
	private Integer uId;
    /**
     * 房源出租方式：0：整租，1：合租
     */
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "房屋出租方式不能为空")
	@TableField("dk_rental_way")
	private Integer dkRentalWay;

	@TableField("dk_room_type")
	private Integer dkRoomType;
    /**
     * 标题
     */
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "标题不能为空")
    private String title;
    /**
     * 室
     */
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "室不能为空")
	private Integer room;
    /**
     * 厅
     */
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "厅不能为空")
	private Integer hall;
    /**
     * 卫
     */
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "卫不能为空")
	private Integer toilet;
    /**
     * 房源面积
     */
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "房源面积不能为空")
	private Integer area;
    /**
     * 房源朝向orientations
     */
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "房源朝向不能为空")
	@TableField("dk_orient")
	private Integer dkOrient;
    /**
     * 房源装修类型：0：毛坯，1：简装修，2：中等装修，3：精装修，4：豪华装修
     */
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "房源装修类型不能为空")
	@TableField("dk_decoration")
	private Integer dkDecoration;
    /**
     * 房源楼层
     */
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "房源楼层不能为空")
	private Integer floor;
    /**
     * 房源总楼层
     */
    @NotBlank(groups = ValidatedGroup.Insert.class,message = " 房源总楼层不能为空")
	@TableField("total_floor")
	private Integer totalFloor;
    /**
     * 租金
     */
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "租金不能为空")
	private BigDecimal rental;
    /**
     * 房源押付类型：1：押一付一，2：押一付二，3：押一付三，4：押二付一，5：押二付二，6：半年付，7：年付，8：面议
     */
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "房源押付不能为空")
	@TableField("dk_rental_type")
	private Integer dkRentalType;
    /**
     * 水费/月
     */
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "电费不能为空")
	@TableField("water_rate")
	private BigDecimal waterRate;
    /**
     * 电费/月
     */
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "水费不能为空")
	@TableField("power_rate")
	private BigDecimal powerRate;
    /**
     * 租金包含费用
     */
	@TableField("dk_rental_cost")
	private String dkRentalCost;
    /**
     * 房源配置
     */
	@TableField("dk_configure")
	private String dkConfigure;
    /**
     * 租赁要求
     */
	@TableField("dk_rental_demand")
	private String dkRentalDemand;
    /**
     * 看房时间：0：仅工作日，1：仅周末，2：随时看房
     */
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "看房时间不能为空")
	@TableField("dk_looktime")
	private Integer dkLooktime;
    /**
     * 适宜居住人数
     */
	private Integer suitable;
    /**
     * 房源联系人姓名
     */
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "房源联系人姓名不能为空")
	@TableField("cont_name")
	private String contName;
    /**
     * 房源联系人电话
     */
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "房源联系人电话不能为空")
	@TableField("cont_phone")
	private String contPhone;
    /**
     * 简介
     */
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "简介不能为空")
	private String introduce;
    /**
     * 详细地址
     */
	private String address;
    /**
     * 地址经度
     */
	@TableField("add_longitude")
	private String addLongitude;
    /**
     * 地址纬度
     */
	@TableField("add_latitude")
	private String addLatitude;
    /**
     * 房源图片地址
     */
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "房源图片地址不能为空")
	@TableField("h_img_path")
	private String hImgPath;
    /**
     * 点击率Click-Through-Rate
     */
	private Integer ctr;
    /**
     * 收藏率
     */
	private Integer favorite;
    /**
     * 举报率
     */
	@TableField("tip_off")
	private Integer tipOff;
    /**
     * 发布状态
     */
	@TableField("dk_pub_status")
	private Integer dkPubStatus;
    /**
     * 发布时间
     */
	@TableField("publish_time")
	private Date publishTime;
    /**
     * 房源异常状态
     */
	@TableField("dk_h_exception")
	private Integer dkHException;

	//最近地铁站点
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "最近地铁站点不能为空")
	@TableField("dk_sub_station")
	private String dkSubStation;

	//房源区域
	@NotBlank(groups = ValidatedGroup.Insert.class,message = "房源区域不能为空")
	@TableField("dk_add_area")
	private Integer dkAddArea;

	@TableField(exist = false)
	private String subway;

	@TableField(exist = false)
	private String[] subStations;

	@TableField(exist = false)
	private String priceRange;

	@TableField(exist = false)
	private String addressKey;

	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	public String[] getSubStations() {
		return subStations;
	}

	public void setSubStations(String[] subStations) {
		this.subStations = subStations;
	}

	public String getDkSubStation() {
		return dkSubStation;
	}

	public void setDkSubStation(String dkSubStation) {
		this.dkSubStation = dkSubStation;
	}

	public Integer getDkAddArea() {
		return dkAddArea;
	}

	public void setDkAddArea(Integer dkAddArea) {
		this.dkAddArea = dkAddArea;
	}

	public Integer gethId() {
		return hId;
	}

	public void sethId(Integer hId) {
		this.hId = hId;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public Integer getDkRentalWay() {
		return dkRentalWay;
	}

	public void setDkRentalWay(Integer dkRentalWay) {
		this.dkRentalWay = dkRentalWay;
	}

	public Integer getDkRoomType() {
		return dkRoomType;
	}

	public void setDkRoomType(Integer dkRoomType) {
		this.dkRoomType = dkRoomType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}

	public Integer getHall() {
		return hall;
	}

	public void setHall(Integer hall) {
		this.hall = hall;
	}

	public Integer getToilet() {
		return toilet;
	}

	public void setToilet(Integer toilet) {
		this.toilet = toilet;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getDkOrient() {
		return dkOrient;
	}

	public void setDkOrient(Integer dkOrient) {
		this.dkOrient = dkOrient;
	}

	public Integer getDkDecoration() {
		return dkDecoration;
	}

	public void setDkDecoration(Integer dkDecoration) {
		this.dkDecoration = dkDecoration;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Integer getTotalFloor() {
		return totalFloor;
	}

	public void setTotalFloor(Integer totalFloor) {
		this.totalFloor = totalFloor;
	}

	public BigDecimal getRental() {
		return rental;
	}

	public void setRental(BigDecimal rental) {
		this.rental = rental;
	}

	public Integer getDkRentalType() {
		return dkRentalType;
	}

	public void setDkRentalType(Integer dkRentalType) {
		this.dkRentalType = dkRentalType;
	}

	public BigDecimal getWaterRate() {
		return waterRate;
	}

	public void setWaterRate(BigDecimal waterRate) {
		this.waterRate = waterRate;
	}

	public BigDecimal getPowerRate() {
		return powerRate;
	}

	public void setPowerRate(BigDecimal powerRate) {
		this.powerRate = powerRate;
	}

	public String getDkRentalCost() {
		return dkRentalCost;
	}

	public void setDkRentalCost(String dkRentalCost) {
		this.dkRentalCost = dkRentalCost;
	}

	public String getDkConfigure() {
		return dkConfigure;
	}

	public void setDkConfigure(String dkConfigure) {
		this.dkConfigure = dkConfigure;
	}

	public String getDkRentalDemand() {
		return dkRentalDemand;
	}

	public void setDkRentalDemand(String dkRentalDemand) {
		this.dkRentalDemand = dkRentalDemand;
	}

	public Integer getDkLooktime() {
		return dkLooktime;
	}

	public void setDkLooktime(Integer dkLooktime) {
		this.dkLooktime = dkLooktime;
	}

	public Integer getSuitable() {
		return suitable;
	}

	public void setSuitable(Integer suitable) {
		this.suitable = suitable;
	}

	public String getContName() {
		return contName;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public String getContPhone() {
		return contPhone;
	}

	public void setContPhone(String contPhone) {
		this.contPhone = contPhone;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddLongitude() {
		return addLongitude;
	}

	public void setAddLongitude(String addLongitude) {
		this.addLongitude = addLongitude;
	}

	public String getAddLatitude() {
		return addLatitude;
	}

	public void setAddLatitude(String addLatitude) {
		this.addLatitude = addLatitude;
	}

	public String gethImgPath() {
		return hImgPath;
	}

	public void sethImgPath(String hImgPath) {
		this.hImgPath = hImgPath;
	}

	public Integer getCtr() {
		return ctr;
	}

	public void setCtr(Integer ctr) {
		this.ctr = ctr;
	}

	public Integer getFavorite() {
		return favorite;
	}

	public void setFavorite(Integer favorite) {
		this.favorite = favorite;
	}

	public Integer getTipOff() {
		return tipOff;
	}

	public void setTipOff(Integer tipOff) {
		this.tipOff = tipOff;
	}

	public Integer getDkPubStatus() {
		return dkPubStatus;
	}

	public void setDkPubStatus(Integer dkPubStatus) {
		this.dkPubStatus = dkPubStatus;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getDkHException() {
		return dkHException;
	}

	public void setDkHException(Integer dkHException) {
		this.dkHException = dkHException;
	}

	public String getSubway() {
		return subway;
	}

	public void setSubway(String subway) {
		this.subway = subway;
	}

	public String getAddressKey() {
		return addressKey;
	}

	public void setAddressKey(String addressKey) {
		this.addressKey = addressKey;
	}


}
