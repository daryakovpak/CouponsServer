package com.darya.coupons.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.darya.coupons.controllers.CacheController;
import com.darya.coupons.data.UserLoginData;


@Component
public class LoginFilter implements Filter {

	@Autowired
	private CacheController cacheController;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		String pageRequested = httpServletRequest.getRequestURL().toString();
		
		if (pageRequested.endsWith("/login")) {
			chain.doFilter(httpServletRequest, response);
			return;
		}
		if (pageRequested.endsWith("/users") && httpServletRequest.getMethod().toString().equals("POST")) {
			chain.doFilter(httpServletRequest, response);
			return;
		}
//		Get user`s token from header and can all user`s data from the cache
		
		String token = httpServletRequest.getHeader("Authorization");
		UserLoginData userLoginData = (UserLoginData) cacheController.get(token);
		
		if (userLoginData != null) {
			request.setAttribute("userLoginData", userLoginData);
			chain.doFilter(httpServletRequest, response);
			return;
		}
		
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setStatus(401);
	}





}
