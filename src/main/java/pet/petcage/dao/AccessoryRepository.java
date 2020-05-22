package pet.petcage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.petcage.entity.Accessory;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/5/22 16:53
 * description: 宠笼配件工厂
 */
@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, String> {
    @Query(value = "select * from petcage_accessory", nativeQuery = true)
    List<Accessory> petcageAccessories();
}
