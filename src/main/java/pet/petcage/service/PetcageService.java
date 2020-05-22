package pet.petcage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.petcage.dao.PetcageRepository;
import pet.petcage.entity.Petcage;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/5/18 16:32
 * description: 宠笼服务
 */
@Service
public class PetcageService {
    @Autowired
    PetcageRepository petcageRepository;

    public int addPetcage(String phone, String accessory_ids, String size) {
        return petcageRepository.addPetcage(phone, accessory_ids, "1", size);
    }

    public List<String> getPetcages(String phone) {
        return petcageRepository.getPetcages(phone);
    }

    public List<Petcage> petcageList(String phone) {
        return petcageRepository.petcageList(phone);
    }
}