package com.juba.spring_boot_library.dto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.juba.spring_boot_library.model.User;

public class UserInfoDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String email;
	private String password;
	private List<GrantedAuthority> authorities;

	public UserInfoDetails(User userInfo) {
		this.email = userInfo.getEmail();
		this.password = userInfo.getPassword();
		this.authorities = userInfo.getRoles().stream().map(role -> role.getPrivileges()).flatMap(List::stream)
				.map(e -> new SimpleGrantedAuthority(e.getName())).collect(Collectors.toList());
		this.authorities.addAll(userInfo.getRoles().stream().map(e -> new SimpleGrantedAuthority(e.getName())).collect(Collectors.toList()));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true; // Implement your logic if you need this
	}

	@Override
	public boolean isAccountNonLocked() {
		return true; // Implement your logic if you need this
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; // Implement your logic if you need this
	}

	@Override
	public boolean isEnabled() {
		return true; // Implement your logic if you need this
	}
}
