package com.example.lab12.Security;
import com.example.lab12.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {
    private final MyUserDetailsService myUserDetailsService;


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()

                .requestMatchers("/api/v1/auth/register","/api/v1/auth/login").permitAll()
                .requestMatchers("/api/v1/auth/update").hasAnyAuthority("CUSTOMER")
                .requestMatchers("/api/v1/auth/get","/api/v1/auth/delete" ).hasAnyAuthority("ADMIN")

                .requestMatchers("/api/v1/blog/add" , "/api/v1/blog/get" , "/api/v1/blog/delete","/api/v1/blog/update", "/api/v1/blog/getuser").hasAnyAuthority("CUSTOMER")
                .requestMatchers( "/api/v1/blog/getid" , "/api/v1/blog/gettitle").hasAnyAuthority("ADMIN")

                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return httpSecurity.build();
    }
}