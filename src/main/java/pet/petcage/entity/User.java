package pet.petcage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by user chenzuoli on 2020/3/22 16:24
 * description: 用户表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user", schema = "petcage")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id; // 自增主键
    private String phone; // 手机号
    private String open_id; // 用户小程序open_id
    private String union_id; // 用户小程序union_id
    private String pwd; // 用户账号密码
    private String user_type; // 用户类型(1普通会员,2周卡会员,3月卡会员,4季卡会员,5年卡会员)
    private String balance; // 余额
    private String valid_start_date; // 会员有效开始日期
    private String valid_end_date; // 会员有效结束日期
    private String token; // 登录access_token
    private String country; // 国别
    private String province; // 省份
    private String city; // 城市
    private String avatar_url; // 头像url
    private String gender; // 性别
    private String nick_name; // 昵称
    private String language; // 语言
    private String birthday; // 出生日期
    private String create_time; // 创建时间
    private String update_time; // 更新时间
}
