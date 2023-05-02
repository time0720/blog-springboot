package com.time.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.time.blog.domain.entity.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userId);
    
}