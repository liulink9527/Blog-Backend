package com.link.blog.dao;

import com.link.blog.model.dto.UniqueViewDTO;
import com.link.blog.entity.UniqueView;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Link
 * @since 2024-06-24 08:21:47
 */
@Mapper
public interface UniqueViewDao extends BaseMapper<UniqueView> {

    /**
     * 查询一周访问量
     * @param startTime 起始时间
     * @param endTime 结束时间
     * @return
     */
    List<UniqueViewDTO> listOneWeekViews(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
