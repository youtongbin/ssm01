package com.neuedu.web;

import com.neuedu.pojo.User;
import com.neuedu.util.CookieUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class TestMethod implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        User user = (User)session.getAttribute("user");

        if (user != null){
            Cookie[] cookies = httpServletRequest.getCookies();
            Map<String,Cookie> map = CookieUtil.getCookie(cookies);
            Cookie cookie1 = map.get("username");
            Cookie cookie2 = map.get("password");
            if (user.getUsername().equals(cookie1.getValue()) && user.getPassword().equals(cookie2.getValue())){
                return true;
            }else {
                httpServletResponse.sendRedirect("login.do");
            }
        }else {
            httpServletResponse.sendRedirect("login.do");
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
