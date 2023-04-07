package br.com.lucas.Container.Store.security;

import br.com.lucas.Container.Store.service.DetailsClienteServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class JWTconfiguration {
   // @Autowired
   // private  DetailsClienteServiceimpl clienteService;
   // @Autowired
  //  private  PasswordEncoder passwordEncoder;


   // public JWTconfiguration(DetailsClienteServiceimpl clienteService, PasswordEncoder passwordEncoder) {
   //     this.clienteService = clienteService;
  //      this.passwordEncoder = passwordEncoder;
   // }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET,"/parking-spot/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/parking-spot").hasRole("USER")
                .requestMatchers(HttpMethod.DELETE,"/parking-spot/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

    CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
    source.registerCorsConfiguration("/**", corsConfiguration);

    return source;
    }
}
