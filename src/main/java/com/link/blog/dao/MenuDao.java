package com.link.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.link.blog.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-06-15 17:02
 */
@Repository
public interface MenuDao extends BaseMapper<Menu> {

    List<Menu> listMenuByUserInfoId(Integer userInfoId);
}
