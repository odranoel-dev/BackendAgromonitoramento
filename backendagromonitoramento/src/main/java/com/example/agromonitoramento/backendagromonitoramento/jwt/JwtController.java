package com.example.agromonitoramento.backendagromonitoramento.jwt;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logout")
@Tag(name="Logout")
public class JwtController {

    @PostMapping("/")
    public ResponseEntity<Void>logout(HttpServletResponse response){

        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // true em produção (HTTPS)
        cookie.setPath("/");
        cookie.setMaxAge(0); // expira token

        response.addCookie(cookie);

        return ResponseEntity.noContent().build();
    }
}
