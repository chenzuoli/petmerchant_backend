package pet.petcage.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.petcage.dto.ResultDTO;
import pet.petcage.entity.UserPet;
import pet.petcage.service.UserPetService;
import pet.petcage.util.Util;

import java.util.HashMap;
import java.util.List;

/**
 * Created by user chenzuoli on 2020/5/22 10:05
 * description: 宠物控制器
 */
@RestController
@RequestMapping(value = "/merchant")
public class UserPetController {
    @Autowired
    UserPetService userPetService;

    @RequestMapping(value = "/pet_type_dis")
    public ResultDTO petTypeDistributed() {
        List<UserPet> userPets = userPetService.petDistributed();
        HashMap<String, Integer> map = new HashMap<>();
        for (UserPet userPet : userPets) {
            if (!map.containsKey(userPet.getPet_type())) {
                map.put(userPet.getPet_type(), 1);
            } else {
                map.put(userPet.getPet_type(), map.get(userPet.getPet_type()) + 1);
            }
        }
        JSONArray result = Util.map2JsonArray(map);
        return ResultDTO.ok(result);
    }

    @RequestMapping(value = "/pet_variety_dis")
    public ResultDTO petVarietyDistributed() {
        List<UserPet> userPets = userPetService.petDistributed();
        HashMap<String, Integer> map = new HashMap<>();
        for (UserPet userPet : userPets) {
            if (!map.containsKey(userPet.getVariety())) {
                map.put(userPet.getVariety(), 1);
            } else {
                map.put(userPet.getVariety(), map.get(userPet.getVariety()) + 1);
            }
        }
        JSONArray result = Util.map2JsonArray(map);
        return ResultDTO.ok(result);
    }


    @RequestMapping(value = "/pet_gender_dis")
    public ResultDTO petGenderDistributed() {
        List<String> petGenders = userPetService.petGenderDistributed();
        HashMap<String, Integer> map = new HashMap<>();
        for (String petGender : petGenders) {
            if ("1".equals(petGender)) {
                petGender = "公";
            } else if ("0".equals(petGender)) {
                petGender = "母";
            } else {
                petGender = "未知";
            }
            if (!map.containsKey(petGender)) {
                map.put(petGender, 1);
            } else {
                map.put(petGender, map.get(petGender) + 1);
            }
        }
        JSONArray result = Util.map2JsonArray(map);
        return ResultDTO.ok(result);
    }

    @RequestMapping(value = "/pet_size_dis")
    public ResultDTO petSizeDistributed() {
        List<String> sizes = userPetService.petSizeDistributed();
        HashMap<String, Integer> map = new HashMap<>();
        for (String size : sizes) {
            if ("1".equals(size)) {
                size = "小";
            } else if ("2".equals(size)) {
                size = "中";
            } else {
                size = "大";
            }
            if (!map.containsKey(size)) {
                map.put(size, 1);
            } else {
                map.put(size, map.get(size) + 1);
            }
        }
        JSONArray result = Util.map2JsonArray(map);
        return ResultDTO.ok(result);
    }


}
