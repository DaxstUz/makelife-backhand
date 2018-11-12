package com.makelife.invoice.controller;

import com.makelife.api.invoice.InvoiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunyz
 * @desc
 * @create 2018-09-28 下午3:50
 */
@RestController
public class InvoiceController implements InvoiceClient {

    @Value("${yy:pool}")
    private String name;

    @Override
    public String getName() {
        return "daxstuz 哈哈哈" + name;
    }
}
