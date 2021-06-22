package com.gdrc.market.web.security;

import com.gdrc.market.domain.service.MarketUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/*
@EnableWebSecurity:
    Anotacion que indica que esta clase se
    encargara de la seguridad.
Sobreescribimos el metodo configure que recibe
un AuthenticationManagerBuilder, y es alli donde
indicaremos que el usuario y contraseña con el
que queremos acceder es el del servicio que creamos.
Por lo tanto, inyectamos el servicio, y al llamar el
metodo userDetailsService de auth, le pasamos nuestro
servicio.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MarketUserDetailsService marketUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(marketUserDetailsService);
    }
}
