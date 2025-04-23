package com.lec.spring.service;

import com.lec.spring.domain.Board;
import com.lec.spring.domain.User;

import java.util.List;

public interface BoardService {

    // 게시판의 전체 글 리스트 보기
    List<Board> findAll();

    int writeBoard(Board board);

    Board findById(int id);


    // 상세보기 클릭 시 조회수 1증가 로직
    public void viewCnt(int id);

    int boardDelete(int id);

    public void updateBoard(int id, String subject, String content);

    List<User> findAllUsers();

    // 유저의 아이디 인식
    public int findByUserId(int uid);




}
