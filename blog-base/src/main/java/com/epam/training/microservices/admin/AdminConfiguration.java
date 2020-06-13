package com.epam.training.microservices.admin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class AdminConfiguration {
  @Bean
  public ViewResolver viewResolver(ISpringTemplateEngine templateEngine,
      ApplicationContext applicationContext) {
    final ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setTemplateEngine(templateEngine);
    viewResolver.setApplicationContext(applicationContext);
    return viewResolver;
  }

  @Bean
  public ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
    final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);
    return templateEngine;
  }

  @Bean
  public ITemplateResolver templateResolver() {
    final SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
    templateResolver.setSuffix(".html");
    templateResolver.setPrefix("/templates/");
    templateResolver.setCheckExistence(true);
    return templateResolver;
  }
}
