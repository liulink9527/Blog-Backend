package com.link.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.link.blog.dao.UserInfoDao;
import com.link.blog.entity.UserInfo;
import com.link.blog.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-06-23 14:51
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements UserInfoService {
}
