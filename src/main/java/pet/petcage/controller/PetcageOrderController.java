package pet.petcage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.petcage.dto.ResultDTO;
import pet.petcage.service.PetcageOrderService;

import java.util.HashMap;
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

    @RequestMapping(value = "/time_distributed_charts")
    public ResultDTO timeDistributed() {
        HashMap<String, TreeMap<String, String>> timeDis = orderService.timeDistributed();
        if (timeDis.size() == 0) {
            return ResultDTO.fail("查询失败");
        } else {
            return ResultDTO.ok(timeDis);
        }
    }

}
