package pet.petcage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.petcage.entity.MerchantAppInfo;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by user chenzuoli on 2020/4/5 18:47
 * description: 微信小程序信息工厂
 */
@Repository
public interface AppInfoRepository extends JpaRepository<MerchantAppInfo, String> {
    @Query(value = "select * from app_info order by update_time desc limit 1", nativeQuery = true)
    MerchantAppInfo getServicePrivateContent();

    @Transactional
    @Modifying
    @Query(value = "update merchant_app_info set version = ?1, update_time = ?2 order by update_time desc limit 1", nativeQuery = true)
    int updateVersion(String version, String update_time);

    @Query(value = "select version from merchant_app_info order by update_time desc limit 1", nativeQuery = true)
    List<String> getAppVersion();

}
