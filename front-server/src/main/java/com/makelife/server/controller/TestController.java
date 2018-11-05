package com.makelife.server.controller;

import com.makelife.api.activity.ActivityClient;
import com.makelife.api.invoice.InvoiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * @create 2018-09-28 下午4:28
 */
@RestController
@RequestMapping("/users")
public class TestController {


    @Autowired
    private InvoiceClient invoiceApi;


    @Autowired
    private ActivityClient activityApi;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/getT")
    public String test(){
        return "  "+activityApi.query();
//        return invoiceApi.getName() + "  "+activityApi.query();
    }

    @GetMapping(value = "/getS")
    public String testS(){
        return "安全测试";
    }

}
