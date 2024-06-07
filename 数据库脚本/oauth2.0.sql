--  ------------------------github  ---------------------------
-- https://github.com/spring-attic/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql       
    
-- -- used in tests that use HSQL
-- create table oauth_client_details (
--   client_id VARCHAR(256) PRIMARY KEY,
--   resource_ids VARCHAR(256),
--   client_secret VARCHAR(256),
--   scope VARCHAR(256),
--   authorized_grant_types VARCHAR(256),
--   web_server_redirect_uri VARCHAR(256),
--   authorities VARCHAR(256),
--   access_token_validity INTEGER,
--   refresh_token_validity INTEGER,
--   additional_information VARCHAR(4096),
--   autoapprove VARCHAR(256)
-- );
-- 
-- create table oauth_client_token (
--   token_id VARCHAR(256),
--   token LONGVARBINARY,
--   authentication_id VARCHAR(256) PRIMARY KEY,
--   user_name VARCHAR(256),
--   client_id VARCHAR(256)
-- );
-- 
-- create table oauth_access_token (
--   token_id VARCHAR(256),
--   token LONGVARBINARY,
--   authentication_id VARCHAR(256) PRIMARY KEY,
--   user_name VARCHAR(256),
--   client_id VARCHAR(256),
--   authentication LONGVARBINARY,
--   refresh_token VARCHAR(256)
-- );
-- 
-- create table oauth_refresh_token (
--   token_id VARCHAR(256),
--   token LONGVARBINARY,
--   authentication LONGVARBINARY
-- );
-- 
-- create table oauth_code (
--   code VARCHAR(256), authentication LONGVARBINARY
-- );
-- 
-- create table oauth_approvals (
-- 	userId VARCHAR(256),
-- 	clientId VARCHAR(256),
-- 	scope VARCHAR(256),
-- 	status VARCHAR(10),
-- 	expiresAt TIMESTAMP,
-- 	lastModifiedAt TIMESTAMP
-- );
-- 
-- 
-- -- customized oauth_client_details table
-- create table ClientDetails (
--   appId VARCHAR(256) PRIMARY KEY,
--   resourceIds VARCHAR(256),
--   appSecret VARCHAR(256),
--   scope VARCHAR(256),
--   grantTypes VARCHAR(256),
--   redirectUrl VARCHAR(256),
--   authorities VARCHAR(256),
--   access_token_validity INTEGER,
--   refresh_token_validity INTEGER,
--   additionalInformation VARCHAR(4096),
--   autoApproveScopes VARCHAR(256)
-- );


-- --------------------------------------------







-- Oauth2相关的5张表：

-- oauth_access_token：访问令牌
-- oauth_refresh_token：更新令牌
-- oauth_client_details：客户端信息
-- oauth_code：授权码
-- oauth_approvals：授权记录
-- oauth_client_token:  客户端用来记录token信息

-- SHOW CREATE TABLE test.`oauth_client_details`;
-- 
-- SELECT  *  FROM test.`oauth_client_details`;


DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` VARCHAR(255) NOT NULL COMMENT '客户端ID',
  `resource_ids` VARCHAR(255) DEFAULT NULL COMMENT '资源ID集合,多个资源时用逗号(,)分隔',
  `client_secret` VARCHAR(255) DEFAULT NULL COMMENT '客户端密匙',
  `scope` VARCHAR(255) DEFAULT NULL COMMENT '客户端申请的权限范围',
  `authorized_grant_types` VARCHAR(255) DEFAULT NULL COMMENT '客户端支持的grant_type',
  `web_server_redirect_uri` VARCHAR(255) DEFAULT NULL COMMENT '重定向URI',
  `authorities` VARCHAR(255) DEFAULT NULL COMMENT '客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔',
  `access_token_validity` INT DEFAULT NULL COMMENT '访问令牌有效时间值(单位:秒)',
  `refresh_token_validity` INT DEFAULT NULL COMMENT '更新令牌有效时间值(单位:秒)',
  `additional_information` VARCHAR(255) DEFAULT NULL COMMENT '预留字段',
  `autoapprove` VARCHAR(255) DEFAULT NULL COMMENT '用户是否自动Approval操作'
) ENGINE=INNODB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
`token_id` VARCHAR(255) DEFAULT NULL COMMENT '加密的access_token的值',
`token` LONGBLOB COMMENT 'OAuth2AccessToken.java对象序列化后的二进制数据',
`authentication_id` VARCHAR(255) DEFAULT NULL COMMENT '加密过的username,client_id,scope',
`user_name` VARCHAR(255) DEFAULT NULL COMMENT '登录的用户名',
`client_id` VARCHAR(255) DEFAULT NULL COMMENT '客户端ID',
`authentication` LONGBLOB COMMENT 'OAuth2Authentication.java对象序列化后的二进制数据',
`refresh_token` VARCHAR(255) DEFAULT NULL COMMENT '加密的refresh_token的值'
) ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
`userId` VARCHAR(255) DEFAULT NULL COMMENT '登录的用户名',
`clientId` VARCHAR(255) DEFAULT NULL COMMENT '客户端ID',
`scope` VARCHAR(255) DEFAULT NULL COMMENT '申请的权限范围',
`status` VARCHAR(10) DEFAULT NULL COMMENT '状态（Approve或Deny）',
`expiresAt` DATETIME DEFAULT NULL COMMENT '过期时间',
`lastModifiedAt` DATETIME DEFAULT NULL COMMENT '最终修改时间'
) ENGINE=INNODB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
`token_id` VARCHAR(255) DEFAULT NULL COMMENT '加密的access_token值',
`token` LONGBLOB COMMENT 'OAuth2AccessToken.java对象序列化后的二进制数据',
`authentication_id` VARCHAR(255) DEFAULT NULL COMMENT '加密过的username,client_id,scope',
`user_name` VARCHAR(255) DEFAULT NULL COMMENT '登录的用户名',
`client_id` VARCHAR(255) DEFAULT NULL COMMENT '客户端ID'
) ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
`code` VARCHAR(255) DEFAULT NULL COMMENT '授权码(未加密)',
`authentication` VARBINARY(255) DEFAULT NULL COMMENT 'AuthorizationRequestHolder.java对象序列化后的二进制数据'
) ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
`token_id` VARCHAR(255) DEFAULT NULL COMMENT '加密过的refresh_token的值',
`token` LONGBLOB COMMENT 'OAuth2RefreshToken.java对象序列化后的二进制数据 ',
`authentication` LONGBLOB COMMENT 'OAuth2Authentication.java对象序列化后的二进制数据'
) ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
`username` VARCHAR(50) DEFAULT NULL COMMENT '用户名',
`password` VARCHAR(50) DEFAULT NULL COMMENT '密码',
PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户信息表';


-- INSERT INTO authority  VALUES(1,'ROLE_ADMIN');
-- INSERT INTO authority VALUES(2,'ROLE_ADMIN');
-- INSERT INTO authority VALUES(3,'ROLE_CLIENT');
-- INSERT INTO credentials VALUES(1,b'1','oauth_admin','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2','0');
-- INSERT INTO credentials VALUES(2,b'1','resource_admin','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2','0');
-- INSERT INTO credentials  VALUES(3,b'1','project_admin','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2','0');
-- INSERT INTO credentials_authorities VALUE (1,1);
-- INSERT INTO credentials_authorities VALUE (2,2);
-- INSERT INTO credentials_authorities VALUE (3,3);


-- 参考链接 https://blog.csdn.net/qq15035899256/article/details/129541483?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-1-129541483-blog-127025830.235^v38^pc_relevant_sort_base1&spm=1001.2101.3001.4242.2&utm_relevant_index=2

-- grant_type 来表明我们的授权方式  ①客户端模式（Client Credentials）
-- 获取授权码的回调地址 http://localhost:9001/loginSuccess
-- autoapprove  true 自动授权，不会弹出手动授权对话网页
-- "authorization_code","password","refresh_token"   ROLE_ADMIN  authorization_code,password,refresh_token   localhost

-- authorized_grant_types 值
-- authorization_code 授权码模式
-- password  密码模式
-- client_credentials 客户端模式
-- implicit
-- refresh_token 刷新token:token过期用refresh_token获取token，获取方式指定
 --              grant_type 指定为refresh_token,client_id、client_secret、
--               refresh_token 

-- 认证码使用一次就作废 重新获取http://localhost:9001/oauth/authorize?client_id=client_id1&response_type=code


-- 权限作用域配置
--  字符服务器配置 scope access("#oauth2.hasScope('book')"); 
 -- 认证端配置  .scopes("book", "user", "borrow") 多个应该用逗号分割
 
 -- 过期时间单位秒，token 过期报错，只要不过期，token和refresh_token就可用
 --  "error": "invalid_token", "error_description": "Access token expired:
 
 
TRUNCATE TABLE  oauth_client_details;
INSERT INTO oauth_client_details VALUES('client_id1','project_api', '$2a$10$Z9UdI243Oan3o9GNwXve4.xdFme64BeRnGC92TLVEKHyi7Lg70LxO', 'service1,sevice2', 'authorization_code,password,refresh_token,client_credentials,implicit', 'http://localhost:9001/loginSuccess', 'ROLE_ADMIN', 3600, 864000, NULL, 'true');


select  *  from sys_user

select  *  from oauth_client_details;

truncate  table oauth_access_token;
select  *  FROM oauth_access_token;


select  *  FROM oauth_refresh_token



select  *  from oauth_approvals

-- truncate DDL 不可回滚，直接清空表不记日志, delete  dml 可回滚，记录事务日志
-- truncate  TABLE 不能用于有外键的表
-- 禁用外键
-- - 启用or禁用指定表所有外键约束 

