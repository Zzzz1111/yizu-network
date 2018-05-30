package com.huzijun.yizunetwork.core.user.DTO;

//登录注册使用
public class UserDTO {
    private Integer uId;

    private String loginId;

    private String pwd;

    private String msgId;

    private String code;

    private String phone;

    private String newPwd;

    //登录途径app,web
    private Integer way;

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

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getWay() {
        return way;
    }

    public void setWay(Integer way) {
        this.way = way;
    }


    public String getPhone() {
        return phone;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
