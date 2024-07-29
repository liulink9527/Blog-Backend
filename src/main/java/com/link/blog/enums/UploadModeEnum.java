package com.link.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author LiuWenqi
 * @Description
 * @Date 2024/7/29 星期一 下午2:27
 */
@Getter
@AllArgsConstructor
public enum UploadModeEnum {

    LOCAL("local", "localUploadStrategyImpl"),

    MINIO("minio", "minioUploadStrategyImpl")

    ;

    /**
     * 模式
     */
    private String mode;

    /**
     * 策略
     */
    private String stragtegy;


    public static String getUploadModeEnum(String mode) {
        for (UploadModeEnum value : UploadModeEnum.values()) {
            if (value.getMode().equals(mode)) {
                return value.getStragtegy();
            }
        }
        return null;
    }


}
