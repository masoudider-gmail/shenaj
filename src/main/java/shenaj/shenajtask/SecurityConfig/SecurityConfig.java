package shenaj.shenajtask.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.Filter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private PasswordEncoder encoder;


    @Autowired
    @Qualifier("userDetailServiceImp")
    private UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder managerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        managerBuilder.authenticationProvider(provider());
        AuthenticationManager authenticationManager = managerBuilder.build();

        http
                .csrf().disable()
                .authorizeRequests(
                        auth -> auth
                                .antMatchers("/register", "/login")
                                .permitAll()
                                .antMatchers("/users-management")
                                .hasAnyAuthority("admin")
                                .antMatchers("/ticket-management")
                                .hasAnyAuthority("admin", "support")
                                .anyRequest()
                                .authenticated()
                ).authenticationManager(authenticationManager)
                .addFilter(new JwtConfig(authenticationManager))
                .addFilterAfter(new JwtVerifier(), JwtConfig.class);


        return http.build();
    }


    @Bean
    public DaoAuthenticationProvider provider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

}