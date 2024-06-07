package com.fancky.authorization.service;

import com.fancky.authorization.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
登录认证逻辑
 */
@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private List<UserInfo> userList;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData() {
        String password = passwordEncoder.encode("123");
        //替代 sys_user 表中的数据
        userList = new ArrayList<>();
//        //模仿从获取库中获取用户信息和用户的权限信息
//        userList.add(new UserInfo("admin", password, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN")));
//        userList.add(new UserInfo("user", password, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER")));
        //模仿从获取库中获取用户信息和用户的权限信息
        //权限采用rabc动态判断+白名单动态判断，不适用注解方式。
        userList.add(new UserInfo("admin", password, null));
        userList.add(new UserInfo("user", password, null));

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       /*
        UserInfo 实现 UserDetails 接口，提供用户名密码
        用户信息从数据库获取，校验密码信息。
        校验之后获取权限信息
        */
        List<UserInfo> findUserList = userList.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(findUserList)) {
            return findUserList.get(0);
        } else {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
    }
}