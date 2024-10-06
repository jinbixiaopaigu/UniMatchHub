package cn.zwz.bd.serviceimpl;

import cn.zwz.bd.mapper.MatchmakingConferenceMapper;
import cn.zwz.bd.entity.MatchmakingConference;
import cn.zwz.bd.service.IMatchmakingConferenceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 相亲大会 服务层接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class IMatchmakingConferenceServiceImpl extends ServiceImpl<MatchmakingConferenceMapper, MatchmakingConference> implements IMatchmakingConferenceService {

    @Autowired
    private MatchmakingConferenceMapper matchmakingConferenceMapper;
}