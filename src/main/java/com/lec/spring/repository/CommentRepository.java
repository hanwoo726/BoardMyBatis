package com.lec.spring.repository;

import com.lec.spring.domain.Comment;

import java.util.List;

public interface CommentRepository {
    // 해당 게시글에 댓글 전체 보기
    List<Comment> allComment();

    // 게시글 댓글 작성
    public int writeComment(Comment comment);

    List<Comment> findByBid(int id, int offset, int limit);

    public Integer findById(int id);

    public Integer countComment(int id);

}
