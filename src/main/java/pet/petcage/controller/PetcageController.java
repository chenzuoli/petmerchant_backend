package pet.petcage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.petcage.dto.ResultDTO;
import pet.petcage.service.PetcageService;

/**
 * Created by user chenzuoli on 2020/5/18 16:34
 * description: 宠笼控制器
 */
@RestController
@RequestMapping(value = "/merchant")
public class PetcageController {
    @Autowired
    PetcageService petcageService;

    @RequestMapping(value = "/add_petcage")
    public ResultDTO addPetcage(@RequestParam("phone") String phone, @RequestParam("accessory_ids") String accessory_ids) {
        int result = petcageService.addPetcage(phone, accessory_ids);
        if (result > 0) {
            return ResultDTO.ok(result);
        } else {
            return ResultDTO.fail("添加失败");
        }
    }
}
