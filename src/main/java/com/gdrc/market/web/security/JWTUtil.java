package com.gdrc.market.web.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
/*
El metodo generateToken:
1 - Con el builder le incluimos el usuario (Subject).
2 - Le seteamos la fecha en que fue creado el token.
3 - Seteamos la expiracion (en este caso con 10 horas despues).
4 - Firmamos el metodo, indicandole:
    - el tipo de firma (en este caso HS256)
    - la clave de seguridad (en este caso KEY)
5 - Compactamos.
 */
public class JWTUtil {
    private static final String KEY = "enM4rk3T70d03sS3gur0";

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }
}
