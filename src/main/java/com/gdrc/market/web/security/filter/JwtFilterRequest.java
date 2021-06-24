package com.gdrc.market.web.security.filter;

import com.gdrc.market.domain.service.MarketUserDetailsService;
import com.gdrc.market.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
Esta clase sera como un middleware
que se ejecutara en cada request para verificar si
el request trae el token para permitir el paso
 */
@Component
public class JwtFilterRequest extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private MarketUserDetailsService marketUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Sacamos el header de la request
        String authorizationHeader = request.getHeader("Authorization");
        // verificamos que no sea nulo y si trae en el la palabra Bearer
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            /*Cortamos el string para que no contenga "Bearer "
            * Sino unicamente el token*/
            String jwt = authorizationHeader.substring(7);
            // Extraemos el username del token
            String username = jwtUtil.extractUsername(jwt);

            /*
            Verificamos si se encuentra el usernam
            y Verificamos si en el contexto aun no exista
            una autenticacion para este usuario
             */
            if (
                    username != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null
            ) {
                //Solicitamos el userDetails utilizando nuestro servicio
                UserDetails userDetails = marketUserDetailsService
                        .loadUserByUsername(username);

                // Verificamos si el token es correcto
                if (jwtUtil.validateToken(jwt, userDetails)) {

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
