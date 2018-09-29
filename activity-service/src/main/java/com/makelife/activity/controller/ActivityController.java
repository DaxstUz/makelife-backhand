package com.makelife.activity.controller;

import com.makelife.activity.controller.mapper.ActivityAdapter;
import com.makelife.activity.domain.Activity;
import com.makelife.activity.service.ActivityService;
import com.makelife.api.activity.ActivityClient;
import com.makelife.api.activity.dto.ActivityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sunyz
 * @desc 活动API
 * @create 2018-09-19 上午9:22
 */
@RestController
public class ActivityController implements ActivityClient {

    Logger logger=LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityService service;

    @Autowired
    private ActivityAdapter adapter;

    @GetMapping("/activity/{id}")
    public Activity getByid(@PathVariable Long id){
        return service.selectByPk(id);
    }

    @GetMapping("/activity")
    public List<Activity> list(@RequestParam(name = "title",required = false) String title,
                               @RequestParam(name = "page",required = false,defaultValue = "1") int page,
                               @RequestParam(name = "pageSize",required = false,defaultValue = "10") int pageSize){
        logger.info(" 获取日志列表： activity");
        return service.query(title,page,pageSize);
    }

    @Override
    public List<ActivityDto> query() {
        List<Activity> list=service.listAll();
        return adapter.mapDto(list);
//        return null;
    }
}
