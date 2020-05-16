package pet.petcage.error;

/**
 * Created by user chenzuoli on 2020/3/27 18:06
 * description: 错误码枚举类
 */
public enum CommonErrorCode implements IErrorCode {

    NETWORK_ERROR(1001, "网络错误，请重试"),
    INVALID_PARAMS(1002, "参数传递有误"),
    OBTAIN_OPENID_ERROR(1003, "登录失败，请重新尝试"),
    UNKOWN_ERROR(1004, "未知错误，请重试"),
    NO_USER(1005, "登录异常，请重新登录"),
    SIGNATURE_ERROR(1006, "验证失败，传递信息有误"),
    CODE_INVALID(1007, "验证码错误"),
    CODE_EXPIRED(1008, "验证码失效");

    private Integer status;
    private String message;

    CommonErrorCode(Integer code, String message) {
        this.status = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
