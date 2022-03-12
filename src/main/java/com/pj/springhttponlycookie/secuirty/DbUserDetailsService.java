package com.pj.springhttponlycookie.secuirty;


import com.pj.springhttponlycookie.domain.User;
import com.pj.springhttponlycookie.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
public class DbUserDetailsService implements UserDetailsService
{
  private final UserRepository userRepository;

  public DbUserDetailsService(UserRepository userRepository)
  {
    this.userRepository = userRepository;
  }

  /**
   * Gets UserDetails object based on provided username
   *
   * @param username username of the User in PRES database
   *
   * @return {@link UserDetails} object
   */
  @Override
  public UserDetails loadUserByUsername(String username)
  {
    User user = userRepository.findByUsername(username);
    if (user == null)
      throw new UsernameNotFoundException("Could not find Username in the database");
    return new DbUserDetails(user, Collections.singletonList((GrantedAuthority) () -> "ROLE_USER"));
  }
}
