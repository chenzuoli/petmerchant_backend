package pet.petcage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.petcage.entity.PetcageOrder;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/5/17 15:11
 * description: 订单工厂
 */
@Repository
public interface PetcageOrderRepository extends JpaRepository<PetcageOrder, String> {

    @Query(value = "select dt from dim_day where dt > ?1 and dt <= ?2 order by dt", nativeQuery = true)
    List<String> timeDay1(String start_dt, String end_dt);

    @Query(value = "select substring(create_time, 1, 10) as dt, count(1) as cnt from petcage_order where substring(create_time, 1, 10) > ?1 group by substring(create_time, 1, 10) order by substring(create_time, 1, 10)", nativeQuery = true)
    List<Object[]> timeDay2(String start_dt);

    @Query(value = "select wk from dim_week where wk > ?1 and wk <= ?2 order by wk", nativeQuery = true)
    List<String> timeWeek1(int start_wk, int end_wk);

    @Query(value = "select weekofyear(create_time) as wk, count(1) as cnt from petcage_order where weekofyear(create_time) > ?1 group by weekofyear(create_time) order by weekofyear(create_time)", nativeQuery = true)
    List<Object[]> timeWeek2(int start_wk);

    @Query(value = "select mt from dim_month where mt > ?1 and mt <= ?2 order by mt", nativeQuery = true)
    List<String> timeMonth1(String start_mt, String end_mt);

    @Query(value = "select substring(create_time, 1,7) as mt, count(1) as cnt from petcage_order where substring(create_time, 1, 7) > ?1 group by substring(create_time, 1, 7) order by substring(create_time, 1, 7)", nativeQuery = true)
    List<Object[]> timeMonth2(String start_mt);

    @Query(value = "select * from petcage_order where device_id = ?1 order by create_time desc", nativeQuery = true)
    List<PetcageOrder> getOrders(String device_id);

    @Query(value = "select * from petcage_order where phone like ?1 and substring(create_time, 1, 10) = ?2 order by create_time desc", nativeQuery = true)
    List<PetcageOrder> searchOrder(String phone, String date);

}
