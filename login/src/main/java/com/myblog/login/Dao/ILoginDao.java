package com.myblog.login.Dao;

import com.myblog.login.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ILoginDao {

     void register(User user);

    User getUserByName(@Param("name") String name);

    List<User> listUsers(@Param("username") String username, @Param("inuse") Boolean inuse);

    User getUserById(@Param("id") Integer id);

    int createUser(User user);

    int updateUser(User user);

    int deleteUser(@Param("id") Integer id);
}
