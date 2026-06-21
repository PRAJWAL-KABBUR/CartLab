package cartlab.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
                // 1. Enable CORS using the bean below — must come before csrf disable
                .cors()
                .and()

                // 2. Disable CSRF (stateless REST API)
                .csrf().disable()

                // 3. Disable HSTS
                .headers().httpStrictTransportSecurity().disable()
                .and()

                // 4. Stateless session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                // 5. Permit ALL requests — ** matches any number of path segments
                //    /api/*   only matched /api/customers  (one segment)
                //    /api/**  matches /api/customers, /api/products/paginated, /api/orders/1
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().permitAll();
    }

    // 6. CORS config registered as a Spring bean
    //    @CrossOrigin on controllers alone doesn't work when Spring Security
    //    intercepts the OPTIONS preflight before the controller is reached.
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3001", "http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}