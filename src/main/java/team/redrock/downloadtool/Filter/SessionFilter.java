package team.redrock.downloadtool.Filter;

import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "sessionFilter",urlPatterns = {"/*"})
public class SessionFilter implements Filter {


    String[] includeUrls = new String[]{"/user/","/user/index.html","/user/register.html","/user/homepage.html","/user/folderdisplay.html","/user/css/bootstrap.min.css",
    "/user/css/style.css","/user/js/util.js","/user/js/bootstrap.min.js","/user/js/jquery.min.js","/user/list","/user/rootlist","/user/img/logo.png",
    "/user/login","/user/register","/user/vediodisplay.html","/user/audiodisplay.html","/user/textdisplay.html","/user/vedio","/user/audio","/user/text"};

    public boolean isNeedFilter(String uri) {

        for (String includeUrl : includeUrls) {
            if(includeUrl.equals(uri)) {
                return false;
            }
        }

        return true;
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletrequest;
        HttpServletResponse response = (HttpServletResponse) servletresponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        System.out.println("filter url:"+uri);
        //是否需要过滤
        boolean needFilter = isNeedFilter(uri);

        if(!needFilter){
            chain.doFilter(servletrequest, servletresponse);
        }else{
            if(session != null&&session.getAttribute("user")!=null){
                chain.doFilter(servletrequest,servletresponse);
                System.out.println("过滤器中的session"+session.getAttribute("user"));
            }else{
            String requestType = request.getHeader("X-Requested-With");
            if(requestType!=null&&"XMLHttpRequest".equals(requestType)){
                response.getWriter().write(JSON.toJSONString("{'status':'-1'},{'content':'你尚未登录'}"));
//                chain.doFilter(servletrequest,servletresponse);
            }else{
                response.sendRedirect("/user");
            }
        }
        }
    }

    @Override
    public void destroy() {

    }
}
