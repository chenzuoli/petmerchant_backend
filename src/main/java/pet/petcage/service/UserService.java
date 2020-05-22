package pet.petcage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.petcage.dao.UserRepository;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/5/22 11:32
 * description: 用户服务
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<String> userGenderDistributed() {
        return userRepository.userGenderDistributed();
    }
}
