package com.cognizant.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.cognizant.auth.filter.JwtFilter;
import com.cognizant.auth.service.UserService;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	private UserService userService;

	private JwtFilter jwtFilter;

	@Autowired
	public SecurityConfiguration(UserService userService, JwtFilter jwtFilter) {
		this.userService = userService;
		this.jwtFilter = jwtFilter;
	}

	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	

	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	

	//giving access to multiple authenticated end points
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable()
         .cors().disable()
         .authorizeRequests()
         .antMatchers("/authenticate", "/h2-console/**", "/**")
         .permitAll()
         .anyRequest()
         .authenticated()
         .and()
         .exceptionHandling()
         .and()
         .sessionManagement()
		 .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		http.headers().frameOptions().disable();

	}
	
	/*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     
        http.csrf().disable()
        .cors().disable()
        .authorizeRequests()
        .antMatchers("/authenticate", "/h2-console/**", "/**")
        .permitAll()
        .anyRequest()
        .authenticated()
                .and()
                .exceptionHandling()
                .and()
                .sessionManagement()
       		 .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
 
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		http.headers().frameOptions().disable();
        
 
        return http.build();
    }*/

}
