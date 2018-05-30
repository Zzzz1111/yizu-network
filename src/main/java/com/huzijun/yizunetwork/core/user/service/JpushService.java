package com.huzijun.yizunetwork.core.user.service;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.ValidSMSResult;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.SMSPayload;
import com.huzijun.yizunetwork.common.BusinessBaseException;
import com.huzijun.yizunetwork.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JpushService {

    private static final Logger logger = LoggerFactory.getLogger(JpushService.class);

    private static final String appKey = PropertiesUtil.getValueBykey("jpushConfig","appKey");

    private static final String masterSecret= PropertiesUtil.getValueBykey("jpushConfig","masterSecret");

    private static final Integer tempId = Integer.valueOf(PropertiesUtil.getValueBykey("jpushConfig","tempId"));;

    private static SMSClient smsClient;

    static {
        smsClient = new SMSClient("a5117fc41f0c6a3ac1b00da9", "221949fbdfab330150a501c5");
    }



    public String sendCheckMsg(String phone){
        SMSPayload payload = SMSPayload.newBuilder().setMobileNumber(phone).setTempId(tempId).build();
        try {
            SendSMSResult res = smsClient.sendSMSCode(payload);
            return res.toString();
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Message: " + e.getMessage());
        }
        throw BusinessBaseException.fail("服务异常");
    }

    public boolean validCheckMsg(String msgId,String code){
        try {
            ValidSMSResult res = smsClient.sendValidSMSCode(msgId,code);
            return res.getIsValid();
        } catch (APIConnectionException e) {
            e.printStackTrace();
            logger.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            e.printStackTrace();
            if (e.getErrorCode() == 50010)
                throw BusinessBaseException.fail("验证码无效");
            if (e.getErrorCode() == 50011)
                throw BusinessBaseException.fail("验证码过期");
            if (e.getErrorCode() == 50011)
                throw BusinessBaseException.fail("验证码已验证");
        }
        throw BusinessBaseException.fail("服务异常");
    }

}
