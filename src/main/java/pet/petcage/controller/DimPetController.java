package pet.petcage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pet.petcage.dto.ResultDTO;
import pet.petcage.entity.DimPet;
import pet.petcage.service.DimPetService;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/4/8 11:24
 * description: 宠物维表控制器
 */
@RestController
@RequestMapping(value = "/merchant")
public class DimPetController {
    @Autowired
    DimPetService dimPetService;

    @RequestMapping(value = "/get_dim_pet", method = RequestMethod.POST)
    public ResultDTO getDimPet() {
        List<DimPet> dimPet = dimPetService.getDimPet();
        if (dimPet.size() == 0) {
            return ResultDTO.fail("获取宠物维表失败");
        } else {
            return ResultDTO.ok(dimPet);
        }
    }
}
