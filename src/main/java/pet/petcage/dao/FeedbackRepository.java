package pet.petcage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.petcage.entity.Feedback;

import javax.transaction.Transactional;


/**
 * Created by user chenzuoli on 2020/5/18 11:06
 * description: 反馈信息工厂
 */
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, String> {

    @Transactional
    @Modifying
    @Query(value = "insert into feedback(feedback_type, petcage_id, feedback_content, pictures, latitude, longitude, description) values(?,?,?,?,?,?,?)", nativeQuery = true)
    int addFeedback(String feedback_type, String petcage_id, String feedback_content, String pictures, String latitude, String longitude, String description);

}
