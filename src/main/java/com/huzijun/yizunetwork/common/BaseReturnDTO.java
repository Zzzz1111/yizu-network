package com.huzijun.yizunetwork.common;

import com.huzijun.yizunetwork.common.enm.ResultCode;

import java.io.Serializable;

public class ResultJson implements Serializable{
        private static final long serialVersionUID = 1L;
        /**
         * 返回结果
         */
        private boolean success;
        /**
         * 返回代码
         */
        private String resultCode;
        /**
         * 返回提示
         */
        private String resultMessage;
        /**
         * 返回的配置项
         */
        private Object obj;
        /**
         * 返回的业务数据
         */
        private Object busObj;

        public ResultJson(){

        }

        public ResultJson(boolean success,String resultCode,String resultMessage){
            this.success = success;
            this.resultCode = resultCode;
            this.resultMessage = resultMessage;
        }

        public static ResultJson ok(String message){
            ResultJson dto = new ResultJson(true,"200",message);
            return dto;
        }

        public static ResultJson ok(String message,Object busObj){
            ResultJson dto = new ResultJson(true,"200",message);
            dto.setBusObj(busObj);
            return dto;
        }

        public static ResultJson ok(String message,Object obj,Object busObj){
            ResultJson dto = new ResultJson(true,"200",message);
            dto.setBusObj(busObj);
            dto.setObj(obj);
            return dto;
        }

        public static ResultJson ok(String code,String message){
            ResultJson dto = new ResultJson(true,code,message);
            return dto;
        }

        public ResultJson(ResultCode code,String resultMessage){
            boolean success = (code == ResultCode.OK) ? true : false;
            this.resultCode =resultCode;
            this.resultMessage = resultMessage;
        }

        public ResultJson(ResultCode code, Object data) {
            boolean success = (code == ResultCode.OK) ? true : false;
            this.success = success;
            this.resultCode = code.getCode();
            this.resultMessage = code.getMessage();
            this.busObj = data;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public String getResultMessage() {
            return resultMessage;
        }

        public void setResultMessage(String resultMessage) {
            this.resultMessage = resultMessage;
        }

        public Object getObj() {
            return obj;
        }

        public void setObj(Object obj) {
            this.obj = obj;
        }

        public Object getBusObj() {
            return busObj;
        }

        public void setBusObj(Object busObj) {
            this.busObj = busObj;
        }

        public static ResultJson fail(String resultMessage){
            ResultJson dto = new ResultJson();
            dto.setSuccess(false);
            dto.setResultMessage(resultMessage);
            return dto;
        }

        public static ResultJson fail(ResultCode code){
            ResultJson dto = new ResultJson();
            dto.setSuccess(false);
            dto.setResultCode(code.getCode());
            dto.setResultMessage(code.getMessage());
            return dto;
        }

        public static ResultJson fail(String code,String resultMessage,Object busObj){
            ResultJson dto = new ResultJson();
            dto.setSuccess(false);
            dto.setResultCode(code);
            dto.setResultMessage(resultMessage);
            dto.setBusObj(busObj);
            return dto;
        }

        public static ResultJson fail(String resultCode,String resultMessage){
            ResultJson dto = new ResultJson();
            dto.setSuccess(false);
            dto.setResultCode(resultCode);
            dto.setResultMessage(resultMessage);
            return dto;
        }

        public static ResultJson success(){
            ResultJson dto = new ResultJson();
            dto.setSuccess(true);
            dto.setResultCode("200");
            dto.setResultMessage("操作成功");
            return dto;
        }

        @Override
        public String toString() {
            return "ResultJson{" +
                    "success=" + success +
                    ", resultCode='" + resultCode + '\'' +
                    ", resultMessage='" + resultMessage + '\'' +
                    ", obj=" + obj.toString() +
                    ", busObj=" + busObj.toString() +
                    '}';
        }
}

