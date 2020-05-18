package pet.petcage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.petcage.entity.DimPet;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/4/8 11:22
 * description: 宠物维表工厂
 */
@Repository
public interface DimPetRepository extends JpaRepository<DimPet, String> {

    @Query(value = "select * from dim_pet", nativeQuery = true)
    List<DimPet> getDimPet();

}
