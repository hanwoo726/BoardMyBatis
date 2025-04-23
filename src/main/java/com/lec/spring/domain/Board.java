package com.lec.spring.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    private int id;
    private String name;
    private String subject;
    private String content;
    private int view;
    private LocalDateTime regdate;
    
    // 유저의 Id값 외래 키
    private int userId;

    private User user;
    private List<Comment> comments;








}
