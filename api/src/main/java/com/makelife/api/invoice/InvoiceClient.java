package com.makelife.api.invoice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author sunyz
 * @desc
 * @create 2018-09-28 下午3:31
 */
@FeignClient(value = "invoice-service")
public interface InvoiceClient {

    @GetMapping(value = "/invoice/getn")
    String getName();

}
