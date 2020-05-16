package pet.petcage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * Created by user chenzuoli on 2020/3/27 14:59
 * description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDTO {
    // 用户信息原始数据
    private String rawData;
    // 用户获取 session_key 的 code
    private String js_code;
}
