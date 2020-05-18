package pet.petcage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.petcage.dao.DimPetRepository;
import pet.petcage.entity.DimPet;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/4/8 11:23
 * description: 宠物维表服务
 */
@Service
public class DimPetService extends BaseService<DimPet> {
    @Autowired
    DimPetRepository dimPetRepository;

    @Override
    public DimPet getById(String id) {
        return null;
    }

    public List<DimPet> getDimPet() {
        return dimPetRepository.getDimPet();
    }

}
