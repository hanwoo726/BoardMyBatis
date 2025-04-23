package com.lec.spring.service;

import com.lec.spring.domain.Comment;
import com.lec.spring.domain.User;
import com.lec.spring.repository.CommentRepository;
import com.lec.spring.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(SqlSession sqlSession) {
        this.commentRepository = sqlSession.getMapper(CommentRepository.class);
        this.userRepository = sqlSession.getMapper(UserRepository.class);
    }

    @Override
    public List<Comment> allComment() {
        return commentRepository.allComment();
    }

    @Override
    public List<Comment> findByBid(int id, int offset, int limit) {
        return commentRepository.findByBid(id, offset, limit);
    }

    @Override
    public int findById(int id) {
        return commentRepository.findById(id);
    }

    @Override
    public Integer writeComment(Comment comment) {

        return commentRepository.writeComment(comment);

    }

    @Override
    public Integer countComment(int id) {
        return commentRepository.countComment(id);
    }
}
