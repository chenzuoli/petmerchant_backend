package pet.petcage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Created by user chenzuoli on 2020/3/24 22:10
 * description:
 */
@RestController
public class CreateDeviceData {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addDevices() {
        double min_latitude = 31.56096;
        double min_longitude = 114.12694;

//        String url = "https://localhost:7443/petcage/addDevice";
//        HashMap<String, String> params = new HashMap<>();
//        for (int i = 0; i < 100; i++) {
//            double latitude = new Random(1).nextDouble() + min_latitude;
//            double longitude = new Random(1).nextDouble() + min_longitude;
//            double kwh = new Random(100).nextDouble();
//            params.put("device_id", String.valueOf(i));
//            params.put("latitude", String.valueOf(latitude));
//            params.put("longitude", String.valueOf(longitude));
//            params.put("kwh", String.valueOf(kwh));
//            System.out.println("序号：" + String.valueOf(i));
//            String response = HttpUtil.sendPost(url, params);
//            System.out.println("---" + response);
//        }
        for (int i = 0; i < 100; i++) {
            double latitude = new Random(1).nextDouble() + min_latitude;
            double longitude = new Random(1).nextDouble() + min_longitude;
            double kwh = new Random(100).nextDouble();
            System.out.println("序号：" + String.valueOf(i));
            int update = jdbcTemplate.update("insert into device(device_id, latitude, longitude, kwh) values(?1, ?2, ?3, ?4)",
                    String.valueOf(i),
                    String.valueOf(latitude),
                    String.valueOf(longitude),
                    String.valueOf(kwh));
            System.out.println("---" + String.valueOf(update));
        }
    }
}
