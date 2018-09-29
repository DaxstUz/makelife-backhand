package com.makelife.server.controller;

import com.makelife.api.activity.ActivityClient;
import com.makelife.api.invoice.InvoiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunyz
 * @desc
 * @create 2018-09-28 下午4:28
 */
@RestController
public class TestController {


    @Autowired
    private InvoiceClient invoiceApi;


    @Autowired
    private ActivityClient activityApi;

    @GetMapping(value = "getT")
    public String test(){
        return invoiceApi.getName() + "  "+activityApi.getA();
    }
}
