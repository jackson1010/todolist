package com.webapp.myfirstwebapp;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * This configuration class sets up the Thymeleaf View Resolver to map ".html"
 * files to HTML templates.
 * It also configures the template engine to use the Spring Resource Template
 * Resolver to locate the templates.
 */

public class WebConfig implements WebMvcConfigurer {
    /**
     * Configures the view resolver to use Thymeleaf and sets the character encoding
     * to "UTF-8".
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }

    /**
     * Bean for configuring the Spring Template Engine with a template resolver.
     * Sets up a Spring Template Engine and sets the template resolver. return
     * SpringTemplateEngine object configured with a template resolver.
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    /**
     * Template resolver bean for resolving templates from the classpath.
     * Sets up a Spring Resource Template Resolver with a prefix and suffix for
     * locating templates
     * and a template mode of HTML.return ITemplateResolver object configured with a
     * prefix, suffix, and template mode.
     */

    private ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }
}
