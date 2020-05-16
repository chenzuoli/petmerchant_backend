package pet.petcage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by user chenzuoli on 2020/5/16 16:24
 * description: 商家表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "merchant", schema = "petcage")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id; // 自增主键
    private String phone; // 手机号
    private String open_id; // 用户小程序open_id
    private String pwd; // 用户账号密码
    private String token; // 登录access_token
    private String create_time; // 创建时间
    private String update_time; // 更新时间
}
