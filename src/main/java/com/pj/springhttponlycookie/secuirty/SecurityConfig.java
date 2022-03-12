package com.pj.springhttponlycookie.secuirty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;


/**
 * Core security config class of the project
 *
 * @author Pavan Kumar Jadda
 * @since 1.0.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
  private final DbUserDetailsService dbUserDetailsService;

  public SecurityConfig(DbUserDetailsService dbUserDetailsService)
  {
    this.dbUserDetailsService = dbUserDetailsService;
  }

  /**
   * Get custom DaoAuthenticationProvider bean and set the userDetailsService
   *
   * @author Pavan Kumar Jadda
   * @since 1.0.0
   */
  @Bean
  public DaoAuthenticationProvider getDaoAuthenticationProvider()
  {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(dbUserDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  /**
   * CORS filter to accept incoming requests
   *
   * @author Pavan Kumar Jadda
   * @since 1.0.0
   */
  @Bean
  CorsConfigurationSource corsConfigurationSource()
  {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.applyPermitDefaultValues();
    configuration.setAllowedMethods(Collections.singletonList("*"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  /**
   * Bcrypt PasswordEncoder with strength 12
   *
   * @author Pavan Kumar Jadda
   * @since 1.0.0
   */
  @Bean
  public PasswordEncoder passwordEncoder()
  {
    return new BCryptPasswordEncoder(12);
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth)
  {
    auth.authenticationProvider(getDaoAuthenticationProvider()).eraseCredentials(true);
  }

  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception
  {
    return super.authenticationManagerBean();
  }

  @Override
  public void configure(WebSecurity webSecurity)
  {
    webSecurity.ignoring().antMatchers("/static/**");
  }

  @Override
  public void configure(HttpSecurity http) throws Exception
  {
    http.authorizeRequests()
      .antMatchers("/", "/login", "/api/v1/actuator/**", "/api/v1/user/login",
        "/api/v1/user/authenticate", "/api/v1/user/logout", "/api/v1/health/find/status")
      .permitAll()
      .and()
      .authorizeRequests().anyRequest().authenticated()
      .and().logout().invalidateHttpSession(true).clearAuthentication(true);

    http.headers().frameOptions().disable();

    // Uses CorsConfigurationSource bean defined below
    http.cors().configurationSource(corsConfigurationSource());

    // Use CookieCsrfTokenRepository to issue cookie based CSRF(XSRF) tokens
    http.csrf().disable();

    //Create new session only if required and Sets maximum sessions to 10 and configures Session Registry bean
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).maximumSessions(10);
  }

}
