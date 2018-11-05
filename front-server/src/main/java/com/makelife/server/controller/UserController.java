package com.makelife.server.controller;

import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sunyz
 * @desc
 * @create 2018-10-11 下午4:39
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TokenEndpoint endpoint;

    @GetMapping(value = "/token/{userName}")
    public String getToken(@PathVariable String userName){

        Map<String, String> parameters=new HashMap<>();

        parameters.put("client_id","devglan-client");
        parameters.put("client_secret","devglan-secret");
        parameters.put("grant_type","client_credentials");


        try {
            ResponseEntity<OAuth2AccessToken> responseEntity= endpoint.getAccessToken(null,parameters);

            return responseEntity.toString();
        } catch (HttpRequestMethodNotSupportedException e) {
            e.printStackTrace();
        }

//       return getAppid(1);
       return null;

    }

    private String getAppid(Integer siteId){
        OkHttpClient client = new OkHttpClient();
        try{
//            String payConfigUrl=ActivityUtils.toUri(constant.getPayConfigUrl(), new MapBuilder().append("siteId",siteId).build());
            Request request = new Request.Builder().url("http://127.0.0.1:8088/oauth/token?client_id=devglan-client&client_secret=devglan-secret&grant_type=client_credentials").build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    JSONObject jsonObject=JSONObject.parseObject(response.body().string());

                    Map<String, String> result=new HashMap<>();
                    if(null!=jsonObject&&jsonObject.containsKey("access_token")){
                        result.put("access_token",jsonObject.getString("access_token"));
                        return JSONObject.toJSON(result).toString();
                    }
                } else {
                    return null;
                }
            } else {
//                logger.error("getAppid  failed ");
                return null;
            }
        }catch(Exception e){
//            logger.error(e.getMessage());
            return null;
        }
        return  null;
    }
}
