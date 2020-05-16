package pet.petcage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by user chenzuoli on 2020/3/31 18:11
 * description: 获取的带参数小程序二维码
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QRCodeDTO {
    private String code_path;
}
