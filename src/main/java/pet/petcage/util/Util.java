package pet.petcage.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created by user chenzuoli on 2020/5/22 11:46
 * description: 工具类
 */
public class Util {

    public static JSONArray map2JsonArray(Map<String, Integer> map) {
        JSONArray result = new JSONArray();
        for (String key : map.keySet()) {
            JSONObject jsonObject = new JSONObject();
            if (key == null) {
                key = "未知";
            }
            jsonObject.put("name", key);
            jsonObject.put("data", map.get(key));
            result.add(jsonObject);
        }
        return result;
    }
}
