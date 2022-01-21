package com.ihc.ehr.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.ihc.ehr.model.ValidatedUser;

/**
 * @author Ihsan Ullah
 *
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	//private UserService userService;
	private UserValidator userValidator;
	

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		
		ValidatedUser user = null;

		List<GrantedAuthority> appRole = (List<GrantedAuthority>) authentication.getAuthorities();
		String app = appRole.get(0).getAuthority();
		user = userValidator.ValidateUser(authentication.getName(), authentication.getCredentials().toString() ,app);		

		if (user != null) {

			Long id=user.getUser_id();
			String principal = id+":"+authentication.getName();
			//String password = authentication.getCredentials().toString();
			
			
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_" + user.getRole_name()));

			// use the credentials
			// and authenticate against the third-party system
			return new UsernamePasswordAuthenticationToken(principal, null, grantedAuths);

		} else {
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}