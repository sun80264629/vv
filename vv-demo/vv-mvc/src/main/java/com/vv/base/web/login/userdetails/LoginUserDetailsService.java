package com.vv.base.web.login.userdetails;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

public class LoginUserDetailsService extends JdbcDaoImpl {

	@Override
	protected void addCustomAuthorities(String username,
			List<GrantedAuthority> authorities) {
		// TODO 自动生成的方法存根
		super.addCustomAuthorities(username, authorities);
		System.out.println("Custom   Authorities");
	}

}
