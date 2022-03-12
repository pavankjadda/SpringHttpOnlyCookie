package com.pj.springhttponlycookie.service;

import com.pj.springhttponlycookie.domain.User;
import com.pj.springhttponlycookie.dto.AuthorityDTO;
import com.pj.springhttponlycookie.dto.UserDTO;
import com.pj.springhttponlycookie.dto.UserProfileDTO;
import com.pj.springhttponlycookie.repository.UserRepository;
import com.pj.springhttponlycookie.secuirty.DbUserDetails;
import com.pj.springhttponlycookie.util.CookieUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implements User business logic
 *
 * @author Pavan Kumar Jadda
 * @since 1.0.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService
{
  private final UserRepository userRepository;
  private final ModelMapper modelMapper;
  private final Logger logger = LoggerFactory.getLogger(getClass());

  public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper)
  {
    this.userRepository = userRepository;
    this.modelMapper = modelMapper;
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
   * @since 1.0.0
   */
  @Override
  public UserDTO loginUser(HttpServletRequest request, HttpServletResponse response)
  {
    response.addCookie(CookieUtil.createCookie("X-Auth-Token", request.getSession(false).getId()));
    response.addCookie(CookieUtil.createCookie("isLoggedIn", "true"));
    return getUserDetails(request, response);
  }


  /**
   * Get logged-in user information
   *
   * @return UserDTO object
   *
   * @author Pavan Kumar Jadda
   * @since 1.0.0
   */
  @Override
  public UserDTO getUserDetails(HttpServletRequest request, HttpServletResponse response)
  {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // If NOT anonymous user, get user info
    if (!(authentication instanceof AnonymousAuthenticationToken))
    {
      UserDTO userDTO;
      try
      {
        org.springframework.security.core.userdetails.User springSecurityUser = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        User user = userRepository.findByUsername(springSecurityUser.getUsername());
        userDTO = createUserDtoObject(request, user);

        //Map Spring Security authorities to AuthorityDTO set
        userDTO.setAuthorities(springSecurityUser.getAuthorities().stream().map(grantedAuthority -> new AuthorityDTO(grantedAuthority.getAuthority())).collect(Collectors.toSet()));
      }
      catch (Exception e)
      {
        logger.info("Failed to convert Authentication Principal to Spring Security User, logged in user is INTERNAL user");
        User user = ((DbUserDetails) authentication.getPrincipal()).getUser();
        userDTO = createUserDtoObject(request, user);
      }
      response.addCookie(CookieUtil.createCookie("X-Auth-Token", request.getSession(false).getId()));
      response.addCookie(CookieUtil.createCookie("isLoggedIn", "true"));
      return userDTO;
    }
    return null;
  }

  @Override
  public UserProfileDTO findUserByUsername(String username)
  {
    User user = userRepository.findByUsername(username);
    if (user != null)
      return modelMapper.map(userRepository.findByUsername(username), UserProfileDTO.class);
    return null;
  }


  private UserDTO createUserDtoObject(HttpServletRequest request, User user)
  {
    UserDTO userDTO = modelMapper.map(user, UserDTO.class);
    userDTO.setUsername(user.getUsername());
    userDTO.setActive(user.getActive());
    userDTO.setAuthorities(Set.of(new AuthorityDTO("ROLE_USER")));
    userDTO.setToken(request.getSession(false).getId());
    userDTO.setTokenExpirationDate(convertEpochTimeToUtcTime(request.getSession(false).getCreationTime() + 3600000L).toString());
    return userDTO;
  }

  /**
   * Convert UNIX Epoch time to UTC time
   *
   * @param epochTime UTC Date Time in Unix Epoch Time(milliseconds)
   *
   * @return UTC Date Time
   *
   * @author Pavan Kumar Jdda
   * @since 1.0.0
   */
  public Instant convertEpochTimeToUtcTime(Long epochTime)
  {
    if (epochTime == null)
      return null;
    return Instant.ofEpochMilli(epochTime);
  }
}
