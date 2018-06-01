package com.huzijun.yizunetwork.core.house.service;

import com.huzijun.yizunetwork.common.BaseService;
import com.huzijun.yizunetwork.common.BusinessBaseException;
import com.huzijun.yizunetwork.core.house.entity.UserComment;
import com.huzijun.yizunetwork.core.house.mapper.UserCommentMapper;
import com.huzijun.yizunetwork.utils.MyStringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户评论记录表 服务实现类
 * </p>
 *
 * @author hzj
 * @since 2018-05-31
 */
@Service
@Transactional
public class UserCommentService extends BaseService<UserCommentMapper, UserComment>{

    public boolean commentHouse(Integer uId,UserComment userComment){
        if(MyStringUtil.isNull(userComment.getMyComment()))
            throw BusinessBaseException.fail("评论不能为空");
        if(MyStringUtil.isSize(userComment.getMyComment(),0,101))
            throw BusinessBaseException.fail("评论字数过少或者超出");
        userComment.setuId(uId);
        return insert(userComment);
    }

    public boolean delMyComment(Integer uId,Integer commentId){
        UserComment userComment = commonExistsCheck(commentId);
        if (!userComment.getuId().equals(uId))
            throw BusinessBaseException.fail("不能删除别人的评论");
        return deleteById(commentId);
    }

}
