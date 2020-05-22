package pet.petcage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.petcage.dao.DimPetRepository;
import pet.petcage.dao.UserPetRepository;
import pet.petcage.entity.DimPet;
import pet.petcage.entity.UserPet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user chenzuoli on 2020/5/22 10:03
 * description: 宠物服务
 */
@Service
public class UserPetService {
    @Autowired
    UserPetRepository userPetRepository;
    @Autowired
    DimPetRepository dimPetRepository;

    public List<UserPet> petDistributed() {
        return userPetRepository.petDistributed();
    }

    public List<String> petGenderDistributed() {
        return userPetRepository.petGenderDistributed();
    }

    public List<String> petSizeDistributed() {
        List<UserPet> userPets = userPetRepository.petDistributed();
        List<DimPet> dimPets = dimPetRepository.petDogDistributed();
        ArrayList<String> result = new ArrayList<>();
        for (UserPet userPet : userPets) {
            for (DimPet dimPet : dimPets) {
                if (userPet.getPet_type().equals(dimPet.getPet_type()) &&
                        userPet.getVariety().equals(dimPet.getVariety())) {
                    result.add(dimPet.getSize());
                }
            }
        }
        return result;
    }
}
