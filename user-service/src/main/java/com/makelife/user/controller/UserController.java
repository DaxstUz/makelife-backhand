package com.makelife.user.controller;

import com.makelife.api.activity.ActivityClient;
import com.makelife.api.invoice.InvoiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunyz
 * @desc
 * @create 2018-09-28 下午4:03
 */
@RestController
public class UserController {

    @Autowired
    private InvoiceClient invoiceApi;


    @Autowired
    private ActivityClient activityApi;

    @GetMapping("name")
    public String getInfo(){
        return activityApi.query() + " "  +invoiceApi.getName();
    }
}
