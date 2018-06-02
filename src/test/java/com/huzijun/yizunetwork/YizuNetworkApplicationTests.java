package com.huzijun.yizunetwork;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.SMSPayload;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.huzijun.yizunetwork.common.PageDTO;
import com.huzijun.yizunetwork.core.house.DTO.UserFavoriteDTO;
import com.huzijun.yizunetwork.core.house.entity.HouseInfo;
import com.huzijun.yizunetwork.core.house.entity.UserFavorite;
import com.huzijun.yizunetwork.core.house.mapper.HouseInfoMapper;
import com.huzijun.yizunetwork.core.house.mapper.UserFavoriteMapper;
import com.huzijun.yizunetwork.core.house.service.HouseInfoService;
import com.huzijun.yizunetwork.core.user.service.JpushService;
import com.huzijun.yizunetwork.core.user.service.UserInfoService;
import com.huzijun.yizunetwork.core.user.web.UserInfoController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YizuNetworkApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);


    @Autowired
    UserInfoService userInfoService;

    @Autowired
    JpushService jpushService;

    @Autowired
    HouseInfoService houseInfoService;

    @Autowired
    HouseInfoMapper houseInfoMapper;

    @Autowired
    UserFavoriteMapper userFavoriteMapper;

    @Test
    public void contextLoads() {
//       UserInfo userInfo =  userInfoService.signInByLoginId("huzijun4","123456Aa");
//        System.out.println(userInfo.getuId());
    }

    @Test
    public void jpushTest(){
        SMSClient client = new SMSClient("a5117fc41f0c6a3ac1b00da9", "221949fbdfab330150a501c5");
        SMSPayload payload = SMSPayload.newBuilder().setMobileNumber("18819745892").setTempId(1).build();
        try {
            SendSMSResult res = client.sendSMSCode(payload);
            System.out.println(res.toString());
            logger.info(res.toString());
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Message: " + e.getMessage());
        }
    }

    @Test
    public void jpush(){
        System.out.println(jpushService.sendCheckMsg("18819745892"));
    }

    @Test
    public void checkJpush(){
        System.out.println(jpushService.validCheckMsg("504424462247","741327"));
    }

    @Test
    public void getHousePage(){
        PageDTO<HouseInfo>  houseInfoPageDTO = new PageDTO<>();
        HouseInfo houseInfo = new HouseInfo();
        houseInfo.setSubway("1");
        houseInfoPageDTO.setModel(houseInfo);
        Page<HouseInfo> page = houseInfoService.getPage(houseInfoPageDTO);
        System.out.println(page);
    }

    @Test
    public void test(){
        System.out.println(houseInfoMapper.selectExCount(6));
    }
}

