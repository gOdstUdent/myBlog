package com.myblog.login.Dao;

import com.myblog.login.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ILoginDao {

     void register(User user);
}
