package com.lec.spring.service;

import com.lec.spring.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> allComment();

    List<Comment> findByBid(int id, int offset, int limit);

    public int findById(int id);

    public Integer writeComment(Comment comment);

    public Integer countComment(int id);
}
