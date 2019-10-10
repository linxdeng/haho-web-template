package cn.haho.template.common.http;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Auther: linx.deng
 * @Date: 2019/9/3 19:50
 */
public class HttpUtils {
    private static Logger log=LoggerFactory.getLogger(HttpUtils.class);
    private static final int CONNECTION_TIMEOUT = 30000;

    /** 初始化httpClient */
    public static HttpClient httpClient=null;
    public static HttpClient getInstance() {
        if(httpClient==null){
            httpClient=HttpClients.createDefault();
        }
        return httpClient;
    }

    /**
     * 带参数的post请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public static HttpResult doPost(String url, Map<String, Object> map) {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        HttpResult httpResult = null;
        try {
            //设置超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(CONNECTION_TIMEOUT)
                    .setConnectTimeout(CONNECTION_TIMEOUT).setConnectionRequestTimeout(CONNECTION_TIMEOUT).build();
            httpPost.setConfig(requestConfig);

            // 判断map不为空
            if (map != null) {
                // 把表单对象设置到httpPost中
                httpPost.setEntity(creatJsonEntity(map));
            }
            // 使用HttpClient发起请求，返回response
            HttpResponse response = HttpUtils.getInstance().execute(httpPost);

            // 解析response封装返回对象httpResult

            if (response.getEntity() != null) {
                httpResult = new HttpResult(response.getStatusLine().getStatusCode(),
                        EntityUtils.toString(response.getEntity(), "UTF-8"));
            } else {
                httpResult = new HttpResult(response.getStatusLine().getStatusCode(), "");
            }
        }catch (Exception e){
            log.error("发送请求错误,e={}",e);
        }
        // 返回结果
        return httpResult;
    }

    /**
     * 创建表单对象
     * @param map
     * @return
     * @throws Exception
     */
    public static HttpEntity creatFormEntity(Map<String, Object> map) throws Exception{
        // 声明存放参数的List集合
        List<NameValuePair> params = new ArrayList<>();

        // 遍历map，设置参数到list中
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
        }
        // 创建form表单对象
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "utf-8");
        return formEntity;
    }

    /**
     * 创建JSON对象
     * @param map
     * @return
     * @throws Exception
     */
    public static HttpEntity creatJsonEntity(Map<String, Object> map) {
        // 创建form表单对象
        String json=JSON.toJSONString(map);
        StringEntity formEntity = new StringEntity(json, "utf-8");
        formEntity.setContentType("application/json");
        return formEntity;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("value","abc");
        HttpResult result=HttpUtils.doPost("http://127.0.0.1:40325/create", map);
        log.info("结果：{}",result);
    }
}
