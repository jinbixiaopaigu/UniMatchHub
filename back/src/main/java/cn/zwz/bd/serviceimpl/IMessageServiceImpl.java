package cn.zwz.bd.serviceimpl;

import cn.zwz.bd.mapper.MessageMapper;
import cn.zwz.bd.entity.Message;
import cn.zwz.bd.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 留言 服务层接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class IMessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

    @Autowired
    private MessageMapper messageMapper;
}