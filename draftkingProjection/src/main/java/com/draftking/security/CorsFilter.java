package com.draftking.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter implements Filter {
//Spring that includes headers for Cross-Origin Resource Sharing (CORS) in the response.	
//allow cross origin resourse sharing (cors).  spring will add CORS headers so browser will 
//allow cross origin sharing.
//this tells the browser to allow cors request from the client.	
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) res;

		HttpServletRequest request = (HttpServletRequest) req;

		//response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost");

		
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

		response.setHeader("Access-Control-Expose-Headers", "x-requested-with");


		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		chain.doFilter(req, res);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
