package pet.petcage.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * Created by user chenzuoli on 2020/4/5 10:37
 * description: 七牛云工具类
 */
public class QiNiuCludeUtil {

    /**
     * 上传文件至七牛云
     *
     * @param filePath 本地文件路径
     */
    public static void uploadFile(String filePath, String accessKey, String secretKey, String bucketName) {
        try {
            //如果是Windows情况下，文件路径格式是 D:\\qiniu\\test.png
            Configuration cfg = new Configuration(Region.region1());
            UploadManager uploadManager = new UploadManager(cfg);
            //...生成上传凭证，然后准备上传
            System.out.println("qiniu params, accesskey:" + accessKey + ",secretkey:" + secretKey + ",bucketname:" + bucketName);

            //默认不指定key的情况下，以文件内容的hash值作为文件名
            String key = null;
            Auth auth = Auth.create(accessKey, secretKey);
//            String upToken = auth.uploadToken(bucketName);
            // 指定七牛云对象存储空间中的文件名
            String upToken = auth.uploadToken(bucketName, null, 3600, new StringMap()
                    .putNotEmpty("saveKey", filePath), true);
            Response response = uploadManager.put(filePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    /**
     * 私有空间获取文件url，获取七牛云空间中的图片url地址，默认一小时有效
     *
     * @param fileName  文件名
     * @param accessKey 七牛云accesskey
     * @param secretKey 七牛云secretkey
     * @return 访问地址
     */
    public static String getFileUrl(String domainOfBucket, String fileName, String accessKey, String secretKey) {
        System.out.println("get file url params, file name: " + fileName + ",access key: " + accessKey + ", secret key: " + secretKey);
        String encodedFileName = null;
        String finalUrl = null;
        try {
            encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
            String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
            Auth auth = Auth.create(accessKey, secretKey);
            long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
            finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
            System.out.println(finalUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return finalUrl;
    }

    /**
     * 公开空间获取图片url
     *
     * @param domainOfBucket
     * @param fileName
     * @return
     */
    public static String getFileUrl(String domainOfBucket, String fileName) {
        String finalUrl = String.format("%s/%s", domainOfBucket, fileName);
        System.out.println(finalUrl);
        return finalUrl;
    }


}
