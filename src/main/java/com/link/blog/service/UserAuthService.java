package com.link.blog.service;

import com.link.blog.entity.UserAuth;
import com.baomidou.mybatisplus.extension.service.IService;
import com.link.blog.model.request.ConditionRequest;
import com.link.blog.model.vo.UserAreaVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Link
 * @since 2024-06-30 08:47:36
 */
public interface UserAuthService extends IService<UserAuth> {

    /**
     * 获取用户区域分布
     * @param request
     * @return
     */
    List<UserAreaVO> getUserArea(ConditionRequest request);
}
