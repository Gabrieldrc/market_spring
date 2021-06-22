package com.gdrc.market.web.controller;

import com.gdrc.market.domain.dto.AuthenticationRequest;
import com.gdrc.market.domain.dto.AuthenticationResponse;
import com.gdrc.market.domain.service.MarketUserDetailsService;
import com.gdrc.market.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MarketUserDetailsService marketUserDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    /*
    Para autenticar:
        1 - Con el AuthenticationManager le indicamos que
            autentique con el username y password.
        2 - Como la autenticacion se realizaran con un
            usernam y password, le pasamos por parametro
            UsernamePasswordAuthenticationToken, pasandole
            el username y password
        3 - Necesitamos los UserDetails, asi que inyectamos
            el marketUserDetailsService.
        4 - Una vez inyectados, le solicitamos el userDetails
            utilizando el metodo loadUserVyUsername y le
            pasamos el Username.
        5 - Ahora, necesitamos generar el jwt, y para eso
            inyectamos el jwtUtil.
        6 - Con el jwtUtil generamos el token mandandole
            el userDetail.
        7 - Si la autenticacion es falsa, retorna un
            status forbidden, de lo contrario,
            retorna una response con el jwt y status ok.
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(
            @RequestBody AuthenticationRequest request
    ) {
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(), request.getPassword()
                        )
                );
                UserDetails userDetails = marketUserDetailsService.loadUserByUsername(
                        request.getUsername()
                );
                String jwt = jwtUtil.generateToken(userDetails);
                return new ResponseEntity<>(
                        new AuthenticationResponse(jwt),
                        HttpStatus.OK
                );
            } catch (BadCredentialsException e) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }


    }
}
