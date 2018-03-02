package com.zkhv.user.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zkhv.constant.Constant;
import com.zkhv.user.entity.User;
//验证用户是否登录的过滤器
public class LoginFilter implements Filter{

	
	public void destroy() {
		
	}

	//判断用户是否登录的过滤器
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,FilterChain chain) throws IOException, ServletException {
		//强转
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response =(HttpServletResponse) servletResponse;
		//得到请求的url 
		String uri = request.getRequestURI();
		//访问门口机页面无需登录
		if(uri!=null&&uri.trim()!=""&&uri.contains("/login")||uri.contains("/mkjView.jsp")){
			//当前请求为页面登录页面,放行
			chain.doFilter(request, response);
		}else{
			//当前请求不是登录页面
			//得到session
			HttpSession session = request.getSession();
			//得到session中的用户
			User user = (User) session.getAttribute(Constant.LOGIN_USER);
			if(user!=null){
				//已是登录状态，放行
				chain.doFilter(request, response);
			}else{
				//跳转到登录页面
				response.sendRedirect(request.getContextPath()+"/login.jsp");
			}
		}
		
	}

	
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
