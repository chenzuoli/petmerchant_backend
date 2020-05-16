package pet.petcage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by user chenzuoli on 2020/3/27 18:10
 * description: Token数据传输对象类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TokenDTO {
    private String token;
}
