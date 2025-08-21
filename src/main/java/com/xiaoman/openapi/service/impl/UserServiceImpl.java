//package com.xiaoman.openapi.service.impl;
//
//import com.xiaoman.openapi.dal.dataobject.User;
//import com.xiaoman.openapi.dal.repository.UserRepository;
//import com.xiaoman.openapi.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.UUID;
//
//@Service
//public class UserServiceImpl implements UserService {
//    @Autowired
//    private UserRepository userRepository;
//
////    public User registerUser(String username, String password) throws Exception {
////        // 检查用户名是否已存在
////        if (userRepository.findByUsername(username).isPresent()) {
////            throw new Exception("用户名已存在");
////        }
////
////        User user = new User();
////        user.setUsername(username);
////        user.setPassword(password);
////        user.setAccessKey(generateAccessKey());
////        user.setSecretKey(generateSecretKey());
////        user.setActive(true);
////        userRepository.save(user);
////    }
//
//    private String generateAccessKey() {
//        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);
//    }
//
//    private String generateSecretKey() {
//        return UUID.randomUUID().toString().replace("-", "").substring(0, 32);
//    }
//
//    @Override
//    public User registerUser(String username, String password) throws Exception {
//        return null;
//    }
//}
