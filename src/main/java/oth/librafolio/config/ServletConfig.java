package oth.librafolio.config;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import oth.CrptApi;

import java.util.concurrent.TimeUnit;

/**
 * @author Ontheheavens
 * @since 12.05.2024
 */

@Configuration
public class ServletConfig {

    private final CrptApi crptApi = new CrptApi(TimeUnit.MINUTES, 10);

    @Bean
    public ServletRegistrationBean<CrptApi.CrptApiServlet> crptApiServletRegistrationBean() {
        return new ServletRegistrationBean<>(crptApi.getServlet(), CrptApi.CRPT_API_PATH);
    }

    @Bean
    public FilterRegistrationBean<Filter> rateLimiterFilterRegistrationBean() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(crptApi.getRequestFilter());
        registrationBean.addUrlPatterns(CrptApi.CRPT_API_PATH);
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
