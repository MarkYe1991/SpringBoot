package com.sishuok.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.util.StarBucksUtil;
public class AuthenticationFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Enter the AuthenticationFilter...");
		HttpServletRequest httpReq = (HttpServletRequest) req;

		boolean isLegal = false;
		try {
			isLegal = StarBucksUtil.isLegalSign(httpReq);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		if(isLegal) {
			chain.doFilter(req, res);
		}
		else {
			RequestDispatcher reqDispatcher = httpReq.getRequestDispatcher("/messageGateWay/illegalSenior");
			reqDispatcher.forward(req, res);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}


}
