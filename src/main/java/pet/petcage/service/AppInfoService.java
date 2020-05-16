package pet.petcage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.petcage.dao.AppInfoRepository;
import pet.petcage.entity.MerchantAppInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by user chenzuoli on 2020/4/5 18:54
 * description: 微信小程序信息服务
 */
@Service
public class AppInfoService extends BaseService<MerchantAppInfo> {

    @Autowired
    AppInfoRepository appInfoRepository;

    @Override
    public MerchantAppInfo getById(String id) {
        return null;
    }

    public int updateVersion(String version) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String current_time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        return appInfoRepository.updateVersion(version, current_time);
    }

    public List<String> getAppVersion() {
        return appInfoRepository.getAppVersion();
    }

}
