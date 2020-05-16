package pet.petcage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pet.petcage.error.IErrorCode;

/**
 * Created by user chenzuoli on 2020/3/27 18:02
 * description: 返回给前端的数据传输结果对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResultDTO {
    private Integer status;
    private Object data;
    private String message;

    public static ResultDTO ok(Object data) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(200);
        resultDTO.setData(data);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static ResultDTO fail(String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(400);
        resultDTO.setData("");
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO fail(IErrorCode errorCode) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(errorCode.getCode());
        resultDTO.setMessage(errorCode.getMessage());
        return resultDTO;
    }

}