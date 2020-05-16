package pet.petcage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.petcage.dao.MerchantRepository;
import pet.petcage.entity.Merchant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MerchantService extends BaseService<Merchant> {

    @Autowired
    MerchantRepository repo;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Merchant getById(String id) {
        System.out.println("get user by id params: " + id);
        return repo.findMerchantByPhone(id);
    }

    public boolean loginCheck(String phone, String pwd) {
        System.out.println("login check params: " + phone + "," + pwd);
        List<Merchant> merchants = repo.loginCheck(phone, pwd);
        System.out.println(merchants);
        return merchants.size() != 0;
    }

    public int updatePass(String nick_name, String pwd, String phone) {
        String update_time = dateFormat.format(new Date(System.currentTimeMillis()));
        System.out.println("update password params: " + nick_name + "," + pwd + "," + update_time + "," + phone);
        return repo.updatePass(nick_name, pwd, update_time, phone);
    }

    public List<Merchant> getMerchantByOpenid(String open_id) {
        System.out.println("get user by open_id params: " + open_id);
        return repo.getMerchantByOpenid(open_id);
    }


    public List<Merchant> getMerchantByToken(String token) {
        System.out.println("get user by token params: " + token);
        return repo.getMerchantByToken(token);
    }

    public int updateMerchantToken(String phone, String token) {
        System.out.println("update user token: " + phone + "," + token);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curr_time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        return repo.updateMerchantToken(phone, token, curr_time);
    }

    public int addMerchant(String phone, String pwd) {
        System.out.println("add merchant params phone: " + phone + ", pwd: " + pwd);
        return repo.addMerchant(phone, pwd);
    }


}
