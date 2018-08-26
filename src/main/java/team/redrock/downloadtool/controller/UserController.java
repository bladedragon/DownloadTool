package team.redrock.downloadtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import team.redrock.downloadtool.service.UserService;
import team.redrock.downloadtool.utils.Response;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/register")
    public Response userRegister(@RequestParam("username")String username,
                                 @RequestParam("password")String password){

        return userService.userRegister(username,password);
    }

    @PostMapping(value = "/login")
    public Response userLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              HttpServletRequest request){

        return userService.userLogin(username,password,request);
    }
@GetMapping(value = "/exit")
    public Response userLogoff(HttpServletRequest request, HttpServletResponse response){
        HttpSession session =request.getSession(false);
            session.invalidate();

    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
        return new Response("0","退出登录成功");
}
//    @GetMapping
//    public String messageForm(Model model,HttpServletRequest request) {
//
//        model.addAttribute("username",request.getSession().getAttribute("user_session"));               //必须加这个，使得post传入的数据有一个容器
////        model.addAttribute("FileInfList",redisService.readMsg());
//
//        return "msgboard";
//    }
//    @GetMapping("/login")
//    public void toLogin(){
//
//    }
//    @GetMapping("/register")
//    public void toRegister(){
//    }

}
