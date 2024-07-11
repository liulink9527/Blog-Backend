package com.link.blog.dao;

import com.link.blog.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author LiuWenqi
 * @Description
 * @since 2024-06-23 03:32:02
 */
@Repository
public interface RoleDao extends BaseMapper<Role> {

}
