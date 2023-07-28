package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.entity.OauthClientDetails;
import generator.service.OauthClientDetailsService;
import generator.mapper.OauthClientDetailsMapper;
import org.springframework.stereotype.Service;

/**
* @author ruili
* @description 针对表【oauth_client_details】的数据库操作Service实现
* @createDate 2023-07-28 09:32:55
*/
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails>
    implements OauthClientDetailsService{

}




