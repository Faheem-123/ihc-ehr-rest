package com.ihc.ehr.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihc.ehr.service.UserService;

/**
 * @author Ihsan Ullah
 *
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationRestController {

   // @Value("${jwt.header}")
   // private String tokenHeader;
	@Autowired
	private UserService userService;	
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, 
    		Device device) throws AuthenticationException {
    	
    	// Perform the security    	
    	List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("APP_"+authenticationRequest.getApp()));
        
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword(),
                        grantedAuths
                )
        );
        
        
        SecurityContextHolder.getContext().setAuthentication(authentication);     
        
        Long Id=Long.parseLong(authentication.getName().split(":")[0]);
        String userName=authentication.getName().split(":")[1];
        
        JwtUser userDetails=new JwtUser(Id, userName, null,authentication.getAuthorities());
        
        final String token = jwtTokenUtil.generateToken(userDetails, device);

        
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }
    
    @RequestMapping("/ip/{ip:.+}")
	public @ResponseBody ResponseEntity<Boolean> ValidateIP(@PathVariable(value = "ip") String ip) {

		
		System.out.println("Inside ValidateIP:"+ip);
		
		Boolean result=userService.validateIP(ip);
		
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
		
	}
    
	@RequestMapping("/getIp/")
	public @ResponseBody ResponseEntity<String> getIP(HttpServletRequest request) {

		
		String remoteAddr = "";

		/*
		Map<String, String> result = new HashMap<>();

		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			result.put(key, value);
			System.out.println(key + " --> " + value);
		}
		*/

		remoteAddr = request.getHeader("X-FORWARDED-FOR");

		if (remoteAddr == null || "".equals(remoteAddr)) {
			remoteAddr = request.getRemoteAddr();
		}

		return new ResponseEntity<String>(remoteAddr, HttpStatus.OK);

	}
    
}
