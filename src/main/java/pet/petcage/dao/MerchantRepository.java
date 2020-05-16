package pet.petcage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pet.petcage.entity.Merchant;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, String> {

    Merchant findMerchantByPhone(String mobile);

    @Query(value = "select * from merchant where phone = :phone and pwd = password(:pwd)", nativeQuery = true)
    List<Merchant> loginCheck(@Param("phone") String phone, @Param("pwd") String pwd);

    @Transactional
    @Modifying
    @Query(value = "update merchant set nick_name = ?1, pwd = password(?2), update_time = ?3 where phone = ?4", nativeQuery = true)
    int updatePass(String nick_name, String pwd, String update_time, String phone);

    @Query(value = "select * from merchant where open_id = ?1 order by create_time desc limit 1", nativeQuery = true)
    List<Merchant> getMerchantByOpenid(String open_id);

    @Query(value = "select * from merchant where token = ?1", nativeQuery = true)
    List<Merchant> getMerchantByToken(String token);

    @Transactional
    @Modifying
    @Query(value = "update merchant set token = ?2, update_time = ?3 where phone = ?1", nativeQuery = true)
    int updateMerchantToken(String phone, String token, String cur_time);

    @Transactional
    @Modifying
    @Query(value = "insert into merchant(phone, pwd) values(?1,password(?2))", nativeQuery = true)
    int addMerchant(String phone, String pwd);

}
