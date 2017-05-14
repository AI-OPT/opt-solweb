package com.ai.opt.sol.core.sandbox.mock;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.ai.runner.base.exception.SystemException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpClientUtil {

    public static JSONObject sendPostRequest(String url, String data) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        JSONObject d = new JSONObject();
        String message = null;
        try {
            HttpPost httpPost = new HttpPost(new URL(url).toURI());
            StringEntity dataEntity = new StringEntity(data, ContentType.APPLICATION_JSON);
            httpPost.setEntity(dataEntity);
            response = httpclient.execute(httpPost);
            int httpStatus = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                message = EntityUtils.toString(entity, "UTF-8");

            } else {
                message = "服务端返回结果为空";
            }
            Object obj = null;
            try {
                obj = JSON.parse(message);
            } catch (Exception ex) {
                obj = message;
            }

            d.put("HttpStatus", httpStatus);
            d.put("HttpEntity", obj);
        } catch (MalformedURLException e) {
            throw new SystemException(e);
        } catch (URISyntaxException e) {
            throw new SystemException(e);
        } catch (ClientProtocolException e) {
            throw new SystemException(e);
        } catch (IOException e) {
            throw new SystemException(e);
        }
        return d;
    }

    public static void main(String[] args) {
        String url = "http://10.1.228.222:30886/clc/querysysconfig/querySysConfig";
        String data = "{\"tenantId\":\"BYD\",\"cfgtype\":\"product\",\"cfgkey\":\"productId\"}";
        JSONObject obj = sendPostRequest(url, data);
        System.out.println(obj.toJSONString());
    }

}
