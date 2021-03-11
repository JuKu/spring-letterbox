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
        return new String[]{"/", "/swagger", "/swagger-ui", "/swagger-ui.html", "/h2/**", "/swagger/**", "/swagger-*", "/swagger-ui/**", "/swagger-resources/**", "/csrf", "/v2/**", "/webjars/**", "/actuator", "/actuator/*", "/errors/*", "/error", "/pages/*", "/res/**"};
    }

}
