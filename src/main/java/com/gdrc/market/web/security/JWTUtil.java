package com.gdrc.market.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.swing.*;
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
@Component
public class JWTUtil {
    private static final SecretKey KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Genera un token con el userDetail, la fecha actual, con una expiracion
     * de 10 horas.
     * @param userDetails el detalle del usuario guardado en la session
     * @return token String del token generado
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(KEY)
                .compact();
    }

    /**
     * Valida el token.
     * Verifica si el usuario guardado es igual al usuario del token y si el
     * token NO ha expirado
     * @param token el String del token de seguridad
     * @param userDetails los datos del usuario guardado en session
     * @return boolean
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        return userDetails.getUsername().equals(extractUsername(token))
                && !isTokenExpired(token);
    }

    /**
     * Extrae el username del token (El username es representado por el subject)
     * @param token el String del token de seguridad
     * @return String username
     */
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * Verifica si expiro el token (Si la fecha de expiracion es paso la
     * fecha actual)
     * @param token el String del token de seguridad
     * @return boolean true si expiró y false en caso contrario
     */
    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration()
                .before(new Date());
    }

    /**
     * Optiene el Claim (Claims son los objetos de jwt) del token
     * @param token el String del token de seguridad
     * @return Claims el objeto del token
     */
    private Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
