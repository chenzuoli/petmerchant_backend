package pet.petcage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by user chenzuoli on 2020/5/22 15:53
 * description: 用户宠物表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user_pet", schema = "petcage")
public class UserPet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String open_id; // 用户open_id
    private String contact; // 宠物联系人
    private String pet_type; // 宠物类型
    private String variety; // 宠物品种
    private String nick_name; // 宠物昵称
    private String gender; // 宠物性别
    private String birthday; // 宠物出生日期
    private String avatar_url; // 宠物头像
    private String description; // 描述
    private String create_time;
    private String update_time;
}
