package com.lec.spring.service;

import com.lec.spring.domain.User;

import java.util.List;

public interface UserService {

    public List<User> findByUid(int id);
    public Integer findById(int id);

    User findUser(User user);
}
