//package com.xiaoman.openapi.dal.repository;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.xiaoman.openapi.dal.dataobject.User;
//import com.xiaoman.openapi.dal.mappers.UserMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import java.util.Optional;
//
//@Repository
//public class UserRepository {
//    @Autowired
//    private UserMapper userMapper;
//
//    public Optional<User> findByUsername(String username) {
//        return Optional.ofNullable(userMapper.selectOne(new LambdaQueryWrapper<User>()
//                .eq(User::getUsername, username)));
//    }
//
//    public void save(User user) {
//        userMapper.insert(user);
//    }
//}
