package com.xiaoman.openapi.dal.mappers;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xiaoman.openapi.dal.dataobject.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
