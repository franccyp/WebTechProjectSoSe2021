package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

import java.net.URI;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;

    OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        successHandler.setPostLogoutRedirectUri(URI.create("http://localhost:8080/"));
        return successHandler;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // allow anonymous access to the root page
                .antMatchers(
                        HttpMethod.GET,
                        Endpoints.Site.INDEX,
                        Endpoints.Site.SLASH_INDEX,
                        Endpoints.Site.ABOUT
                ).permitAll()

                // static resources
                .antMatchers(
                        "/css/**",
                        "/pictures/**",
                        "/js/**"
                ).permitAll()

                // all other requests
                .anyRequest().authenticated()

                // After we logout, redirect to root page, by default Spring will send you to /login?logout
                .and().logout().logoutSuccessUrl("/?logout")

                // RP-initiated logout
                .and().logout().logoutSuccessHandler(oidcLogoutSuccessHandler())

                // enable OAuth2/OIDC
                .and().oauth2Client().and().oauth2Login()

                .and().csrf().disable();

    }
}
