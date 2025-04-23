package com.lec.spring.repository;

import com.lec.spring.domain.Board;
import com.lec.spring.domain.User;

import java.util.List;

public interface BoardRepository {

    // 기능 구현

    // 게시판의 전체 리스트 출력
    public List<Board> boardList();

    // 보드 게시글 작성(INSERT)
    int write(Board board);

    // 게시글 id 값을 기준으로 해당 게시글 상세보기 로직
    public Board findById(int id);


    // 게시글 리스트에서 상세보기를 클릭 하면 조회수가 1이 증가 하는 로직
    public void incrementView(int id);

    int deleteBoard(int id);

    public void updateBoard(int id, String subject, String content);

    // userId로 조인 해서 user 정보 보기
    List<User> users();

    int findByUserId(int uid);


}
