package pet.petcage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by user chenzuoli on 2020/3/31 17:23
 * description: session DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SessionDTO {
    private String session_key;
    private String open_id;
}
