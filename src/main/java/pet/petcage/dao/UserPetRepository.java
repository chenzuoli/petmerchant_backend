package pet.petcage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.petcage.entity.DimPet;
import pet.petcage.entity.UserPet;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/5/22 10:02
 * description: 宠物工厂
 */
@Repository
public interface UserPetRepository extends JpaRepository<UserPet, String> {

    @Query(value = "select * from user_pet", nativeQuery = true)
    List<UserPet> petDistributed();

    @Query(value = "select gender from user_pet", nativeQuery = true)
    List<String> petGenderDistributed();

}
