package com.link.blog.manager;

import cn.hutool.core.date.DateUtil;
import com.link.blog.dao.UniqueViewDao;
import com.link.blog.model.dto.UniqueViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-06-24 21:11
 */
@Component
public class UniqueViewManager {

    @Autowired
    private UniqueViewDao uniqueViewDao;

    /**
     * 获取一周浏览量
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public List<UniqueViewDTO> listOneWeekViews() {
        Date startTime = DateUtil.beginOfDay(DateUtil.offsetDay(new Date(), -7));
        Date endTime = DateUtil.endOfDay(new Date());
        return uniqueViewDao.listOneWeekViews(startTime, endTime);
    }

}
