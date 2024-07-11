package com.link.blog.schedule;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.link.blog.constant.CommonConstant;
import com.link.blog.constant.RedisConstant;
import com.link.blog.dao.UniqueViewDao;
import com.link.blog.dao.UserAuthDao;
import com.link.blog.entity.UniqueView;
import com.link.blog.entity.UserAuth;
import com.link.blog.model.vo.UserAreaVO;
import com.link.blog.service.RedisService;
import com.link.blog.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-06-30 22:27
 */
@Component
public class ScheduleTask {

    @Autowired
    private UserAuthDao userAuthDao;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UniqueViewDao uniqueViewDao;

    /**
     * 定时任务 统计用户分布
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void statisticsUserArea() {
        // 统计用户地域分布
        Map<String, Long> userAreaMap = userAuthDao.selectList(new LambdaQueryWrapper<UserAuth>().select(UserAuth::getIpSource)).stream()
                .map(item -> {
                    if (StringUtils.isNotBlank(item.getIpSource())) {
                        return item.getIpSource().substring(0, 2)
                                .replaceAll(CommonConstant.PROVICE, "")
                                .replaceAll(CommonConstant.CITY, "");
                    }
                    return CommonConstant.UNKNOWN;
                })
                .collect(Collectors.groupingBy(item -> item, Collectors.counting()));

        // 转换格式
        List<UserAreaVO> userAreaVOList = userAreaMap.entrySet().stream()
                .map(item -> UserAreaVO.builder()
                        .name(item.getKey())
                        .value(item.getValue())
                        .build())
                .collect(Collectors.toList());
        redisService.set(RedisConstant.USER_AREA, JSON.toJSONString(userAreaVOList));
    }

    /**
     * 定时任务 保存用户访问量
     */
    @Scheduled(cron = "0 0 0 * * ?", zone = "Asia/Shanghai")
    public void saveUniqueView() {
        // 获取每日访问量
        Long count = redisService.sSize(RedisConstant.UNIQUE_VISITOR);
        UniqueView uniqueView = UniqueView.builder()
                .viewsCount(Optional.of(count.intValue()).orElse(0))
                .createTime(LocalDateTimeUtil.offset(LocalDateTime.now(ZoneId.of("Asia/Shanghai")), -1, ChronoUnit.DAYS))
                .build();
        uniqueViewDao.insert(uniqueView);
    }

    /**
     * 定时任务 清空访客记录
     */
    @Scheduled(cron = "0 1 0 * * ?", zone = "Asia/Shanghai")
    public void clear() {
        // 删除访客记录
        redisService.del(RedisConstant.UNIQUE_VISITOR);
        // 删除统计访客区域
        redisService.del(RedisConstant.VISITOR_AREA);
    }
}
