package com.room.yeahnolja.config;

import com.room.yeahnolja.security.JwtAuthenticationFilter;
import com.room.yeahnolja.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider();
        //JwtTokenProvider클래스에서 private final, @RequiredArgsConstructor 써서 생성자 주입 방법을 써서 이게 등록이 안되는건가?
        //근데 이걸 등록하는 것 자체는 JwtTokenProvider도 다른 클래스에서 객체로 활용될 수 있도록 하기 위한 작업인데 왜 안되는 것일까?
        //그냥 JwtTokenProvider 클래스에서 @Autowired로 설정하고 필드 주입 방식을 쓰면 해결이 되는 것 같은데 왜 그러지? 생성자 주입 방식은 안되는 건가?
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/", "/join", "/login").permitAll()
                .antMatchers("/hotels/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()
                .csrf().disable()
                .logout()
                .permitAll()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider()), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

