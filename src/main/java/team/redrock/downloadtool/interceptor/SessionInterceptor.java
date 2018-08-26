package team.redrock.downloadtool.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import team.redrock.downloadtool.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class SessionInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
            User user;
        HttpSession session = request.getSession(false);
//
        return true;
//        if (session != null && session.getAttribute("user") != null) {
//            return true;
//        } else {
//            System.out.println(request.getRequestURI());
//            response.sendRedirect("user/index.html");
//            return false;
//        }
//--------------------------------------------------------------------------------------------------------

        //        System.out.println("我在拦截");
//            Object obj = request.getSession().getAttribute("user_session");
//            if(obj ==null)
//            {
//
//                return false;
//            }
        //----------------------------------------------------------------------
//                if(request.getRequestURI().equals("/user/downloading.html")){
//
//                        response.sendRedirect("upload");
//                            return false;
//                    }
//
//                    if(request.getRequestURI().equals("/user/download")){
//                        return true;
//        }

    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
    }


    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
    }
}
