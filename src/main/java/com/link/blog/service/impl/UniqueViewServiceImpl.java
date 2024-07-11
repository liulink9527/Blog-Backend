package com.link.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.link.blog.model.dto.UniqueViewDTO;
import com.link.blog.entity.UniqueView;
import com.link.blog.dao.UniqueViewDao;
import com.link.blog.service.UniqueViewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Link
 * @since 2024-06-24 08:21:47
 */
@Service
public class UniqueViewServiceImpl extends ServiceImpl<UniqueViewDao, UniqueView> implements UniqueViewService {

}
