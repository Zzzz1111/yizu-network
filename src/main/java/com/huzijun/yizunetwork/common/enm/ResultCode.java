package com.huzijun.yizunetwork.common.enm;

public enum ResultCode {

        OK										("200","success"),
        PARAMETER_ERROR							("1001","请求参数错误"),
        LOGIN_FAIL								("903","登录失败"),
        LOGIN_TIMEOUT							("9031","登录超时"),
        SESSION_OVERDUE							("9032","SESSION过期"),
        //	LOGIN_NAME_EMPTY						("903","用户名不能为空"),
//	LOGIN_PASSWORD_EMPTY					("904","用户密码不能为空"),
        USERNAME_PASSWORD_ERROR					("905","账号与密码不匹配"),
        PASSWORD_EMPTY							("905","密码不能为空"),
        //	LOGIN_ACCOUNT_FAIL						("906","登录失败"),
//	LOGIN_ACCOUNT_FAIL_EXCEPTION			("907","登录失败，系统异常"),
        PASSWORD_ERROR							("908","密码错误"),
        DELETE_CURRENT_USER						("909","不能删除当前用户"),
        DELETE_ADMIN							("910","不能删除超级管理员"),

        FILE_EMPTY								("606","文件为空"),
        DATA_EMPTY								("607","数据为空"),

        UNAUTHORIZED							("403","没有权限"),


        SEND_MINA_MESSAGE_FAIL					("803","发送mina消息失败"),

        UNSUPPORT_MEDIA_TYPE 					("400","不支持的请求类型"),

        SEVER_ERROR 							("9998","服务器错误"),

        UNKNOWN									("9999","未知错误");

        private String code;
        private String message;

        ResultCode(String message) {
            this.message = message;
        }

        ResultCode(String code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public String toString() {
            return this.message;
        }

        /**
         * @return the code
         */
        public String getCode() {
            return this.code;
        }

        public String getMessage(){
            return this.message;
        }

        /**
         * 自定义错误提示, 错误代码为9999
         * @param desc
         * @return
         */
//	public static ResultCode customDesc(String desc){
//		UNKNOWN.message = desc;
//		return UNKNOWN;
//	}

//	public static ResultCode valueOf(String code){
//		for (ResultCode resultCode :values()) {
//			if (resultCode.code.equals(code)){
//				return resultCode;
//			}
//		}
//		return null;
//	}
    }

