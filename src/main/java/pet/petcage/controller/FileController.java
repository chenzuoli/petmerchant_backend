package pet.petcage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pet.petcage.common.Constant;
import pet.petcage.dto.ResultDTO;
import pet.petcage.util.QiNiuCludeUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by user chenzuoli on 2020/3/26 09:29
 * description: 文件上传下载控制器
 */
@RestController
@RequestMapping("/merchant")
public class FileController {
    @Autowired
    Constant constant;

    @ResponseBody
    @RequestMapping(value = "/upload_file", method = RequestMethod.POST)
    public ResultDTO upload(HttpServletRequest request, @RequestParam(value = "avatarFile", required = false) MultipartFile file) throws IOException {
        System.out.println("execute upload file...");
        request.setCharacterEncoding("UTF-8");
        String visit_url = "";
        if (!file.isEmpty()) {
            System.out.println("success get the file.");
            String fileName = file.getOriginalFilename();
            String path = null;
            String type = null;
            type = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            System.out.println("initialize file name as: " + fileName + ", type is: " + type);
            if (type != null) {
                // 自定义的文件名称
                String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                // 设置存放图片文件的路径
                path = constant.getAvatar_path() + "/" + trueFileName;
                System.out.println("server local file path:" + path);
                file.transferTo(new File(path));
                System.out.println("success upload file to the server.");

                // 文件上传到七牛云
                // 上传小程序码到七牛云
                System.out.println("upload the file to the qiniu cloud.");
                QiNiuCludeUtil.uploadFile(path,
                        constant.getQiniu_access_key(),
                        constant.getQiniu_secret_key(),
                        constant.getQiniu_bucket_name());
//                    visit_url = QiNiuCludeUtil.getFileUrl(
//                            constant.getQiniu_domain_of_bucket(),
//                            path,
//                            constant.getQiniu_access_key(),
//                            constant.getQiniu_secret_key());
                // 公开空间
                visit_url = QiNiuCludeUtil.getFileUrl(
                        constant.getQiniu_domain_of_bucket(),
                        path
                );
            } else {
                System.out.println("type of the file is empty");
                return ResultDTO.fail("error");
            }
        } else {
            System.out.println("cannot find the file");
            return ResultDTO.fail("error");
        }
        return ResultDTO.ok(visit_url);
    }
}
