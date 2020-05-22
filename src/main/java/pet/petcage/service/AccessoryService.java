package pet.petcage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.petcage.dao.AccessoryRepository;
import pet.petcage.entity.Accessory;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/5/22 16:54
 * description: 宠笼配件服务
 */
@Service
public class AccessoryService {
    @Autowired
    AccessoryRepository accessoryRepository;

    public List<Accessory> petcageAccessories() {
        return accessoryRepository.petcageAccessories();
    }
}
