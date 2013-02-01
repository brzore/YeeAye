package com.theta.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import static com.theta.security.CommonConstant.LOGIN_TO_URL;
import static com.theta.security.CommonConstant.USER_CONTEXT;

import com.theta.user.bean.User;

/**
 * @Title: YeeAye
 * @Description: Ȩ�޹���
 * @Author: brzore
 * @Since:2013-1-13
 * @Version:1.1.0
 */
public class ForumFilter implements Filter {
	private static final String FILTERED_REQUEST = "@@session_context_filtered_request";

	// �� ����Ҫ��¼���ɷ��ʵ�URI��Դ
	private static final String[] INHERENT_ESCAPE_URIS = {"/theta"};

	// �� ִ�й���
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		// ��-1 ��֤�ù�������һ��������ֻ������һ��
		if (request != null && request.getAttribute(FILTERED_REQUEST) != null) {
			chain.doFilter(request, response);
		} else {
			
			// ��-2 ���ù��˱�ʶ����ֹһ�������ι���
			request.setAttribute(FILTERED_REQUEST, Boolean.TRUE);
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			User userContext = getSessionUser(httpRequest);
			
			// ��-3 �û�δ��¼, �ҵ�ǰURI��Դ��Ҫ��¼���ܷ���
			if (userContext == null && !isURILogin(httpRequest.getRequestURI(), httpRequest)) {
				String toUrl = httpRequest.getRequestURL().toString();
				
				if (!StringUtils.isEmpty(httpRequest.getQueryString())) {
					toUrl += "?" + httpRequest.getQueryString();
				}

				// ��-4���û�������URL������session�У����ڵ�¼�ɹ�֮������Ŀ��URL
				httpRequest.getSession().setAttribute(LOGIN_TO_URL, toUrl);

				// ��-5ת������¼ҳ��
				request.getRequestDispatcher("/login").forward(request,response);
				return;
			}
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	/**
	 * ��ǰURI��Դ�Ƿ���Ҫ��¼���ܷ���
	 * 
	 * @param requestURI
	 * @param request
	 * @return
	 */
	private boolean isURILogin(String requestURI, HttpServletRequest request) {
		// ����Ǹ�Ŀ¼���򲻽�������
		if (request.getContextPath().equalsIgnoreCase(requestURI) || (request.getContextPath() + "/").equalsIgnoreCase(requestURI)) {
			return true;
		}

		// ƥ�����в�����Ȩ�����ص�URI
		for (String uri : INHERENT_ESCAPE_URIS) {
			if (requestURI != null && requestURI.indexOf(uri) >= 0) {
				return true;
			}
		}
		return false;
	}

	//��ȡ�û���Ϣ
	protected User getSessionUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(USER_CONTEXT);
	}

	public void destroy() {

	}
}
