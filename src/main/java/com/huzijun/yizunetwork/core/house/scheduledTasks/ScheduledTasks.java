package com.huzijun.yizunetwork.core.house.scheduledTasks;


import com.huzijun.yizunetwork.core.house.DTO.CleanHouseDTO;
import com.huzijun.yizunetwork.core.house.service.HouseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ScheduledTasks {
    private Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private HouseInfoService houseInfoService;

//    @Scheduled(cron = " */10 * * * * *" )
//    public void sayHi(){
//        System.out.println("Hello");
//    }

//    @Scheduled(cron = " */10 * * * * *" )
    @Scheduled(cron =  "0 0 0 * * ?")
    public void cleanOutTimeHouseInfo(){
        Date date = new Date();
        Date dBefore ;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,-6);
        dBefore = calendar.getTime();
        List<CleanHouseDTO> cleanHouseDTOList = houseInfoService.selectDTOList();
        List<Integer> needCleanList = new ArrayList<>();
        for (CleanHouseDTO cleanHouseDTO:cleanHouseDTOList ) {
            if (cleanHouseDTO.getPublishTime().before(dBefore))
                needCleanList.add(cleanHouseDTO.getId());
        }
        if (needCleanList.size() >0){
            Boolean flag = houseInfoService.cleanHouseInfo(needCleanList);
            logger.info("定时清除房源任务执行结果"+flag.toString());
        }
        else
            logger.info("没有需要清理的房源");
    }

}
