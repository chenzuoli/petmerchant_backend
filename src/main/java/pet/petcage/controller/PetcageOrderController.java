package pet.petcage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.petcage.dto.ResultDTO;
import pet.petcage.entity.DimPet;
import pet.petcage.entity.PetcageOrder;
import pet.petcage.service.DimPetService;
import pet.petcage.service.PetcageOrderService;
import pet.petcage.service.PetcageService;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by user chenzuoli on 2020/5/17 18:04
 * description:
 */
@RestController
@RequestMapping(value = "/merchant")
public class PetcageOrderController {
    @Autowired
    PetcageOrderService orderService;
    @Autowired
    PetcageService petcageService;
    @Autowired
    DimPetService dimPetService;

    @RequestMapping(value = "/time_distributed_charts")
    public ResultDTO timeDistributed() {
        HashMap<String, TreeMap<String, String>> timeDis = orderService.timeDistributed();
        if (timeDis.size() == 0) {
            return ResultDTO.fail("查询失败");
        } else {
            return ResultDTO.ok(timeDis);
        }
    }

    @RequestMapping(value = "/order_list")
    public ResultDTO getPetcageOrders(@RequestParam("phone") String phone) {
        JSONArray result = new JSONArray();
        List<String> petcage_ids = petcageService.getPetcages(phone);
        List<DimPet> dimPet = dimPetService.getDimPet();
        for (String petcage_id : petcage_ids) {
            List<PetcageOrder> orders = orderService.getOrders(petcage_id);
            for (PetcageOrder order : orders) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("phone", order.getPhone() == null ? "" : order.getPhone());
                String pet_id = order.getPet_id();
                for (DimPet pet : dimPet) {
                    String pet_type = "";
                    if (pet.getId().equals(pet_id)) {
                        pet_type = pet.getPet_type();
                    }
                    jsonObject.put("pet_type", pet_type);
                }
                jsonObject.put("start_time", order.getStart_time() == null ? "" : order.getStart_time());
                jsonObject.put("end_time", order.getEnd_time() == null ? "" : order.getEnd_time());
                result.add(jsonObject);
            }
        }
        return ResultDTO.ok(result);
    }

    @RequestMapping(value = "/search_order")
    public ResultDTO searchOrders(@RequestParam("phone") String phone, @RequestParam("date") String date) {
        List<PetcageOrder> petcageOrders = orderService.searchOrders(phone, date);

        return ResultDTO.ok(petcageOrders);
    }

}
