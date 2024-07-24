package com.link.blog.util;

import java.util.Random;

/**
 * @author LiuWenqi
 * @Description 通用工具类
 * @date 2024-07-16 22:16
 */
public class CommonUtils {

    /**
     * 生成6位随机验证码
     * @return
     */
    public static String getRandomCode() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
