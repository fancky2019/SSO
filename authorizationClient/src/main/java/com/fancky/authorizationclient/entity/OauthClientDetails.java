package com.fancky.authorizationclient.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2023-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oauth_client_details")
public class OauthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 资源ID集合,多个资源时用逗号(,)分隔
     */
    private String resourceIds;

    /**
     * 客户端密匙
     */
    private String clientSecret;

    /**
     * 客户端申请的权限范围
     */
    private String scope;

    /**
     * 客户端支持的grant_type
     */
    private String authorizedGrantTypes;

    /**
     * 重定向URI
     */
    private String webServerRedirectUri;

    /**
     * 客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔
     */
    private String authorities;

    /**
     * 访问令牌有效时间值(单位:秒)
     */
    private Integer accessTokenValidity;

    /**
     * 更新令牌有效时间值(单位:秒)
     */
    private Integer refreshTokenValidity;

    /**
     * 预留字段
     */
    private String additionalInformation;

    /**
     * 用户是否自动Approval操作
     */
    private String autoapprove;


}
