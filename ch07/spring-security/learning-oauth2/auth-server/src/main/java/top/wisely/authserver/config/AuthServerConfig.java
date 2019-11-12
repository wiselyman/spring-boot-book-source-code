package top.wisely.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;


import javax.sql.DataSource;

@EnableAuthorizationServer
@Configuration
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final DataSource dataSource;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final TokenStore tokenStore;
    private final AccessTokenConverter accessTokenConverter;
    private final AuthorizationServerProperties properties;

    public AuthServerConfig(PasswordEncoder passwordEncoder,
                            DataSource dataSource,
                            AuthenticationManager authenticationManager,
                            UserDetailsService userDetailsService,
                            TokenStore tokenStore,
                            AccessTokenConverter accessTokenConverter,
                            AuthorizationServerProperties properties) {
        this.passwordEncoder = passwordEncoder;
        this.dataSource = dataSource;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenStore = tokenStore;
        this.accessTokenConverter = accessTokenConverter;
        this.properties = properties;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
            .jdbc(this.dataSource)
            .passwordEncoder(passwordEncoder);
//
//        JdbcClientDetailsService detailsService = new JdbcClientDetailsService(this.dataSource);
//        detailsService.setPasswordEncoder(passwordEncoder);
//        clients.withClientDetails(detailsService);

//    clients
//        .inMemory()
//            .withClient("postman")
//            .secret(passwordEncoder.encode("postman"))
//            .scopes("any")
//            .authorizedGrantTypes("password", "authorization_code", "refresh_token")
//        .and()
//            .withClient("app")
//            .secret(passwordEncoder.encode("app"))
//            .scopes("any")
//            .authorizedGrantTypes("password", "authorization_code", "refresh_token");
}

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.accessTokenConverter(this.accessTokenConverter);
            endpoints.tokenStore(this.tokenStore);
            endpoints.authenticationManager(this.authenticationManager);
            endpoints.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            security.checkTokenAccess(this.properties.getCheckTokenAccess());
            security.tokenKeyAccess(this.properties.getTokenKeyAccess());
            security.realm(this.properties.getRealm());
    }
}

