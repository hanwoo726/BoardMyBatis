package com.lec.spring.service;

import com.lec.spring.domain.User;
import com.lec.spring.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(SqlSession sqlSession) {
        this.userRepository = sqlSession.getMapper(UserRepository.class);
    }

    @Override
    public List<User> findByUid(int id) {
        return userRepository.findByUid(id);
    }

    @Override
    public Integer findById(int id) {

        return null;
    }

    
    // 로그인 검증 코드
    @Override
    public User findUser(User user) {
        user.setId(0);
        if(user.getUserID() !=null && !user.getUserID().trim().isEmpty()
                && user.getPassword() != null &&
                !user.getPassword().trim().isEmpty()){
        User foundUser =  userRepository.findUser(user);

        if(foundUser != null && foundUser.getPassword().equals(user.getPassword())){
                return foundUser;
                }
        }
        return null;

    }


    
    
    
    
}
