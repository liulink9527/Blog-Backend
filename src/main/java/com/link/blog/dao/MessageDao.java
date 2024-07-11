package com.link.blog.dao;

import com.link.blog.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Link
 * @since 2024-06-24 08:10:48
 */
@Mapper
public interface MessageDao extends BaseMapper<Message> {

}
