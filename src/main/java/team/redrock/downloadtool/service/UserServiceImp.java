package team.redrock.downloadtool.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import team.redrock.downloadtool.entity.User;
import team.redrock.downloadtool.jpa.UserJPA;
import team.redrock.downloadtool.utils.Response;
import team.redrock.downloadtool.utils.Utility;

import javax.servlet.http.HttpServletRequest;



@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserJPA userJPA;

    @Override
    public Response userRegister(String username,String password){
        if(Utility.IsNull(username)){
            Response response = new Response("-1","用户名不能为空!");
            return response;
        }
        if(Utility.IsNull(password)){
            Response response = new Response("-1","密码不能为空！");
            return response;
        }
        if(userJPA.findByUserName(username)!=null)
        {
            return new Response("-1","用户名已存在！");
        }
        User user = new User();
        user.setUsername(username);
//        String encodedpassword = Encoding.getMD5(password);
        user.setPassword(password);
        user.setIsPrime(0);
        userJPA.save(user);
        return new Response("0", JSON.toJSONString(user));

    }

    @Override
    public Response userLogin(String username, String password, HttpServletRequest request) {

        if (Utility.IsNull(username)) {
            return new Response("-1", "用户名不能为空");
        }
        if (Utility.IsNull((password))) {
            return new Response("-1", "密码不能为空");
        }
        User user = userJPA.findByUserName(username);
        if (user == null) {
            return new Response("-1", "用户名不存在");
        } else {
            if (!user.getPassword().equals(password)) {
                return new Response("-1", "密码错误");
            } else {
//                if (request.getSession().getAttribute("user").equals(username)) {
//                    System.out.println("得到sessionl");
//                    return new Response("-1", "用户已登录");
//                } else {
                    request.getSession().setAttribute("user", user.getUsername());
                    String user1 = (String) request.getSession().getAttribute("user");
                    System.out.println("session为" + user1);

//                System.out.println("当前用户登录"+request.getSession().getAttribute("user_session"));
                    return new Response("0", JSON.toJSONString(user));

            }

        }


//    public Response sessionController(HttpServletRequest request){
//        HttpSession session = request.getSession();
//        if(session==null){
//            return  new Response("1","当前用户尚未登录，请先登录");
//        }
//        else{
//            return new Response("0",session.getAttribute("user"));
//        }
//    }


    }
}
