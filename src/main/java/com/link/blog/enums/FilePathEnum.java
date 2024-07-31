package com.link.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author LiuWenqi
 * @Description
 * @Date 2024/7/31 星期三 上午10:32
 */
@Getter
@AllArgsConstructor
public enum FilePathEnum {

    /**
     * 头像路径
     */
    AVATAR("avatar/", "头像路径"),
    /**
     * 文章图片路径
     */
    ARTICLE("articles/", "文章图片路径"),
    /**
     * 音频路径
     */
    VOICE("voice/", "音频路径"),
    /**
     * 照片路径
     */
    PHOTO("photos/", "相册路径"),
    /**
     * 配置图片路径
     */
    CONFIG("config/", "配置图片路径"),
    /**
     * 说说图片路径
     */
    TALK("talks/", "说说图片路径"),
    /**
     * md文件路径
     */
    MD("markdown/", "md文件路径");

    /**
     * 路径
     */
    private String path;

    /**
     * 描述
     */
    private String desc;


    /**
     * 获取文件路径
     * @param path
     * @return
     */
    public static FilePathEnum getFilePathEnum(String path) {
        for (FilePathEnum filePathEnum : FilePathEnum.values()) {
            if (filePathEnum.getPath().equals(path)) {
                return filePathEnum;
            }
        }
        return null;
    }
}
