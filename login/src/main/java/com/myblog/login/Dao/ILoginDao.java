package com.myblog.login.Dao;

import com.myblog.login.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ILoginDao {

     void register(User user);

    User getUserByName(@Param("name") String name);
}
