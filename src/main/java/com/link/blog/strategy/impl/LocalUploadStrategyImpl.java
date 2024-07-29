package com.link.blog.strategy.impl;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author LiuWenqi
 * @Description
 * @Date 2024/7/29 星期一 下午2:40
 */
@Service("localUploadStrategyImpl")
public class LocalUploadStrategyImpl extends AbstractUploadStrategyImpl{
    @Override
    public Boolean exists(String filePath) {
        return null;
    }

    @Override
    public void upload(String path, String fileName, InputStream inputStream) throws IOException {

    }

    @Override
    public String getFileAccessUrl(String filePath) {
        return "";
    }
}
