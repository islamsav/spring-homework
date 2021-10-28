package com.example.MyBookShopApp.config.security;

import com.example.MyBookShopApp.entity.token.JWTBlackListEntity;
import com.example.MyBookShopApp.service.JWTBlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class LogoutHandler implements LogoutSuccessHandler {

    private final JWTBlackListService jwtBlackListService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException {
        String token = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
            }
        }
        if (Objects.nonNull(token) && Objects.isNull(jwtBlackListService.getByToken(token))) {
            JWTBlackListEntity jwtBlacklist = new JWTBlackListEntity();
            jwtBlacklist.setToken(token);
            jwtBlackListService.saveToken(jwtBlacklist);

        }
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, authentication);

        response.sendRedirect("/");
    }
}
