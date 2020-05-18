package pet.petcage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.petcage.dao.PetcageRepository;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/5/18 16:32
 * description: 宠笼服务
 */
@Service
public class PetcageService {
    @Autowired
    PetcageRepository petcageRepository;

    public int addPetcage(String phone, String accessory_ids) {
        return petcageRepository.addPetcage(phone, accessory_ids, "采购中");
    }

    public List<String> getPetcages(String phone) {
        return petcageRepository.getPetcages(phone);
    }
}