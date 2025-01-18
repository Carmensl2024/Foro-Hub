package com.aluracursos.FORO_HUB.security;

import com.aluracursos.FORO_HUB.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret.key}")
    private String secretKey; // Reemplazar con una clave más segura

    @Value("${jwt.token.expiration}")
    private long validityInHours; // 1 hora

    public String generateToken(User user) {
        try{
            Algorithm algorithm=Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withSubject(user.getUsername())
                    .withClaim("id",user.getId())
                    .withExpiresAt(LocalDateTime.now().plusHours(validityInHours).toInstant(ZoneOffset.of("-05:00")))
                    .sign(algorithm);


        }catch (JWTCreationException exception){
            throw new RuntimeException("Error creando token");
        }
    }

    public Boolean validateToken(String token){
        //Validar token
        return true;
    }

    public String getUser(String token){
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm=Algorithm.HMAC256(secretKey);
            JWTVerifier verifier= JWT.require(algorithm)
                    .build();
            return verifier.verify(token).getSubject();
        }catch (JWTVerificationException exception){
            throw new RuntimeException("Token invalido");
        }
    }
}
