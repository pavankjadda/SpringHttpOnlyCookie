package com.pj.springhttponlycookie.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;


/**
 * Utility class for User and related operations
 *
 * @author Pavan Kumar Jadda
 * @since 1.0.0
 */
@Service
public class UserUtil
{
  private static final Logger logger = LoggerFactory.getLogger(UserUtil.class);

  private UserUtil()
  {
    //Hides public constructor
  }

  /**
   * Get current username from Spring Security Context Holder
   *
   * @return logged in user username if it can be retrieved otherwise return 'System'
   *
   * @author Pavan Kumar Jadda
   * @since 1.0.0
   */
  public static String getUsername()
  {
    try
    {
      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return user.getUsername();
    }
    catch (Exception e)
    {
      logger.debug("Failed to get user information.");
    }
    return "System";
  }

  /**
   * Get current User Roles from Spring Security Context Holder
   *
   * @return Logged in User Roles username if it can be retrieved otherwise return 'System'
   *
   * @author Pavan Kumar Jadda
   * @since 2.2.22
   */
  public static Collection<GrantedAuthority> getCurrentUserAuthorities()
  {
    try
    {
      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return user.getAuthorities();
    }
    catch (Exception e)
    {
      logger.debug("Failed to get user information.");
    }
    return Collections.emptyList();
  }

  /**
   * Checks if the current user is logged in
   *
   * @return Returns true if user logged in otherwise returns false
   *
   * @author Pavan Kumar Jadda
   * @since 1.0.0
   */
  public static boolean isLoggedIn()
  {
    return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
  }

  /**
   * Logout user and delete session
   *
   * @param httpServletRequest Servlet HttpServletRequest object
   *
   * @return status of logout request
   *
   * @author Pavan Kumar Jadda
   * @since 1.0.0
   */
  public static boolean logoutUser(HttpServletRequest httpServletRequest)
  {
    try
    {
      SecurityContextHolder.getContext().setAuthentication(null);
      httpServletRequest.logout();
      return true;
    }
    catch (ServletException e)
    {
      logger.warn("Exception occurred while logging out. Message: {}", e.getLocalizedMessage());
    }
    return false;
  }

}
