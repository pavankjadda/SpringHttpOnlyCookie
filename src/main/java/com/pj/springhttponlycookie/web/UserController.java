package com.pj.springhttponlycookie.web;

import com.pj.springhttponlycookie.dto.UserDTO;
import com.pj.springhttponlycookie.dto.UserProfileDTO;
import com.pj.springhttponlycookie.service.UserService;
import com.pj.springhttponlycookie.util.UserUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Exposes API end points for User and related operations
 *
 * @author Pavan Kumar Jadda
 * @since 2.0.0
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController
{
  private final UserService userService;

  public UserController(UserService userService)
  {
    this.userService = userService;
  }

  /**
   * Get user profile information. Used by profile section in client applications
   *
   * @param username Username of the User
   *
   * @return UserDTO object
   *
   * @author Pavan Kumar Jadda
   * @since 2.0.0
   */
  @GetMapping("/find/username/{username}")
  public UserProfileDTO findUserByUsername(@PathVariable String username)
  {
    return userService.findUserByUsername(username);
  }

  /**
   * Spring Security takes care of the authentication, this method returns UserDTO object of the corresponding user
   *
   * @param request  Servlet HttpServletRequest object
   * @param response Servlet HttpServletResponse object
   *
   * @return UserDTO object
   *
   * @author Pavan Kumar Jadda
   * @since 2.0.0
   */
  @GetMapping(value = {"/authenticate", "/login"})
  public UserDTO loginUser(HttpServletRequest request, HttpServletResponse response)
  {
    return userService.loginUser(request, response);
  }

  /**
   * Get logged-in user information
   *
   * @return UserDTO object
   *
   * @author Pavan Kumar Jadda
   * @since 2.0.0
   */
  @GetMapping(value = {"/home"})
  public UserDTO getUserDetails(HttpServletRequest request, HttpServletResponse response)
  {
    return userService.getUserDetails(request, response);
  }


  /**
   * Checks if the current user is logged in
   *
   * @return Returns true if user logged in otherwise returns false
   *
   * @author Pavan Kumar Jadda
   * @since 2.0.0
   */
  @GetMapping("/is_logged_in")
  public boolean isLoggedIn()
  {
    return UserUtil.isLoggedIn();
  }

  /**
   * Logout user and delete session
   *
   * @param httpServletRequest Servlet HttpServletRequest object
   *
   * @return status of logout request
   *
   * @author Pavan Kumar Jadda
   * @since 2.0.0
   */
  @GetMapping("/logout")
  public boolean logoutUser(HttpServletRequest httpServletRequest)
  {
    return UserUtil.logoutUser(httpServletRequest);
  }
}
