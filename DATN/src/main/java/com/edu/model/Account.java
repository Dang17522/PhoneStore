package com.edu.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public interface Account extends UserDetails {
	@Override
	default boolean isAccountNonExpired() {
		return true;
	}
	@Override
	default boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	default boolean isAccountNonLocked() {
		return true;
	}
	@Override
	default Collection<? extends GrantedAuthority> getAuthorities() {
		List<UserRole> userRoles = this.getUserRoles();
		if(userRoles == null) {
			return new ArrayList<>();
		}
		return userRoles.stream().map(ur -> {
			return new SimpleGrantedAuthority("ROLE_" + ur.getRole().getId());
		}).collect(Collectors.toList());
	}
	List<UserRole> getUserRoles();
	default boolean isStaff() {
		List<UserRole> userRoles = this.getUserRoles();
		return (userRoles != null && !userRoles.isEmpty());
	}
	default boolean hasAnyRole(String...roles) {
		List<UserRole> userRoles = this.getUserRoles();
		if(userRoles == null || userRoles.isEmpty()) {
			return false;
		}
		return userRoles.stream().anyMatch(ur -> {
			return Stream.of(roles).anyMatch(role -> ur.getRole().getId().endsWith(role));
		});
	}
}