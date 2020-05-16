package pet.petcage.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties
@PropertySource("classpath:param.properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Constant {
    private String wx_app_id; // wx appid
    private String wx_app_secret; // wx secret
    private String access_url; // 微信获取session key，openid的url
    private String access_token_url; // 微信获取accessToken的url
    private String sms_url; // 榛子短信url
    private String sms_app_id; // 榛子短信appid
    private String sms_app_secret; // 榛子短信secret
    private String qiniu_access_key; // 七牛云ak
    private String qiniu_secret_key; // 七牛云sk
    private String qiniu_bucket_name; // 七牛云bucket名称
    private String qiniu_domain_of_bucket; // 七牛云域名映射
    private String avatar_path; // 头像本地存放路径
}
