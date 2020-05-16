package pet.petcage.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import pet.petcage.dto.ResultDTO;
import pet.petcage.entity.Merchant;
import pet.petcage.error.CommonErrorCode;
import pet.petcage.service.MerchantService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by user chenzuoli on 2020/3/27 17:44
 * description: 登录拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    MerchantService merchantService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI();

        //允许login地址无需登陆即可访问
        if (url.indexOf("/petcage/merchant/login") >= 0 || url.indexOf("/petcage/merchant/register") >= 0) {
            return true;
        }

        //请求之前，验证通过返回true，验证失败返回false
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            makeFail(response);
            return false;
        } else {
            List<Merchant> merchants = merchantService.getMerchantByToken(token);
            if (merchants.size() == 0) {
                makeFail(response);
                return false;
            }
        }
        return true;
    }

    private void makeFail(HttpServletResponse response) {
        ResultDTO resultDTO = ResultDTO.fail(CommonErrorCode.NO_USER);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            out.print(JSON.toJSONString(resultDTO));
            out.close();
        } catch (Exception e) {
            System.out.println("LoginInterceptor preHandle" + e.toString());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
