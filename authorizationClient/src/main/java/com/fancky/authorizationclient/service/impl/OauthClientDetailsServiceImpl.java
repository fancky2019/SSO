package com.fancky.authorizationclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fancky.authorizationclient.entity.OauthClientDetails;
import com.fancky.authorizationclient.mapper.OauthClientDetailsMapper;
import com.fancky.authorizationclient.service.IOauthClientDetailsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-07-28
 */
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements IOauthClientDetailsService {

    public  void test()
    {
    }
}
