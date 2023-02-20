package com.edu;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.edu.service.UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserService accountservice;

	@Autowired
	HttpSession session;

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(username -> {
            try {
                com.edu.model.User user = accountservice.findById(username);
                String password = getPasswordEncoder().encode(user.getPassword());
                String[] roles = user.getAuthorites().stream().map(er -> er.getRole().getId())
                        .collect(Collectors.toList()).toArray(new String[0]);
                return User.withUsername(username).password(password).roles(roles).build();
            } catch (NoSuchElementException e) {
                throw new UsernameNotFoundException(username + " Không tìm thấy");
            }
        });
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable();
        http.authorizeRequests().antMatchers("/order/**").authenticated().antMatchers("/admin/**")
                .hasAnyRole("STAF", "DIRE").antMatchers("/report/*").hasRole("DIRE").anyRequest().permitAll();

        http.formLogin().loginPage("/security/login/form").loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/security/login/success", false).failureUrl("/security/login/error");

        http.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400);

        http.exceptionHandling().accessDeniedPage("/security/unauthoried");

        http.logout().logoutUrl("/security/logoff").logoutSuccessUrl("/security/logoff/success");

        http.oauth2Login().loginPage("/security/login/form").defaultSuccessUrl("/oauth2/login/success", true)
                .failureUrl("/auth/login/error").authorizationEndpoint().baseUri("/oauth2/authorization");
        http.httpBasic();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
}
