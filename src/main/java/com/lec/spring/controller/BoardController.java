package com.lec.spring.controller;

import com.lec.spring.domain.Board;
import com.lec.spring.domain.Comment;
import com.lec.spring.domain.User;
import com.lec.spring.service.BoardService;
import com.lec.spring.service.CommentService;
import com.lec.spring.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    // 보드 컨트롤러 기본 게시글 작성 로직 구성
    @RequestMapping("/list")
    public String BoardList(User user, HttpSession session, Model model){
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            if(loggedInUser == null){
                user.setId(0);
                user.setUserID("GUEST");
                loggedInUser = user;
            }



        List<Board> boards;
        boards = boardService.findAll();

        model.addAttribute("boards", boards);
        model.addAttribute("user", loggedInUser);


        return "board/list";
    }

    @RequestMapping("/write")
    public String BoardWrite(User user, HttpSession session ,Model model){
        User loggedUser = (User) session.getAttribute("loggedInUser");
        if(loggedUser == null){
            user.setId(0);
            user.setUserID("GUEST");
            loggedUser = user;
        }
        model.addAttribute("user", loggedUser);
        return "board/write";
    }

    // 게시판 작성 완료
    @PostMapping("/save")
    public String BoardSave(@ModelAttribute Board board,
                            RedirectAttributes redirectAttributes){

        int result = boardService.writeBoard(board);

        if (result > 0) {
            // 게시글 작성 성공 시, 성공 메시지를 추가하고 목록 페이지로 리디렉션
            redirectAttributes.addFlashAttribute("message", "게시글이 작성되었습니다.");
        } else {
            // 게시글 작성 실패 시, 실패 메시지를 추가하고 작성 페이지로 리디렉션
            redirectAttributes.addFlashAttribute("message", "게시글 작성에 실패했습니다.");
        }


        return "redirect:/board/list";


    }


    // 보드 상세보기 페이지
    @GetMapping("/detail/{id}")
    String BoardDetail(@PathVariable int id,
                        @RequestParam(defaultValue = "1") int page
                        ,Model model,
                       User user,
                       HttpSession session){
        int countComment = commentService.countComment(id);

        int limit = 10;
        int offset = (page - 1) * limit;
        int totalPage = (int) Math.ceil((double) countComment / limit);


        boardService.viewCnt(id);
        Board board = boardService.findById(id);
        List<Comment> comment = commentService.findByBid(id, offset, limit);
        User loggedUser = (User) session.getAttribute("loggedInUser");
        if(loggedUser == null){
            user.setId(0);
            user.setUserID("GUEST");
            loggedUser = user;
        }


        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("countC", countComment);
        model.addAttribute("user", loggedUser);
        model.addAttribute("comment", comment);
        model.addAttribute("detail", board);


        return "board/detail";
    }

    @GetMapping("/update/{id}")
    String BoardUpdate(@PathVariable int id
            ,User user, HttpSession session
            ,Model model){

       User loggedUser =  (User) session.getAttribute("loggedInUser");
        if(loggedUser == null){
            user.setId(0);
            user.setUserID("GUEST");
            loggedUser = user;
        }

        Board board = boardService.findById(id);
        model.addAttribute("user", loggedUser);
        model.addAttribute("update", board);
        return "board/update";
    }

    @RequestMapping("/deleteOk")
    String BoardDelete(@RequestParam int id, Model model){

       int result =  boardService.boardDelete(id);

        System.out.println("result의 값 : " + result);

        return "redirect:/board/list";
    }

    @PostMapping("/updateOk")
    String BoardUpdateOk( @RequestParam int id,
                            @RequestParam String subject,
                            @RequestParam String content
                            , Model model){

        System.out.println("id: " + id + ", subject: " + subject + ", content: " + content);
        boardService.findById(id);
        model.addAttribute("subject", subject);
        model.addAttribute("content", content);
        boardService.updateBoard(id, subject, content);


        return "redirect:/board/list";
    }



    @PostMapping("/writeOk")
    public String commentOk(@RequestParam int id,
            @ModelAttribute Comment comment,
                            RedirectAttributes redirectAttributes
    ,HttpSession session, Model model){


        User loggedUser = (User) session.getAttribute("loggedInUser");
        if(loggedUser != null) {
            comment.setUserId(loggedUser.getId());
            comment.setUser(loggedUser);  // comment 객체에 User 객체를 설정

        } else{
            comment.setUserId(0);
        }

        System.out.println("유저 아이디 값 = " + comment.getUser().getUserID());


        if(comment.getComment() == null || comment.getComment().isEmpty()){
                return "redirect:/board/error";
        }
        else{
            commentService.writeComment(comment);
        return "redirect:/board/detail/" + id;
        }


        }

        @RequestMapping("/error")
        public String errMessage() {

            return "/board/error";
        }









    }


