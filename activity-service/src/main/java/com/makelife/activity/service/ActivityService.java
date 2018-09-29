package com.makelife.activity.service;

import com.makelife.activity.domain.Activity;
import com.makelife.activity.mapper.ActivityMapper;
import com.makelife.utils.data.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author sunyz
 * @desc
 * @create 2018-09-19 上午10:26
 */
@Service
public class ActivityService extends BaseService<Activity> {

    @Autowired
    private ActivityMapper mapper;

    public List<Activity> query(String title,
                               int page,
                               int pageSize){
        Example example=new Example(Activity.class);
        Example.Criteria criteria=example.createCriteria();
        if(StringUtils.isNotBlank(title)){
            criteria.andEqualTo("title",title);
        }
       return mapper.selectByExampleAndRowBounds(example,new RowBounds(pageSize*(page-1),pageSize));
    }

    public List<Activity> listAll(){
        return mapper.selectAll();
    }
}
