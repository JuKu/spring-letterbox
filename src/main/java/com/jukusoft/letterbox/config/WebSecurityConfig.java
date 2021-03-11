package com.jukusoft.letterbox.config;

import com.jukusoft.authentification.jwt.config.JWTWebSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableWebSecurity
/*@EnableGlobalMethodSecurity(
        prePostEnabled = true)*/
public class WebSecurityConfig extends JWTWebSecurityConfig {

    @Override
    protected String[] listPermittedPages() {
        return new String[]{"test"};
    }

}
