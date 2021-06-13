package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Frontend pages
        registry.addViewController(Endpoints.Site.INDEX).setViewName(ViewNames.INDEX);
        registry.addViewController(Endpoints.Site.SLASH_INDEX).setViewName(ViewNames.INDEX);
        registry.addViewController(Endpoints.Site.ALL_LISTS).setViewName(ViewNames.ALL_LISTS);
        registry.addViewController(Endpoints.Site.LIST).setViewName(ViewNames.LIST);
        registry.addViewController(Endpoints.Site.LIST).setViewName(ViewNames.POST_LIST);
        registry.addViewController(Endpoints.Site.SINGLE_LIST).setViewName(ViewNames.SINGLE_LIST);
        registry.addViewController(Endpoints.Site.ABOUT).setViewName(ViewNames.ABOUT);
    }

    @Bean
    LocaleResolver localeResolver() {
        return new FixedLocaleResolver(Locale.ENGLISH);
    }
}
