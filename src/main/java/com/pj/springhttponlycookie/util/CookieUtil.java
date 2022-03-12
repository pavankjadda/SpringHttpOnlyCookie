package com.pj.springhttponlycookie.util;

import javax.servlet.http.Cookie;


/**
 * Utility class for Cookie operations
 *
 * @author Pavan Kumar Jadda
 * @since 2.5.12
 */
public class CookieUtil
{
  private CookieUtil()
  {
    //Hides public constructor
  }

  /**
   * Set cookie with given value and default settings
   *
   * @return cookie Created cookie
   *
   * @author Pavan Kumar Jadda
   * @since 2.5.12
   */
  public static Cookie createCookie(String name, String value)
  {
    var cookie = new Cookie(name, value);
    cookie.setMaxAge(3600);
    cookie.setHttpOnly(false);
    cookie.setSecure(true);
    cookie.setPath("/");
    return cookie;
  }
}
