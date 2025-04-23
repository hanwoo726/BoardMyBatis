package com.lec.spring.repository;

import com.lec.spring.domain.User;

import java.util.List;

public interface UserRepository {

    // 전체 유저 수 보기
    List<User> users();

    public List<User> findByUid(int id);

    public Integer findById(int id);

    User findUser(User user);
}
