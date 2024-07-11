package com.link.blog.service;

import java.util.Map;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-06-23 13:58
 */
public interface RedisService {

    /**
     * 保存属性
     * @param key
     * @param value
     */
    void set(String key, Object value);

    /**
     * 保存属性
     * @param key
     * @param value
     * @param expireTime 过期时间
     */
    void set(String key, Object value, long expireTime);

    /**
     * 获取属性
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 递增
     * @param key
     * @param delta 递增大小
     * @return
     */
    Long incr(String key, long delta);

    /**
     * 删除
     * @param key
     * @return
     */
    Boolean del(String key);

    /**
     * zset根据分数从大到小排列
     * @param key
     * @param start 起始索引
     * @param end 结尾索引
     * @return
     */
    Map<Object, Double> zReverseRangeWithScores(String key, long start, long end);

    /**
     * zset 获取key的所有分数
     * @param key
     * @return
     */
    Map<Object, Double> zAllScore(String key);

    /**
     * Set 是否包含属性
     * @param key
     * @param value
     * @return
     */
    Boolean sIsMember(String key, Object value);

    /**
     * Set 添加
     * @param key
     * @param values
     * @return
     */
    Long sAdd(String key, Object... values);

    /**
     * Set 获取Set结构大小
     * @param key
     * @return
     */
    Long sSize(String key);

    /**
     * Hash 递增
     * @param key
     * @param hashKey
     * @param delta 递增大小
     * @return 返回递增后的数据
     */
    Long hIncr(String key, String hashKey, long delta);

    /**
     * Hash 获取key的所有结构
     * @param key
     * @return
     */
    Map<String, Object> hGetAll(String key);



}
