package pet.petcage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.petcage.dto.ResultDTO;
import pet.petcage.entity.Accessory;
import pet.petcage.entity.Petcage;
import pet.petcage.service.AccessoryService;
import pet.petcage.service.PetcageService;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/5/18 16:34
 * description: 宠笼控制器
 */
@RestController
@RequestMapping(value = "/merchant")
public class PetcageController {
    @Autowired
    PetcageService petcageService;
    @Autowired
    AccessoryService accessoryService;

    @RequestMapping(value = "/add_petcage")
    public ResultDTO addPetcage(@RequestParam("phone") String phone,
                                @RequestParam("accessory_ids") String accessory_ids,
                                @RequestParam("size") String size) {
        int result = petcageService.addPetcage(phone, accessory_ids, size);
        if (result > 0) {
            return ResultDTO.ok(result);
        } else {
            return ResultDTO.fail("添加失败");
        }
    }

    @RequestMapping(value = "/petcage_list")
    public ResultDTO petcageList(@RequestParam("phone") String phone) {
        List<Petcage> petcages = petcageService.petcageList(phone);
        List<Accessory> accessories = accessoryService.petcageAccessories();
        JSONArray result = new JSONArray();
        for (Petcage petcage : petcages) {
            JSONObject jsonObject = new JSONObject();
            String accessory_ids = petcage.getAccessory_ids();
            jsonObject.put("device_name", petcage.getDevice_name() == null ? "" : petcage.getDevice_name());

            jsonObject.put("kwh", petcage.getKwh() == null ? "" : petcage.getKwh());

            String size;
            if ("1".equals(petcage.getSize())) {
                size = "小";
            } else if ("2".equals(petcage.getSize())) {
                size = "中";
            } else if ("3".equals(petcage.getSize())) {
                size = "大";
            } else {
                size = "其他";
            }
            jsonObject.put("size", size);

            if (accessory_ids == null) {
                jsonObject.put("accessory_names", "");
            } else {
                String[] ids = accessory_ids.split(",");
                StringBuilder accessory_names = new StringBuilder();
                for (int i = 0; i < ids.length; i++) {
                    String id = ids[i];
                    for (Accessory accessory : accessories) {
                        if (id.equals(accessory.getAccessory_id())) {
                            accessory_names.append(accessory.getAccessory_name()).append(",");
                        }
                    }
                }
                if (accessory_names.length() > 0) {
                    jsonObject.put("accessory_names", accessory_names.substring(0, accessory_names.length() - 1));
                } else {
                    jsonObject.put("accessory_names", "");
                }
            }
            result.add(jsonObject);
        }
        return ResultDTO.ok(result);
    }
}
