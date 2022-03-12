package com.pj.springhttponlycookie.service;

import com.pj.springhttponlycookie.dto.UserDTO;
import com.pj.springhttponlycookie.dto.UserProfileDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService
{

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
  UserDTO loginUser(HttpServletRequest request, HttpServletResponse response);

  /**
   * Get logged-in user information
   *
   * @return UserDTO object
   *
   * @author Pavan Kumar Jadda
   * @since 2.0.0
   */
  UserDTO getUserDetails(HttpServletRequest request, HttpServletResponse response);

  UserProfileDTO findUserByUsername(String username);

}
