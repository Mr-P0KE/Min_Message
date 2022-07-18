package com.poke.Config;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

public class UserInterceptor implements HandlerInterceptor {
    private RedisTemplate template;

    public UserInterceptor(StringRedisTemplate template) {
        this.template = template;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        System.out.println("============");
        System.out.println(uri);
        System.out.println("============");
        HttpSession session = request.getSession();
        String s = (String)session.getAttribute("email");
        System.out.println(s);
        Boolean flag = this.template.hasKey("login:user" + s);
        if (flag) {
            return true;
        } else {
            response.sendRedirect("/login");
            return false;
        }
    }
}
