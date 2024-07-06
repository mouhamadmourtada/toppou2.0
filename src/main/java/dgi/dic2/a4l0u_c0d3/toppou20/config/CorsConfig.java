package dgi.dic2.a4l0u_c0d3.toppou20.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Configuration des CORS
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:5173"); // Origine locale
        config.addAllowedOrigin("https://toppou-front.vercel.app"); // Origine de production
        config.addAllowedOriginPattern("*"); // Permet toutes les origines.
        config.addAllowedHeader("*"); // Permet tous les headers.
        config.addAllowedMethod("*"); // Permet toutes les méthodes HTTP (GET, POST, PUT, DELETE, etc.).

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
