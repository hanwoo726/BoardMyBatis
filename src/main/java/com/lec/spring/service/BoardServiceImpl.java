package com.lec.spring.service;

import com.lec.spring.domain.Board;
import com.lec.spring.domain.User;
import com.lec.spring.repository.BoardRepository;
import com.lec.spring.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    @Autowired
    public BoardServiceImpl(SqlSession sqlSession){
        this.boardRepository = sqlSession.getMapper(BoardRepository.class);
        this.userRepository = sqlSession.getMapper(UserRepository.class);
    }

    @Override
    public List<Board> findAll() {
        return boardRepository.boardList();
    }

    @Override
    public int writeBoard(Board board) {
        return boardRepository.write(board);
    }

    @Override
    public Board findById(int id) {

        return boardRepository.findById(id);
    }

    @Override
    public void viewCnt(int id) {
       boardRepository.incrementView(id);
    }

    @Override
    public int boardDelete(int id) {
        if(id > 0){
            return boardRepository.deleteBoard(id);
        }
        else{
            return 0;
        }
    }

    @Override
    public void updateBoard(int id, String subject, String content) {
        boardRepository.updateBoard(id,subject, content);
    }

    @Override
    public List<User> findAllUsers() {
        return boardRepository.users();
    }

    @Override
    public int findByUserId(int uid) {
        return boardRepository.findByUserId(uid);
    }


}
