package pet.petcage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.petcage.dto.ResultDTO;
import pet.petcage.service.FeedbackSerevice;


/**
 * Created by user chenzuoli on 2020/5/18 11:19
 * description: 反馈信息控制器
 */
@RestController
@RequestMapping(value = "/merchant")
public class FeedbackController {

    @Autowired
    FeedbackSerevice feedbackSerevice;

    /**
     * 添加信息反馈
     *
     * @param feedback_content 反馈内容
     * @param pictures         反馈图片
     * @param petcage_id       笼牌号
     * @param description      反馈描述
     * @return 返回数值  > 0代表添加成功
     */
    @RequestMapping(value = "/add_feedback", method = RequestMethod.POST)
    ResultDTO addFeedback(@RequestParam("petcage_id") String petcage_id,
                          @RequestParam("feedback_content") String feedback_content,
                          @RequestParam("pictures") String pictures,
                          @RequestParam("latitude") String latitude,
                          @RequestParam("longitude") String longitude,
                          @RequestParam("description") String description) {
        int result = feedbackSerevice.addFeedback("4", petcage_id, feedback_content, pictures, latitude, longitude, description);
        if (result > 0) {
            return ResultDTO.ok(result);
        } else {
            return ResultDTO.fail("添加反馈失败");
        }
    }

}
