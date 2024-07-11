package com.link.blog.handler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.link.blog.constant.CommonConstant;
import com.link.blog.util.PageUtils;
import com.link.blog.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Optional;

/**
 * @author LiuWenqi
 * @Description 分页拦截器
 * @date 2024-07-09 21:10
 */
public class PageInterceptorHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String pageNo = request.getParameter(CommonConstant.CURRENT);
        String pageSize = Optional.ofNullable(request.getParameter(CommonConstant.SIZE)).orElse(CommonConstant.DEFAULT_SIZE);
        if (StringUtils.isNotEmpty(pageNo)) {
            PageUtils.setCurrentPage(new Page<>(Long.parseLong(pageNo), Long.parseLong(pageSize)));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        PageUtils.remove();
    }
}
