package com.link.blog.service.impl;

import com.link.blog.entity.Message;
import com.link.blog.dao.MessageDao;
import com.link.blog.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Link
 * @since 2024-06-24 08:10:48
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageDao, Message> implements MessageService {

}
