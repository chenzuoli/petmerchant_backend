package pet.petcage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.petcage.dao.FeedbackRepository;
import pet.petcage.entity.Feedback;


/**
 * Created by user chenzuoli on 2020/5/18 11:18
 * description: 反馈信息服务
 */
@Service
public class FeedbackSerevice extends BaseService<Feedback> {
    @Autowired
    FeedbackRepository feedRepo;

    @Override
    public Feedback getById(String id) {
        return null;
    }

    /**
     * 添加反馈
     *
     * @param feedback_type    反馈类型：1订单反馈 2设备维修反馈 3系统反馈
     * @param feedback_content 反馈内容
     * @param pictures         反馈图片
     * @param petcage_id       反馈设备id
     * @param description      反馈描述
     * @return 插入成功与否
     */
    public int addFeedback(String feedback_type, String petcage_id, String feedback_content,
                           String pictures, String latitude, String longitude, String description) {
        System.out.println("add feedback params: " + "," + feedback_content + "," + feedback_content + "," + "," + pictures + "," + petcage_id + "," + description);
        return feedRepo.addFeedback(feedback_type, petcage_id, feedback_content, pictures, latitude, longitude, description);
    }

}
