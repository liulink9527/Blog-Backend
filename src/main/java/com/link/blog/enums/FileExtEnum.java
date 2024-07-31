package com.link.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author LiuWenqi
 * @Description
 * @Date 2024/7/31 星期三 上午10:29
 */
@Getter
@AllArgsConstructor
public enum FileExtEnum {

    /**
     * jpg文件
     */
    JPG(".jpg", "jpg文件"),
    /**
     * png文件
     */
    PNG(".png", "png文件"),
    /**
     * Jpeg文件
     */
    JPEG(".jpeg", "jpeg文件"),
    /**
     * wav文件
     */
    WAV(".wav", "wav文件"),
    /**
     * md文件
     */
    MD(".md","markdown文件"),
    /**
     * txt文件
     */
    TXT(".txt","txt文件")

    ;

    /**
     * 后缀类型
     */
    private String extName;

    /**
     * 描述
     */
    private String desc;


    /**
     * 获取文件格式
     * @param extName
     * @return
     */
    public static FileExtEnum getFileExtEnum(String extName) {
        for (FileExtEnum fileExtEnum : FileExtEnum.values()) {
            if (fileExtEnum.getExtName().equals(extName)) {
                return fileExtEnum;
            }
        }
        return null;
    }
}
