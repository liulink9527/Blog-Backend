package com.link.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author LiuWenqi
 * @Description 网站配置DTO
 * @date 2024-07-14 12:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebsiteConfigDTO {

    /**
     * 网站头像
     */
    private String websiteAvatar;

    /**
     * 网站名称
     */
    private String websiteName;

    /**
     * 网站作者
     */
    private String websiteAuthor;

    /**
     * 网站介绍
     */
    private String websiteIntro;

    /**
     * 网站公告
     */
    private String websiteNotice;

    /**
     * 网站创建时间
     */
    private LocalDateTime websiteCreateTime;

    /**
     * 网站备案号
     */
    private String websiteRecordNo;

    /**
     * 网站地址
     */
    private String websiteAddress;

    /**
     * 社交登录列表
     */
    private List<String> socialLoginList;

    /**
     * 社交url列表
     */
    private List<String> socialUrlList;

    /**
     * qq
     */
    private String qq;

    /**
     * github
     */
    private String github;

    /**
     * gitee
     */
    private String gitee;

    /**
     * 游客头像
     */
    private String vistorAvatar;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 是否评论审核
     */
    private Integer isCommentReview;

    /**
     * 是否留言审核
     */
    private Integer isMessageReview;

    /**
     * 是否邮箱通知
     */
    private Integer isEmailNotice;

    /**
     * 是否打赏
     */
    private Integer isReward;

    /**
     * 微信二维码
     */
    private String weixinQRCode;

    /**
     * 支付宝二维码
     */
    private String alipayQRCode;

    /**
     * 文章封面
     */
    private String articleCover;

    /**
     * 是否开启聊天室
     */
    private Integer isChatRoom;

    /**
     * websocket地址
     */
    private String websocketUrl;

    /**
     * 是否开启音乐
     */
    private Integer isMusicPlayer;

}
