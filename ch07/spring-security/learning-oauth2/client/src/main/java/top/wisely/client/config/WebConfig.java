package top.wisely.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class WebConfig {

    @Bean
    RestTemplateCustomizer restTemplateCustomizer(OAuth2AuthorizedClientService clientService){
        return restTemplate -> {
            List<ClientHttpRequestInterceptor> interceptors =
                    restTemplate.getInterceptors();
            if (CollectionUtils.isEmpty(interceptors)) {
                interceptors = new ArrayList<>();
            }
            interceptors.add((request, body, execution) -> {
                OAuth2AuthenticationToken auth = (OAuth2AuthenticationToken)
                        SecurityContextHolder.getContext().getAuthentication();
                String clientRegistrationId = auth.getAuthorizedClientRegistrationId();
                String principalName = auth.getName();
                OAuth2AuthorizedClient client =
                        clientService.loadAuthorizedClient(clientRegistrationId, principalName);
                String accessToken = client.getAccessToken().getTokenValue();
                request.getHeaders().add("Authorization", "Bearer " + accessToken);
                return execution.execute(request, body);
            });
            restTemplate.setInterceptors(interceptors);
        };
    }

}
