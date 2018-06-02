package com.huzijun.yizunetwork.core.house.service;

import cn.jiguang.common.utils.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.huzijun.yizunetwork.common.BaseService;
import com.huzijun.yizunetwork.common.BusinessBaseException;
import com.huzijun.yizunetwork.common.PageDTO;
import com.huzijun.yizunetwork.core.dict.service.DictGzSubStationService;
import com.huzijun.yizunetwork.core.house.DTO.CleanHouseDTO;
import com.huzijun.yizunetwork.core.house.entity.HouseInfo;
import com.huzijun.yizunetwork.core.house.mapper.HouseInfoMapper;
import com.huzijun.yizunetwork.core.user.service.FileService;
import com.huzijun.yizunetwork.utils.MyStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 易租网房源信息 服务实现类
 * </p>
 *
 * @author hzj
 * @since 2018-05-29
 */
@Service
@Transactional
public class HouseInfoService extends BaseService<HouseInfoMapper,HouseInfo>{

    @Autowired
    private FileService fileService;


    @Autowired
    private DictGzSubStationService dictGzSubStationService;



    //共有房源建议
    private void commonCheckHouseInfo(HouseInfo houseInfo) {
        if (houseInfo.getDkHException() != null)
            throw BusinessBaseException.fail("更新参数非法");
        if (houseInfo.getCtr() != null)
            throw BusinessBaseException.fail("更新参数非法");
        if (houseInfo.getTipOff() != null)
            throw BusinessBaseException.fail("更新参数非法");
        if (houseInfo.getDkPubStatus() != null)
            throw BusinessBaseException.fail("更新参数非法");
        if (houseInfo.getPublishTime() != null)
            throw BusinessBaseException.fail("更新参数非法");
        if (houseInfo.getFavorite() != null)
            throw BusinessBaseException.fail("更新参数非法");
    }

    //发布房源
    public boolean publishHouseInfo(Integer uId,HouseInfo houseInfo){
        houseInfo.setuId(uId);
        commonCheckHouseInfo(houseInfo);
        if(houseInfo.getDkRentalWay() == null)
            throw BusinessBaseException.fail("房源出租方式不能为空");
        if(houseInfo.getDkRentalWay().equals(1)){
            if (houseInfo.getDkRoomType() == null)
                throw BusinessBaseException.fail("主次卧不能为空");
        }
        if(MyStringUtil.isNull(houseInfo.getTitle()))
            throw BusinessBaseException.fail("标题不能为空");
        if(houseInfo.getRoom() == null)
            throw BusinessBaseException.fail("房间数不能为空");
        if(houseInfo.getHall() == null)
            throw BusinessBaseException.fail("厅数不能为空");
        if(houseInfo.getToilet() == null)
            throw BusinessBaseException.fail("卫浴数不能为空");
        if(houseInfo.getArea()== null)
            throw BusinessBaseException.fail("房源面积不能为空");
        if(houseInfo.getDkOrient() == null)
            throw BusinessBaseException.fail("房源朝向不能为空");
        if(houseInfo.getDkDecoration() == null)
            throw BusinessBaseException.fail("房源装修类型不能为空");
        if(houseInfo.getRental()== null)
            throw BusinessBaseException.fail("租金不能为空");
        if(houseInfo.getDkRentalType() == null)
            throw BusinessBaseException.fail("压付类型不能为空");
        if(houseInfo.getWaterRate() == null)
            throw BusinessBaseException.fail("水费不能为空");
        if(houseInfo.getPowerRate() == null)
            throw BusinessBaseException.fail("电费不能为空");
        if(houseInfo.getDkLooktime() == null)
            throw BusinessBaseException.fail("看房时间不能为空");
        if(MyStringUtil.isNull(houseInfo.getContName()))
            throw BusinessBaseException.fail("联系人姓名不能为空");
        if(houseInfo.getContPhone() == null)
            throw BusinessBaseException.fail("联系人电话不能为空");
        if (MyStringUtil.isNull(houseInfo.getAddress()))
            throw BusinessBaseException.fail("详细地址不能为空");
        if (MyStringUtil.isNull(houseInfo.getIntroduce()))
            throw BusinessBaseException.fail("简介不能为空");
        if (!MyStringUtil.isSize(houseInfo.getIntroduce(),9,301))
            throw BusinessBaseException.fail("简介字数过少或者超出");
        if (houseInfo.getDkConfigures() != null && houseInfo.getDkConfigures().length > 0)
            houseInfo.setDkConfigure(StringUtils.arrayToString(houseInfo.getDkConfigures()));
        if (houseInfo.getDkRentalCosts() != null && houseInfo.getDkRentalCosts().length > 0)
            houseInfo.setDkRentalCost(StringUtils.arrayToString(houseInfo.getDkRentalCosts()));
        if (houseInfo.getDkRentalDemands() != null && houseInfo.getDkRentalDemands().length > 0)
            houseInfo.setDkRentalDemand(StringUtils.arrayToString(houseInfo.getDkRentalDemands()));
        if (houseInfo.gethImgPaths() != null && houseInfo.gethImgPaths().length > 0)
            houseInfo.sethImgPath(StringUtils.arrayToString(houseInfo.gethImgPaths()));
        return insert( houseInfo);
    }

    public boolean updateHouseInfo(HouseInfo houseInfo){
        commonCheckHouseInfo(houseInfo);
        commonExistsCheck(houseInfo.gethId());
        if (houseInfo.getuId() != null)
            throw BusinessBaseException.fail("不能修改发布人");
        if (MyStringUtil.isNull(houseInfo.getIntroduce()))
            throw BusinessBaseException.fail("简介不能为空");
        if (!MyStringUtil.isSize(houseInfo.getIntroduce(),9,301))
            throw BusinessBaseException.fail("简介字数过少或者超出");
        return updateById(houseInfo);
    }

    public boolean delHouseInfo(Integer uId,Integer hId){
        HouseInfo houseInfo = commonExistsCheck(hId);
        if (!houseInfo.getuId().equals(uId))
            throw BusinessBaseException.fail("不能删除非本人发布房源");
        if (houseInfo.getDkHException() != 0)
            throw BusinessBaseException.fail("该房源异常，无法操作");
        return deleteById(hId);
    }

    public HouseInfo selectDetail(Integer hId){
        HouseInfo houseInfo = commonExistsCheck(hId);
        houseInfo.setCtr(houseInfo.getCtr() + 1);
        updateById(houseInfo);
        if (houseInfo.getDkHException() != 0)
            throw BusinessBaseException.fail("该房源异常，无法展示");
        return houseInfo;
    }

    public Page<HouseInfo> getPage(PageDTO<HouseInfo> houseInfoPageDTO){
        Page<HouseInfo> houseInfoPage = new Page<>();
        HouseInfo model;
        String[] subStations;
        //判断排序当前页面是否为空
        if (houseInfoPageDTO.getCurrent() != null)
            houseInfoPage.setCurrent(houseInfoPageDTO.getCurrent());
        //判断排序页面大小是否为空
        if (houseInfoPageDTO.getSize() != null)
            houseInfoPage.setSize(houseInfoPageDTO.getSize());
        //判断排序字段是否为空
        if (!MyStringUtil.isNull(houseInfoPageDTO.getOrderBy()))
            houseInfoPage.setOrderByField(houseInfoPageDTO.getOrderBy());
        if(houseInfoPageDTO.getAsc() != null)
            houseInfoPage.setAsc(houseInfoPageDTO.getAsc());
        //初始化查询条件
        EntityWrapper<HouseInfo> ew = new EntityWrapper<>();
        model = houseInfoPageDTO.getModel();
        if (model == null)
            //如果查询条件不为空，将其设置进Wrapper
            model = new HouseInfo();
        model.setDkHException(0);
        model.setDkPubStatus(2);
        ew.setEntity(model);
        subStations = model.getSubStations();
        if (subStations != null && subStations.length > 0 )
            ew.in("dk_sub_station",subStations);
        else if (!MyStringUtil.isNull(model.getSubway()))
            ew.in("dk_sub_station",dictGzSubStationService.selectSubStationList(model.getSubway()));
        if (!MyStringUtil.isNull(model.getPriceRange())){
            String priceRanges[] = model.getPriceRange().split(",");
            BigDecimal min = new BigDecimal(MyStringUtil.isNull(priceRanges[0])?"0":priceRanges[0]);
            BigDecimal max = new BigDecimal(MyStringUtil.isNull(priceRanges[0])?"999999":priceRanges[0]);
            if (min.compareTo(max) > 0)
                throw BusinessBaseException.fail("价格区间有误");
            ew.between("rental",min,max);
        }
        if(!MyStringUtil.isNull(model.getAddressKey())){
            ew.like("address",model.getAddressKey());
            ew.or();
            ew.like("title",model.getAddressKey());}
        houseInfoPage.setRecords(this.baseMapper.selectList(houseInfoPage,ew));
        return houseInfoPage;
    }

    public Page<HouseInfo> getMyPage(PageDTO<HouseInfo> houseInfoPageDTO,Integer uId){
        if (houseInfoPageDTO.getModel() != null)
            throw BusinessBaseException.fail("该模块暂不支持条件查询");
        Page<HouseInfo> houseInfoPage = new Page<>();
        if (houseInfoPageDTO.getCurrent() != null)
            houseInfoPage.setCurrent(houseInfoPageDTO.getCurrent());
        //判断排序页面大小是否为空
        if (houseInfoPageDTO.getSize() != null)
            houseInfoPage.setSize(houseInfoPageDTO.getSize());
        //判断排序字段是否为空
        if (MyStringUtil.isNull(houseInfoPageDTO.getOrderBy()))
            houseInfoPage.setOrderByField(houseInfoPageDTO.getOrderBy());
        EntityWrapper<HouseInfo> ew = new EntityWrapper<>();
        ew.eq("u_id",uId);
        ew.orderBy("create_time",false);
        houseInfoPage = selectPage(houseInfoPage,ew);
        return houseInfoPage;
    }

    public List<CleanHouseDTO> selectDTOList(){
        return this.baseMapper.selectDTOList();
    }

    public Long selectExCount(Integer uId){
        return this.baseMapper.selectExCount(uId);
    }

    public List<String> uploadHouseImg(HttpServletRequest request){
        return fileService.upload(request);
    }

    public boolean cleanHouseInfo(List<Integer> needCleanHouses){
        return this.baseMapper.cleanHouseInfo(needCleanHouses);
    }

    public static void main(String[] args) {

    }
}
