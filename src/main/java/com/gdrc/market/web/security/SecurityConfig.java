package com.gdrc.market.web.security;

import com.gdrc.market.domain.service.MarketUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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

    /*
    Este metodo basicamente le indicamos que no necesita permisos
    para poder entrar a este path.
    1 - Desabilitamos las peticiones cruzadas.
    2 - Autorize todas las peticiones que macheen con el
        path "authenticate".
    3 - Las demas peticiones requeriran autenticacion.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/**/authenticate").permitAll()
                .anyRequest().authenticated();
    }

    /*
    Como en el AuthController utilizamos AuthenticationManager,
    necesitamos indicarle a Spring, por medio de la anotacion
    @Bean, que lo elejimos a el como gestor de autenticacion
    de la aplicacion.
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*
    Se modifico este metodo para permitir el acceso a la documentacion
    de Swagger sin necesidad de token.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs", "/configuration/ui",
                "/swagger-resources/**", "/configuration/security",
                "/swagger-ui.html", "/webjars/**",
                "/swagger-ui/**"
        );
    }
}
