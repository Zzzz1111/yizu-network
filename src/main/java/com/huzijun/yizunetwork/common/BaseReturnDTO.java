package com.huzijun.yizunetwork.common;

import com.huzijun.yizunetwork.common.enm.ResultCode;

import java.io.Serializable;

public class BaseReturnDTO implements Serializable{
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

        public BaseReturnDTO(){

        }

        public BaseReturnDTO(boolean success, String resultCode, String resultMessage){
            this.success = success;
            this.resultCode = resultCode;
            this.resultMessage = resultMessage;
        }

        public static BaseReturnDTO ok(String message){
            BaseReturnDTO dto = new BaseReturnDTO(true,"200",message);
            return dto;
        }

        public static BaseReturnDTO ok(String message, Object busObj){
            BaseReturnDTO dto = new BaseReturnDTO(true,"200",message);
            dto.setBusObj(busObj);
            return dto;
        }

        public static BaseReturnDTO ok(String message, Object obj, Object busObj){
            BaseReturnDTO dto = new BaseReturnDTO(true,"200",message);
            dto.setBusObj(busObj);
            dto.setObj(obj);
            return dto;
        }

        public static BaseReturnDTO ok(String code, String message){
            BaseReturnDTO dto = new BaseReturnDTO(true,code,message);
            return dto;
        }

        public BaseReturnDTO(ResultCode code, String resultMessage){
            boolean success = (code == ResultCode.OK) ? true : false;
            this.resultCode =resultCode;
            this.resultMessage = resultMessage;
        }

        public BaseReturnDTO(ResultCode code, Object data) {
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

        public static BaseReturnDTO fail(String resultMessage){
            BaseReturnDTO dto = new BaseReturnDTO();
            dto.setSuccess(false);
            dto.setResultMessage(resultMessage);
            return dto;
        }

        public static BaseReturnDTO fail(ResultCode code){
            BaseReturnDTO dto = new BaseReturnDTO();
            dto.setSuccess(false);
            dto.setResultCode(code.getCode());
            dto.setResultMessage(code.getMessage());
            return dto;
        }

        public static BaseReturnDTO fail(String code, String resultMessage, Object busObj){
            BaseReturnDTO dto = new BaseReturnDTO();
            dto.setSuccess(false);
            dto.setResultCode(code);
            dto.setResultMessage(resultMessage);
            dto.setBusObj(busObj);
            return dto;
        }

        public static BaseReturnDTO fail(String resultCode, String resultMessage){
            BaseReturnDTO dto = new BaseReturnDTO();
            dto.setSuccess(false);
            dto.setResultCode(resultCode);
            dto.setResultMessage(resultMessage);
            return dto;
        }

        public static BaseReturnDTO success(){
            BaseReturnDTO dto = new BaseReturnDTO();
            dto.setSuccess(true);
            dto.setResultCode("200");
            dto.setResultMessage("操作成功");
            return dto;
        }

        @Override
        public String toString() {
            return "BaseReturnDTO{" +
                    "success=" + success +
                    ", resultCode='" + resultCode + '\'' +
                    ", resultMessage='" + resultMessage + '\'' +
                    ", obj=" + obj.toString() +
                    ", busObj=" + busObj.toString() +
                    '}';
        }
}

