package pet.petcage.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.petcage.common.Constant;
import pet.petcage.dto.ResultDTO;
import pet.petcage.entity.Merchant;
import pet.petcage.error.CommonErrorCode;
import pet.petcage.service.MerchantService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * 返回对象
 * //@RestController
 * <p>
 * 返回的内容是去根目录寻找相应的jsp文件
 * //@Controller
 */
@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    Constant constant;
    @Autowired
    MerchantService merchantService;
    @Autowired
    AppInfoController appInfoController;

    /**
     * 用户名、密码方式登录检验
     *
     * @param phone 用户手机号
     * @param pwd   用户密码
     * @return 用户名密码是否匹配
     */
    @RequestMapping(value = "/login")
    public ResultDTO login(@RequestParam("phone") String phone, @RequestParam("pwd") String pwd) {
        boolean is_match = merchantService.loginCheck(phone, pwd);
        ResultDTO resultDTO = appInfoController.accessToken();
        if (is_match && resultDTO.getStatus() == 200) {
            // 验证手机号和密码
            boolean flag = merchantService.loginCheck(phone, pwd);
            if (flag) {
                // 更新用户token
                updateUserToken(phone, resultDTO.getData().toString());
                return ResultDTO.ok(resultDTO.getData());
            } else {
                return ResultDTO.fail("登录失败");
            }
        } else {
            return ResultDTO.fail("登录失败");
        }
    }

    @RequestMapping(value = "/register")
    public ResultDTO register(@RequestParam("phone") String phone, @RequestParam("pwd") String pwd) {
        boolean is_exists = merchantService.loginCheck(phone, pwd);
        if (is_exists) {
            return ResultDTO.fail("该用户已存在");
        } else {
            int result = merchantService.addMerchant(phone, pwd);
            if (result > 0) {
                ResultDTO resultDTO = appInfoController.accessToken();
                if (resultDTO.getStatus() == 200) {
                    // 更新用户token
                    updateUserToken(phone, resultDTO.getData().toString());
                }
                return ResultDTO.ok(resultDTO.getData());
            } else {
                return ResultDTO.fail("注册失败");
            }
        }
    }

    private int updateUserToken(String phone, String token) {
        return merchantService.updateMerchantToken(phone, token);
    }

    /**
     * 发送短信
     *
     * @param phone       手机号
     * @param httpSession session
     * @return boolean 发送成功、失败
     */
    @RequestMapping("/sms_code")
    public ResultDTO smsCode(@RequestParam String phone, HttpSession httpSession) {
        try {
            ZhenziSmsClient client = new ZhenziSmsClient(
                    constant.getSms_url(),
                    constant.getSms_app_id(),
                    constant.getSms_app_secret());
            HashMap<String, String> params = new HashMap<>();
            String code = String.valueOf(100000 + new Random().nextInt(899999));
            params.put("message", "您的验证码为:" + code + "，该码有效期为5分钟，该码只能使用一次!");
            params.put("number", phone);
            String result = client.send(params);
            JSONObject json = JSONObject.parseObject(result);
            if (json.getIntValue("code") != 0) {//发送短信失败
                return ResultDTO.fail("发送验证码失败");
            }
            //将验证码存到session中,同时存入创建时间
            //以json存放，这里使用的是阿里的fastjson
            json = new JSONObject();
            json.put("phone", phone);
            json.put("code", code);
            json.put("createTime", System.currentTimeMillis());
            // 将认证码存入SESSION
            httpSession.setAttribute("code", json);
            return ResultDTO.ok(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultDTO.fail("发送验证码失败");
    }

    /**
     * 注册验证验证码
     *
     * @param phone       手机号
     * @param code        前端传回验证码
     * @param httpSession session
     * @return 验证结果
     */
    @RequestMapping(value = "/code_register", method = RequestMethod.POST)
    public ResultDTO smsCodeCheck(@RequestParam String phone,
                                  @RequestParam String code,
                                  HttpSession httpSession) {
        ResultDTO resultDTO = null;
//        JSONObject json = (JSONObject) httpSession.getAttribute("code");
//        if (System.currentTimeMillis() - 5 * 60 * 1000 > Long.parseLong(json.getString("createTime"))) {
//            resultDTO = ResultDTO.fail(CommonErrorCode.CODE_EXPIRED);
//        } else {
//            if (phone.equals(json.getString("phone")) && code.equals(json.getString("code"))) {
//                resultDTO = ResultDTO.ok("注册成功");
//            } else {
//                resultDTO = ResultDTO.fail(CommonErrorCode.CODE_INVALID);
//            }
//        }
        resultDTO = ResultDTO.ok("注册成功");
        return resultDTO;
    }

    /**
     * 更新用户密码
     *
     * @param nick_name   用户昵称
     * @param pwd         用户新密码
     * @param phone       用户手机号
     * @param code        更改密码手机验证码
     * @param httpSession session
     * @return 更新结果
     */
    @RequestMapping(value = "/update_pass", method = RequestMethod.POST)
    public ResultDTO updatePass(@RequestParam("nick_name") String nick_name,
                                @RequestParam("pwd") String pwd,
                                @RequestParam("phone") String phone,
                                @RequestParam("code") String code,
                                HttpSession httpSession) {
        JSONObject json = (JSONObject) httpSession.getAttribute("code");
        ResultDTO resultDTO = null;
        if (System.currentTimeMillis() - 5 * 60 * 1000 > Long.parseLong(json.getString("createTime"))) {
            resultDTO = ResultDTO.fail(CommonErrorCode.CODE_EXPIRED);
        } else {
            if (phone.equals(json.getString("phone")) && code.equals(json.getString("code"))) {
                int flag = merchantService.updatePass(nick_name, pwd, phone);
                if (flag > 0) {
                    resultDTO = ResultDTO.ok("更新密码成功");
                } else {
                    resultDTO = ResultDTO.fail("更新密码失败");
                }
            } else {
                resultDTO = ResultDTO.fail(CommonErrorCode.CODE_INVALID);
            }
        }
        return resultDTO;
    }

    @RequestMapping(value = "/get_user_by_open_id", method = RequestMethod.POST)
    public ResultDTO getUserByOpenid(@RequestParam("open_id") String open_id) {
        List<Merchant> merchants = merchantService.getMerchantByOpenid(open_id);
        if (merchants.size() == 0) {
            return ResultDTO.fail("未查询到user");
        } else {
            return ResultDTO.ok(merchants.get(0));
        }
    }

}
