package com.ihc.ehr.security;

import java.io.Serializable;

/**
 * @author Ihsan Ullah
 *
 */
public class  JwtAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    private String username;
    private String password;
    private String app;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String username, String password,String app) {
        this.setUsername(username);
        this.setPassword(password);
        this.setApp(app);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}    
}
