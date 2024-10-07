package cn.zwz.bd.serviceimpl;

import cn.zwz.bd.mapper.MemberMapper;
import cn.zwz.bd.entity.Member;
import cn.zwz.bd.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会员 服务层接口实现

 */
@Slf4j
@Service
@Transactional
public class IMemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Autowired
    private MemberMapper memberMapper;
}