package generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName oauth_client_details
 */
@TableName(value ="oauth_client_details")
public class OauthClientDetails implements Serializable {
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 客户端ID
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * 客户端ID
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * 资源ID集合,多个资源时用逗号(,)分隔
     */
    public String getResourceIds() {
        return resourceIds;
    }

    /**
     * 资源ID集合,多个资源时用逗号(,)分隔
     */
    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    /**
     * 客户端密匙
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * 客户端密匙
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * 客户端申请的权限范围
     */
    public String getScope() {
        return scope;
    }

    /**
     * 客户端申请的权限范围
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * 客户端支持的grant_type
     */
    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    /**
     * 客户端支持的grant_type
     */
    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    /**
     * 重定向URI
     */
    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    /**
     * 重定向URI
     */
    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    /**
     * 客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔
     */
    public String getAuthorities() {
        return authorities;
    }

    /**
     * 客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔
     */
    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    /**
     * 访问令牌有效时间值(单位:秒)
     */
    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    /**
     * 访问令牌有效时间值(单位:秒)
     */
    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    /**
     * 更新令牌有效时间值(单位:秒)
     */
    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    /**
     * 更新令牌有效时间值(单位:秒)
     */
    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    /**
     * 预留字段
     */
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * 预留字段
     */
    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    /**
     * 用户是否自动Approval操作
     */
    public String getAutoapprove() {
        return autoapprove;
    }

    /**
     * 用户是否自动Approval操作
     */
    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OauthClientDetails other = (OauthClientDetails) that;
        return (this.getClientId() == null ? other.getClientId() == null : this.getClientId().equals(other.getClientId()))
            && (this.getResourceIds() == null ? other.getResourceIds() == null : this.getResourceIds().equals(other.getResourceIds()))
            && (this.getClientSecret() == null ? other.getClientSecret() == null : this.getClientSecret().equals(other.getClientSecret()))
            && (this.getScope() == null ? other.getScope() == null : this.getScope().equals(other.getScope()))
            && (this.getAuthorizedGrantTypes() == null ? other.getAuthorizedGrantTypes() == null : this.getAuthorizedGrantTypes().equals(other.getAuthorizedGrantTypes()))
            && (this.getWebServerRedirectUri() == null ? other.getWebServerRedirectUri() == null : this.getWebServerRedirectUri().equals(other.getWebServerRedirectUri()))
            && (this.getAuthorities() == null ? other.getAuthorities() == null : this.getAuthorities().equals(other.getAuthorities()))
            && (this.getAccessTokenValidity() == null ? other.getAccessTokenValidity() == null : this.getAccessTokenValidity().equals(other.getAccessTokenValidity()))
            && (this.getRefreshTokenValidity() == null ? other.getRefreshTokenValidity() == null : this.getRefreshTokenValidity().equals(other.getRefreshTokenValidity()))
            && (this.getAdditionalInformation() == null ? other.getAdditionalInformation() == null : this.getAdditionalInformation().equals(other.getAdditionalInformation()))
            && (this.getAutoapprove() == null ? other.getAutoapprove() == null : this.getAutoapprove().equals(other.getAutoapprove()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getClientId() == null) ? 0 : getClientId().hashCode());
        result = prime * result + ((getResourceIds() == null) ? 0 : getResourceIds().hashCode());
        result = prime * result + ((getClientSecret() == null) ? 0 : getClientSecret().hashCode());
        result = prime * result + ((getScope() == null) ? 0 : getScope().hashCode());
        result = prime * result + ((getAuthorizedGrantTypes() == null) ? 0 : getAuthorizedGrantTypes().hashCode());
        result = prime * result + ((getWebServerRedirectUri() == null) ? 0 : getWebServerRedirectUri().hashCode());
        result = prime * result + ((getAuthorities() == null) ? 0 : getAuthorities().hashCode());
        result = prime * result + ((getAccessTokenValidity() == null) ? 0 : getAccessTokenValidity().hashCode());
        result = prime * result + ((getRefreshTokenValidity() == null) ? 0 : getRefreshTokenValidity().hashCode());
        result = prime * result + ((getAdditionalInformation() == null) ? 0 : getAdditionalInformation().hashCode());
        result = prime * result + ((getAutoapprove() == null) ? 0 : getAutoapprove().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", clientId=").append(clientId);
        sb.append(", resourceIds=").append(resourceIds);
        sb.append(", clientSecret=").append(clientSecret);
        sb.append(", scope=").append(scope);
        sb.append(", authorizedGrantTypes=").append(authorizedGrantTypes);
        sb.append(", webServerRedirectUri=").append(webServerRedirectUri);
        sb.append(", authorities=").append(authorities);
        sb.append(", accessTokenValidity=").append(accessTokenValidity);
        sb.append(", refreshTokenValidity=").append(refreshTokenValidity);
        sb.append(", additionalInformation=").append(additionalInformation);
        sb.append(", autoapprove=").append(autoapprove);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}