package ibn.api.health.core.security.auth_server;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import ibn.api.health.domain.model.Usuario;
import ibn.api.health.domain.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class AuthorizationServerConfiguration {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authServerfilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        return http.formLogin(Customizer.withDefaults()).build();
    }

    @Bean
    public AuthorizationServerSettings providerSettings() {
        return AuthorizationServerSettings.builder()
                .issuer("http://localhost:8080")
                .build();
    }

    @Bean
    public RegisteredClientRepository clientRepository(PasswordEncoder passwordEncoder) {
        RegisteredClient webClient = RegisteredClient
                .withId("1")
                .clientId("health-client")
                .clientSecret(passwordEncoder.encode("123"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .scope("READ")
                .scope("WRITE")
                .tokenSettings(
                        TokenSettings.builder()
                                .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
                                .accessTokenTimeToLive(Duration.ofMinutes(30))
                                .build()
                )
                .redirectUri("http://127.0.0.1:8080/authorized")
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(false)
                        .build())
                .build()
                ;


        return new InMemoryRegisteredClientRepository(Arrays.asList(webClient));
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, JOSEException {
        var password = "123456".toCharArray();
        var alias = "health";

        Resource location = new ClassPathResource("keystore/health_key.jks");
        InputStream inputStream = location.getInputStream();
        KeyStore keyStore = KeyStore.getInstance("JKS");

        keyStore.load(inputStream, password);
        RSAKey rsaKey = RSAKey.load(keyStore, alias, password);

        return new ImmutableJWKSet<>(new JWKSet(rsaKey));
    }

//    @Bean
//    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer(UsuarioRepository usuarioRepository) {
//        return context -> {
//            Authentication authentication = context.getPrincipal();
//            if (authentication.getPrincipal() instanceof User) {
//                User user = (User) authentication.getPrincipal();
//
//                Usuario usuario = usuarioRepository.findByEmail(user.getUsername()).orElseThrow();
//
//                Set<String> authorities = new HashSet<>();
//                for (GrantedAuthority authority : user.getAuthorities()) {
//                    authorities.add(authority.getAuthority());
//                }
//
//                context.getClaims().claim("usuario_id", usuario.getId().toString());
//                context.getClaims().claim("role", usuario.getRole().getDescricao());
//                context.getClaims().claim("code", usuario.getCode());
//                context.getClaims().claim("authorities", authorities);
//            }
//        };
//    }

}
