package ra.md05hl.sercurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ra.md05hl.sercurity.jwt.JwtAuthTokenFilter;
import ra.md05hl.sercurity.principal.UserDetailsServiceCustom;

@Configuration
@EnableMethodSecurity(securedEnabled = true)// cấu hình phân quyền trên phương thức
public class SecurityConfig { // cấu hình bảo mật
    @Autowired
    private JwtAuthTokenFilter jwtAuthTokenFilter;
    @Autowired
    private UserDetailsServiceCustom userDetailsServiceCustom;
    @Bean
    public PasswordEncoder passwordEncoder(){ // mã hóa mật khẩu
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsServiceCustom);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // phi trạng thái
                .exceptionHandling(ex->ex.accessDeniedHandler(new DeniedHandler()))
                .authorizeHttpRequests( // phân quyền theo hướng dẫn
                        auth -> auth.requestMatchers("/api/v1/public/**").permitAll() //không cần xác thực
                                .requestMatchers("/api/v1/user/**").hasAuthority("ROLE_USER") // chỉ có User mới có quyền truy cập
                                .requestMatchers("/api/v1/admin/**").hasAuthority("ROLE_ADMIN") // chỉ có Admin mới có quyền truy cập
                                .anyRequest().authenticated()
                );
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

