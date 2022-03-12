package com.pj.springhttponlycookie.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserProfileDTO implements Serializable
{
  @Serial
  private static final long serialVersionUID = -5900543641956145866L;

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
}
