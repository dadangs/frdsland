package com.hadepro.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hadepro.developer.controller.LocalDB;
import com.hadepro.developer.model.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Resource(name="localDB")
    private LocalDB localDB;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if(user == null){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = auth.getName();
           /* user = userService.findByEmail(email);
            session.setAttribute("user", user);*/
            user = new User();
            user.setName(email);
            session.setAttribute("user", user);
        }

        return super.preHandle(request, response, handler);
    }
}
