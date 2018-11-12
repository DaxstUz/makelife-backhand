package com.makelife.api.activity;

import com.makelife.api.activity.dto.ActivityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author sunyz
 * @desc
 * @create 2018-09-28 下午3:32
 */
@FeignClient(value = "activity-service")
public interface ActivityClient {

    @GetMapping(value = "/activity/query")
    List<ActivityDto> query();
}
