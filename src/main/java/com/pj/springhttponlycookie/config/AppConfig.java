package com.pj.springhttponlycookie.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.security.web.firewall.RequestRejectedHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

/**
 * Core application config class that defines required beans for application
 *
 * @author Pavan Kumar Jadda
 * @since 1.0.0
 */
@Configuration
@EnableScheduling
@EnableAsync
public class AppConfig
{


  /**
   * Allows // in HTTP requests
   *
   * @return HttpStatusRequestRejectedHandler bean
   */
  @Bean
  RequestRejectedHandler requestRejectedHandler()
  {
    return new HttpStatusRequestRejectedHandler();
  }


  /**
   * Create ModelMapper bean
   *
   * @return ModelMapper bean
   *
   * @author Pavan Kumar Jadda
   * @since 1.0.0
   */
  @Bean
  public ModelMapper modelMapper()
  {
    return new ModelMapper();
  }


  /**
   * Create HttpSessionEventPublisher bean
   *
   * @return HttpSessionEventPublisher bean
   *
   * @author Pavan Kumar Jadda
   * @since 1.0.0
   */
  @Bean
  public HttpSessionEventPublisher httpSessionEventPublisher()
  {
    return new HttpSessionEventPublisher();
  }

  /**
   * Create HttpSessionIdResolver bean and sets X-Auth-Token as Session Token
   *
   * @return HttpSessionIdResolver bean
   *
   * @author Pavan Kumar Jadda
   * @since 1.0.0
   */
  @Bean
  public HttpSessionIdResolver httpSessionIdResolver()
  {
    return HeaderHttpSessionIdResolver.xAuthToken();
  }
}
