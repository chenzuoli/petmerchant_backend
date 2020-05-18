package pet.petcage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.petcage.dao.PetcageOrderRepository;
import pet.petcage.entity.PetcageOrder;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by user chenzuoli on 2020/5/17 18:00
 * description: 订单服务类
 */
@Service
public class PetcageOrderService {
    @Autowired
    PetcageOrderRepository orderRepository;

    public HashMap<String, TreeMap<String, String>> timeDistributed() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String cur_dt = format.format(new Date(System.currentTimeMillis()));
        String seven_day_before = format.format(new Date(System.currentTimeMillis()-7*86400*1000));
        HashMap<String, TreeMap<String, String>> timeDis = new HashMap<>();
        // day
        System.out.println("current dt: " + cur_dt);
        System.out.println("seven day before: " + seven_day_before);
        List<String> timeDay1 = orderRepository.timeDay1(seven_day_before, cur_dt);
        List<Object[]> timeDay2 = orderRepository.timeDay2(seven_day_before);
        TreeMap<String, String> values = deal(timeDay1, timeDay2);
        timeDis.put("day", values);

        // week
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int start_wk = calendar.get(Calendar.WEEK_OF_YEAR);
        System.out.println("currend week: " + start_wk);
        System.out.println("seven week before: " + (start_wk-7));
        List<String> timeWeek1 = orderRepository.timeWeek1(start_wk - 7, start_wk);
        List<Object[]> timeWeek2 = orderRepository.timeWeek2(start_wk - 7);
        values = deal(timeWeek1, timeWeek2);
        timeDis.put("week", values);

        String cur_month = cur_dt.substring(0, 7);
        calendar.add(Calendar.MONTH, -7);
        String seven_month_before = format.format(calendar.getTime()).substring(0, 7);
        System.out.println("current month: " + cur_month);
        System.out.println("seven month before: " + seven_month_before);
        List<String> timeMonth1 = orderRepository.timeMonth1(seven_month_before, cur_month);
        List<Object[]> timeMonth2 = orderRepository.timeMonth2(seven_month_before);
        values = deal(timeMonth1, timeMonth2);
        timeDis.put("month", values);

        System.out.println(timeDis);
        return timeDis;
    }

    private TreeMap<String, String> deal(List<String> time1, List<Object[]> time2) {
        TreeMap<String, String> result = new TreeMap<>();
        System.out.println("------deal start-------");
        System.out.println(time1);
        System.out.println(time2);
        for (String day : time1) {
            String value = "0";
            for (Object[] row: time2) {
                for (int i = 0; i < row.length; i++) {
                    if (day.equals(row[0].toString())) {
                        value = row[1].toString();
                    }
                }
            }
            result.put(day, value);
        }
        System.out.println("-------deal end--------");
        System.out.println(result);
        return result;
    }

    public List<PetcageOrder> getOrders(String petcage_id) {
        return orderRepository.getOrders(petcage_id);
    }

    public List<PetcageOrder> searchOrders(String phone, String date) {
        return orderRepository.searchOrder(phone, date);
    }
}
