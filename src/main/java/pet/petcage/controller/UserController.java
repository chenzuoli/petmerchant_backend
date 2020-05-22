package pet.petcage.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.petcage.dto.ResultDTO;
import pet.petcage.service.UserService;
import pet.petcage.util.Util;

import java.util.HashMap;
import java.util.List;

/**
 * Created by user chenzuoli on 2020/5/22 11:40
 * description: 用户工厂
 */
@RestController
@RequestMapping(value = "/merchant")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/user_gender_dis")
    public ResultDTO userGenderDistributed() {
        List<String> userGenders = userService.userGenderDistributed();
        HashMap<String, Integer> map = new HashMap<>();
        for (String userGender : userGenders) {
            if ("1".equals(userGender)) {
                userGender = "男";
            } else if ("0".equals(userGender)) {
                userGender = "女";
            } else {
                userGender = "未知";
            }
            if (!map.containsKey(userGender)) {
                map.put(userGender, 1);
            } else {
                map.put(userGender, map.get(userGender) + 1);
            }
        }
        JSONArray result = Util.map2JsonArray(map);
        return ResultDTO.ok(result);
    }
}
