package com.fancky.authorizationclient.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fancky.authorizationclient.entity.OauthClientDetails;
import com.fancky.authorizationclient.service.IOauthClientDetailsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色权限控制
 */
@Component
public class AuthService {

    @Value("${service1.client-id}")
    private String clientId;
    @Autowired
    private IOauthClientDetailsService clientDetailsService;

    public boolean canAccess(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            return false;
        }

        if (authentication instanceof AnonymousAuthenticationToken) {
            //check if this uri can be access by anonymous
            //return
        }

        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
            OAuth2Request auth2Request = oAuth2Authentication.getOAuth2Request();
            if (!auth2Request.getScope().contains(clientId)) {
                return false;
            }
            return true;

        } else {
            QueryWrapper<OauthClientDetails> queryWrapper = new QueryWrapper<OauthClientDetails>();
            queryWrapper.eq(StringUtils.isNotEmpty(clientId), "client_id", clientId);
            List<OauthClientDetails> list = this.clientDetailsService.list(queryWrapper);
            if (list.size() > 0) {
                list.get(0);
                //后续不再处理
            }
        }

        //判断RABC角色权限
//        Set<String> roles = authentication.getAuthorities()
//                .stream()
//                .map(e -> e.getAuthority())
//                .collect(Collectors.toSet());
        String uri = request.getRequestURI();
        List<String> rolePaths = new ArrayList<String>();
        //check this uri can be access by this role
        if (!rolePaths.contains(rolePaths)) {
            return false;
        }
        return true;
    }
}

