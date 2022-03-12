package com.pj.springhttponlycookie.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
public class UserDTO implements Serializable
{
	@Serial
	private static final long serialVersionUID = -5900543641956145866L;

	private Long id;
	private String username;
	private String firstName;
	private String lastName;
	private String displayName = "";
	private String email;
	private Boolean active = true;
	private String token;
	private String tokenExpirationDate;
	private Set<AuthorityDTO> authorities;
}
