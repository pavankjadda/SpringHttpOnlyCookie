package com.pj.springhttponlycookie.repository;

import com.pj.springhttponlycookie.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>
{
  User findByUsername(String username);
}
