package pet.petcage.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.petcage.common.Constant;
import pet.petcage.dto.ResultDTO;
import pet.petcage.dto.SessionDTO;
import pet.petcage.service.AppInfoService;
import pet.petcage.service.MerchantService;
import pet.petcage.util.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by user chenzuoli on 2020/4/5 18:57
 * description: 微信小程序信息控制器
 */
@RestController
@RequestMapping("/merchant")
public class AppInfoController {
    @Autowired
    Constant constant;
    @Autowired
    AppInfoService appInfoService;
    @Autowired
    MerchantService merchantService;

    /**
     * 小程序获取session_key openid
     *
     * @param js_code 小程序生成的code
     * @return session_key open_id
     */
    @RequestMapping(value = "/open_id")
    public ResultDTO openId(@RequestParam("js_code") String js_code) {
        HashMap<String, String> params = new HashMap<>();
        params.put("appid", constant.getWx_app_id());
        params.put("secret", constant.getWx_app_secret());
        params.put("js_code", js_code);
        params.put("grant_type", "authorization_code");
        String response = HttpUtil.sendPost(constant.getAccess_url(), params);
        System.out.println("response: " + response);
        SessionDTO sessionDTO = new SessionDTO();
        try {
            JSONObject jsonObject = JSONObject.parseObject(response);
            sessionDTO.setOpen_id(jsonObject.getString("openid"));
            sessionDTO.setSession_key(jsonObject.getString("session_key"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDTO.fail("获取失败");
        }
        return ResultDTO.ok(sessionDTO);
    }

    /**
     * 获取小程序access token, 保存用户token到mysql数据库
     *
     * @return 请求响应
     */
    @RequestMapping(value = "/access_token", method = RequestMethod.GET)
    public ResultDTO accessToken() {
        ResultDTO resultDTO = null;
        try {
            StringBuilder params = new StringBuilder();
            params.append("appid=").append(constant.getWx_app_id());
            params.append("&secret=").append(constant.getWx_app_secret());
            params.append("&grant_type=client_credential");
            String request_url = constant.getAccess_token_url() + "?" + params;
            System.out.println(request_url);
            String response = HttpUtil.get(request_url);
            System.out.println("get access token response: " + response);
            String token = JSONObject.parseObject(response).getString("access_token");
            resultDTO = ResultDTO.ok(token);
        } catch (Exception e) {
            e.printStackTrace();
            resultDTO = ResultDTO.fail("获取失败");
        }
        return resultDTO;
    }

    /**
     * 更新小程序版本号
     *
     * @param version 小程序版本号
     * @return ResultDTO
     */
    @RequestMapping(value = "/update_version", method = RequestMethod.POST)
    public ResultDTO updateVersion(@RequestParam("version") String version) {
        int i = appInfoService.updateVersion(version);
        if (i > 0) {
            return ResultDTO.ok(i);
        } else {
            return ResultDTO.fail("更新失败");
        }
    }

    @RequestMapping(value = "/get_app_version", method = RequestMethod.POST)
    public ResultDTO getAppVersion() {
        List<String> version = appInfoService.getAppVersion();
        if (version.size() == 0) {
            return ResultDTO.fail("获取app版本号失败");
        } else {
            return ResultDTO.ok(version.get(0));
        }
    }

}
