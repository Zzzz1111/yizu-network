package com.huzijun.yizunetwork.core.house.web;

import com.baomidou.mybatisplus.plugins.Page;
import com.huzijun.yizunetwork.common.BaseController;
import com.huzijun.yizunetwork.common.BaseReturnDTO;
import com.huzijun.yizunetwork.common.PageDTO;
import com.huzijun.yizunetwork.common.enm.ValidatedGroup;
import com.huzijun.yizunetwork.core.house.entity.HouseInfo;
import com.huzijun.yizunetwork.core.house.service.HouseInfoService;
import com.huzijun.yizunetwork.core.user.entity.UserInfo;
import com.huzijun.yizunetwork.utils.ValidataUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 易租网房源信息 前端控制器
 * </p>
 *
 * @author hzj
 * @since 2018-05-29
 */
@Api
@RestController
@RequestMapping("/house/houseInfo")
public class HouseInfoController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(HouseInfoController.class);

    @Autowired
    private HouseInfoService houseInfoService;

    @PostMapping(value = "/publishHouseInfo")
    public BaseReturnDTO publishHouseInfo(HttpSession session,@Validated(ValidatedGroup.Insert.class)@RequestBody HouseInfo houseInfo){
        UserInfo user= (UserInfo) session.getAttribute("user");
        return BaseReturnDTO.ok("操作成功",houseInfoService.publishHouseInfo(user.getuId(),houseInfo));
    }

    @PostMapping(value = "/updateHouseInfo")
    public BaseReturnDTO updateHouseInfo(@RequestBody HouseInfo houseInfo){
        return BaseReturnDTO.ok("更新成功",houseInfoService.updateHouseInfo(houseInfo));
    }

    @GetMapping(value = "/selectDetail")
    public BaseReturnDTO selectDetail(Integer hId){
        return  BaseReturnDTO.ok("查询成功",houseInfoService.selectDetail(hId));
    }

    @PostMapping(value = "/getPage")
    public BaseReturnDTO selectPage(@RequestBody PageDTO<HouseInfo> pageDTO){
        return  BaseReturnDTO.ok("查询成功",houseInfoService.getPage(pageDTO));
    }

    @PostMapping(value = "/uploadHouseImg")
    public BaseReturnDTO uploadHouseImg(HttpServletRequest request){
        return BaseReturnDTO.ok("上传成功",houseInfoService.uploadHouseImg(request));
    }

    @GetMapping(value = "/delHouseInfo")
    public BaseReturnDTO delHouseInfo(HttpSession session,Integer hId){
        UserInfo user= (UserInfo) session.getAttribute("user");
        return BaseReturnDTO.ok("操作成功",houseInfoService.delHouseInfo(user.getuId(),hId));
    }

    @PostMapping(value = "/getMyPage")
    public BaseReturnDTO getMyPage(HttpSession session,@RequestBody PageDTO<HouseInfo> pageDTO){
        UserInfo user= (UserInfo) session.getAttribute("user");
        return BaseReturnDTO.ok("查询成功",houseInfoService.getMyPage(pageDTO,user.getuId()));
    }


}
