package com.pj.springhttponlycookie.secuirty;

import com.pj.springhttponlycookie.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;

public record DbUserDetails(User user, Collection<? extends GrantedAuthority> roles) implements UserDetails
{
	@Serial
	private static final long serialVersionUID = 677487327261457030L;

	public User getUser()
	{
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return roles;
	}

	@Override
	public String getPassword()
	{
		return user.getPassword();
	}

	@Override
	public String getUsername()
	{
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return user.getAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return user.getAccountNonLocked();
	}

	/**
	 * Indicates whether the user's credentials (password) has expired. Expired
	 * credentials prevent user.
	 *
	 * @return <code>true</code> if the user's credentials are valid (ie non-expired),
	 * <code>false</code> if no longer valid (ie expired)
	 */
	@Override
	public boolean isCredentialsNonExpired()
	{
		return user.getCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled()
	{
		return user.getActive();
	}
}
