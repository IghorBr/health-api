package ibn.api.health.core.security.resource_server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class ResourceServerConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(request ->
                        request.requestMatchers("/oauth2/**").authenticated()
                )
                .csrf().disable()
                .cors()
                .and()
                .oauth2ResourceServer().jwt();
//                .jwtAuthenticationConverter(jwtAuthenticationConverter());

        return http.formLogin(Customizer.withDefaults()).build();
    }

//    private JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//
//        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
//            List<String> authorities = jwt.getClaimAsStringList("authorities");
//
//            if (authorities == null) {
//                return Collections.emptyList();
//            }
//
//            JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
//            Collection<GrantedAuthority> grantedAuthorities = authoritiesConverter.convert(jwt);
//
//            grantedAuthorities.addAll(authorities
//                    .stream()
//                    .map(SimpleGrantedAuthority::new)
//                    .collect(Collectors.toList()));
//
//            grantedAuthorities.add(new SimpleGrantedAuthority(jwt.getClaimAsString("role")));
//            grantedAuthorities.add()
//
//            return grantedAuthorities;
//        });
//
//        return converter;
//    }

}