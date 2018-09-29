package com.makelife.activity.controller.mapper;

import com.makelife.activity.domain.Activity;
import com.makelife.api.activity.dto.ActivityDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author sunyz
 * @desc
 * @create 2018-09-28 下午4:54
 */
@Mapper(componentModel = "spring")
public abstract class ActivityAdapter {

    public abstract ActivityDto mapDto(Activity obj);

    public abstract List<ActivityDto> mapDto(List<Activity> objs);

}
