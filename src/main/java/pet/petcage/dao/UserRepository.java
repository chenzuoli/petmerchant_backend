package pet.petcage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.petcage.entity.User;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/5/22 11:30
 * description: 用户工厂
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "select gender from user", nativeQuery = true)
    List<String> userGenderDistributed();
}
