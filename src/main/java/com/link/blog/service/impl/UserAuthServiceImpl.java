package com.link.blog.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.link.blog.constant.RedisConstant;
import com.link.blog.entity.UserAuth;
import com.link.blog.dao.UserAuthDao;
import com.link.blog.enums.UserTypeEnum;
import com.link.blog.model.request.ConditionRequest;
import com.link.blog.model.vo.UserAreaVO;
import com.link.blog.service.RedisService;
import com.link.blog.service.UserAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Link
 * @since 2024-06-30 08:47:36
 */
@Service
@Slf4j
public class UserAuthServiceImpl extends ServiceImpl<UserAuthDao, UserAuth> implements UserAuthService {

    @Autowired
    private RedisService redisService;

    @Override
    public List<UserAreaVO> getUserArea(ConditionRequest request) {
        List<UserAreaVO> userAreaVOList = new ArrayList<>();
        switch (Objects.requireNonNull(UserTypeEnum.getUserType(request.getType()))) {
            case USER:
                Object userArea = redisService.get(RedisConstant.USER_AREA);
                if (Objects.nonNull(userArea)) {
                    userAreaVOList = JSON.parseObject(userArea.toString(), new TypeReference<List<UserAreaVO>>() {});
                }
                return userAreaVOList;
            case VISITOR:
                Map<String, Object> visitorArea = redisService.hGetAll(RedisConstant.VISITOR_AREA);
                if (Objects.nonNull(visitorArea)) {
                    userAreaVOList = visitorArea.entrySet().stream()
                            .map(item -> UserAreaVO.builder()
                                    .name(item.getKey())
                                    .value(Long.valueOf(item.getValue().toString()))
                                    .build())
                            .collect(Collectors.toList());
                }
                return userAreaVOList;
            default:
                break;
        }
        return userAreaVOList;
    }
}
