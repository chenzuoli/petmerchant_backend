package pet.petcage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by user chenzuoli on 2020/5/18 08:15
 * description: 反馈表
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "feedback", schema = "petcage")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String phone; // 用户手机号
    private String open_id; // 用户open_id
    private String feedback_type; // 反馈类型
    private String feedback_content; // 反馈内容
    private String satisfy_grade; // 客户满意度
    private String pictures; // 反馈图片地址
    private String latitude; // 反馈用户径度
    private String longitude; // 反馈用户纬度
    private String petcage_id; // 反馈设备id
    private String description; // 反馈描述
    private String create_time; //
    private String update_time; //
}
