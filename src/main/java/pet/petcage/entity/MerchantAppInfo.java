package pet.petcage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by user chenzuoli on 2020/5/16 18:45
 * description: 商家版微信小程序信息
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "merchant_app_info", schema = "petcage")
public class MerchantAppInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String version;
    private String create_time;
    private String update_time;
}
