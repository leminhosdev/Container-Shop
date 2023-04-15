package br.com.lucas.Container.Store.Config;

import br.com.lucas.Container.Store.entity.ClienteUserDetails;
import br.com.lucas.Container.Store.service.ClienteUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebConfigProject {
    @Autowired
    private ClienteUserDetailsServiceImpl clienteUserDetailsService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeHttpRequests().
                requestMatchers("/templates/register/**").permitAll()
                .requestMatchers("/templates/login/**").permitAll()
                .requestMatchers("/templates/home/**").permitAll()
                .requestMatchers("/inicio").permitAll()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/login").permitAll()
                .anyRequest().authenticated().and().
                formLogin().loginPage("/login").defaultSuccessUrl("/home").permitAll();

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
