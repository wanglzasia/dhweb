package com.dh.ora.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class CharacterEncodingFilter extends HttpServlet
	implements Filter
{

	private static final long serialVersionUID = 1L;
	private String charset;
	private FilterConfig config;

	public CharacterEncodingFilter()
	{
		charset = "UTF-8";
	}

	public void destroy()
	{
		charset = null;
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException
	{
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config)
		throws ServletException
	{
		this.config = config;
		String charset = config.getServletContext().getInitParameter("charset");
		if (charset != null && charset.trim().length() != 0)
			this.charset = charset;
	}
}
