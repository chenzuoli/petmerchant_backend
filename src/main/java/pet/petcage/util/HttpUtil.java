package pet.petcage.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {

    /**
     * description: 发送post请求
     * param: [url, params]
     * return: java.lang.String
     * date: 2018/6/13
     * time: 15:39
     */
    public static String sendPost(String url, Map<String, String> params) {
        String str = "";
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(url);
            List<NameValuePair> list = getNameValuePairArr(params);
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
            httppost.setEntity(entity);
            CloseableHttpResponse response;
            try {
                response = httpclient.execute(httppost);
                HttpEntity httpentity = response.getEntity();
                if (httpentity != null) {
                    str = EntityUtils.toString(httpentity, "UTF-8");
                }
            } catch (Exception e) {
                System.out.println("send post request to url: " + url + " with parameters: " + params + " exception!");
            }
        } catch (Exception e) {
            System.out.println("send post request to url: " + url + " with parameters: " + params + " exception!");
        }
        return str;
    }

    private static List<NameValuePair> getNameValuePairArr(
            Map<String, String> parasMap) {
        List<NameValuePair> nvps = new ArrayList<>();
        for (Map.Entry<String, String> parasEntry : parasMap.entrySet()) {
            String parasName = parasEntry.getKey();
            String parasValue = parasEntry.getValue();
            nvps.add(new BasicNameValuePair(parasName, parasValue));
        }
        return nvps;
    }

    /**
     * description: 发送https http请求
     * param: [urlStr, postData为json字符串, requestMethod：请求方法（get post）]
     * return: 响应结果
     * time: 2018/6/29 13:40
     */
    public static String request(String urlStr, String postData, String requestMethod) {
        String result = null;
        try {
            //创建连接
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod(requestMethod);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json; charset=gbk");
            connection.connect();
            //请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.write(postData.getBytes(StandardCharsets.UTF_8));
            out.flush();
            out.close();
            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer();
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), StandardCharsets.UTF_8);
                sb.append(lines);
            }
            result = sb.toString();
            System.out.println(sb);
            reader.close();
            // 断开连接
            connection.disconnect();
        } catch (Exception e) {
            System.out.println("send " + requestMethod + " request to url: " + urlStr + " with parameters: " + postData + " exception!");
            System.out.println(e);
        }
        return result;
    }

    /**
     * get请求
     *
     * @param urlStr url
     * @return response
     */
    public static String get(String urlStr) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 返回结果-字节输入流转换成字符输入流，控制台输出字符
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            System.out.println(sb);
        } catch (IOException e) {
            System.out.println(e);
        }
        return sb.toString();
    }

    /**
     * 发送post请求
     *
     * @param urlStr url
     * @param body   请求参数：json格式字符串
     * @return 响应
     */
    public static String post(String urlStr, String body) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            // 设置Content-Type
            connection.setRequestProperty("Content-Type", "application/json");
            // 设置是否向httpUrlConnection输出，post请求设置为true，默认是false
            connection.setDoOutput(true);

            // 设置RequestBody
            PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
            printWriter.write(body);
            printWriter.flush();

            // 返回结果-字节输入流转换成字符输入流，控制台输出字符
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
