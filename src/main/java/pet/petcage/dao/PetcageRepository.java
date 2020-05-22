package pet.petcage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.petcage.entity.Petcage;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by user chenzuoli on 2020/5/18 16:30
 * description: 宠笼工厂
 */
@Repository
public interface PetcageRepository extends JpaRepository<Petcage, String> {
    @Transactional
    @Modifying
    @Query(value = "insert into device(phone, accessory_ids, status, size) values (?1, ?2, ?3, ?4)", nativeQuery = true)
    int addPetcage(String phone, String accessory_ids, String status, String size);

    @Query(value = "select device_id from device where phone = ?1", nativeQuery = true)
    List<String> getPetcages(String phone);

    @Query(value = "select * from device where phone = ?1", nativeQuery = true)
    List<Petcage> petcageList(String phone);


}
