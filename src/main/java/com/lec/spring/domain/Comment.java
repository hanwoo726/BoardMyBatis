package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    private Integer commentId;
    private String comment;
    private LocalDateTime regdate;

    // 게시글 Id값 외래 키
    private int id;
    // 유저의 아이디 외래 키
    private int userId;

    User user;
    Board board;
}
