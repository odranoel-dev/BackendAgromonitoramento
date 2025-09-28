package com.example.agromonitoramento.backendagromonitoramento.jwt;

import jakarta.servlet.http.Cookie;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;

    public JwtService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String gerarToken(String idUsuario, long duracaoSegundos) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("backendAgromonitoramento")
                .subject(idUsuario) // dado único do usuário
                .issuedAt(now)
                .expiresAt(now.plusSeconds(duracaoSegundos))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public Cookie gerarCookie(String token, long duracaoSegundos) {
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // true em produção (HTTPS)
        cookie.setPath("/");
        cookie.setMaxAge((int) duracaoSegundos);
        return cookie;
    }
}
