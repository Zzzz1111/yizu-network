package com.huzijun.yizunetwork.core.house.DTO;

import com.baomidou.mybatisplus.annotations.TableField;

public class UserFavoriteDTO {
    private Integer uFId;
    private Integer hFavorite;

    private String houseInfoTitle;


    private String houseInfoPubStatus;

    public Integer getuFId() {
        return uFId;
    }

    public void setuFId(Integer uFId) {
        this.uFId = uFId;
    }

    public Integer gethFavorite() {
        return hFavorite;
    }

    public void sethFavorite(Integer hFavorite) {
        this.hFavorite = hFavorite;
    }

    public String getHouseInfoTitle() {
        return houseInfoTitle;
    }

    public void setHouseInfoTitle(String houseInfoTitle) {
        this.houseInfoTitle = houseInfoTitle;
    }

    public String getHouseInfoPubStatus() {
        return houseInfoPubStatus;
    }

    public void setHouseInfoPubStatus(String houseInfoPubStatus) {
        this.houseInfoPubStatus = houseInfoPubStatus;
    }
}
