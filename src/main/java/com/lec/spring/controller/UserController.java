package com.lec.spring.controller;

import com.lec.spring.domain.User;
import com.lec.spring.service.UserServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
   private UserServiceImpl userService;


    // 로그인 페이지
    @PostMapping("/login")
    public String login(){

        System.out.println("로그인 페이지!");

        return "/user/login";
    }

    @GetMapping("/login")
    public String getLogin(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("userId")) {
                    model.addAttribute("cookieUsername", c.getValue());
                    System.out.println("쿠키의 username 값 출력 : " + c.getValue());
                    break;
                }
            }
        }
        return "/user/login";
    }

    @PostMapping("/loginOk")
    public String loginOk(@ModelAttribute User user,
                          @RequestParam(required = false) String rememberId,
                          HttpSession session,
                          HttpServletResponse response,
                          Model model){


        User findUser = userService.findUser(user);

        if(findUser == null || !findUser.getPassword().equals(user.getPassword())){
//            System.out.println("로그인 실패" + "\n" +
//             "받은 ID 값 : " + findUser.getUserID() + "\n"
//             + "받은 비밀번호 값 : " + findUser.getPassword());
            return "redirect:/user/loginError";
        }

        if("on".equals(rememberId)) {
            Cookie cookie = new Cookie("userId", user.getUserID());
            cookie.setMaxAge(60 * 60 * 24 * 30); // 30일동안 유효하게 설정
            cookie.setPath("/user/login");
            response.addCookie(cookie);

            System.out.println("쿠키 저장 성공 쿠키 값 : " + cookie.getValue());
        }
        else{

            Cookie cookie = new Cookie("userId", null);
            System.out.println("쿠키 값 삭제 삭제된 값 : " + cookie.getValue() );
            cookie.setMaxAge(0);
            cookie.setPath("/user/login");
            response.addCookie(cookie);
        }



        System.out.println("로그인 성공 \n 받은 값 : " + findUser.getUserID() + "\n" +
                "패스워드 : " + findUser.getPassword());
        System.out.println("유저의 ID 값 : " + findUser.getId());

        if(user != null) {
            session.setAttribute("loggedInUser", findUser);
        } else{
                findUser.setId(0);
        }
        return "redirect:/board/list";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loggedInUser");
        return "redirect:/user/login";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model){
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        model.addAttribute("user", loggedInUser);
        return "/user/profile";
    }

    @RequestMapping("/loginError")
    public String loginError(){
        return "/user/loginError";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, Model model){
        session.removeAttribute("loggedInUser");


        return "/user/logout";

    }
}
