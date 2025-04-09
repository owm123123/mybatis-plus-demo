package com.example.mybatisplusdemo.user.repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.mybatisplusdemo.user.repository.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRepository extends BaseMapper<UserEntity> {
    List<UserEntity> selectAll(@Param(Constants.WRAPPER) Wrapper<UserEntity> wrapper);
}
