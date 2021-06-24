package com.gdrc.market.domain.service;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
/*
Si ejecutamos la aplicacion sin este servicio,
desde la terminal se creara una contraseña con
el nombre de usuario "user", pero gracias a este
servicio, podemos crear nuestro usuario.
El constructor de User recibe:
- El nombre de usuario,
- La contraseña que se le coloca el {noop} para que
funcione mientras no la pasamos por un encriptador.
- Un array (actualmente vacio) que es donde iran los
tipos de roles que tendra el usuario.
NOTA:
    Lo que deberia ocurrir en el metodo
    loadUserByUsername, es que deberia ir a la base
    de datos o contra un sistema como auth0 para que
    verifique el correcto inicio de sesion antes de
    utilizar nuestros servicios.
 */
@Service
public class MarketUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("admin", "{noop}marketPassword", new ArrayList<>());
    }
}
