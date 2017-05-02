package cn.mldn.vshop.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import cn.mldn.util.action.ActionMessageUtil;
@WebFilter("/pages/back/admin/*") 
public class AdminLoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req ;
		if (request.getSession().getAttribute("mid") == null) {
			// request.setAttribute("currentUrl", request.getRequestURL());
			// System.out.println("**********************************");
			// req.getRequestDispatcher("/MemberLoginActionFront!cookieLogin.action").forward(req, resp);
			request.setAttribute("msg", ActionMessageUtil.getMsg("login.uncheck"));
			request.setAttribute("url", ActionMessageUtil.getUrl("login.page"));
			req.getRequestDispatcher(ActionMessageUtil.getUrl("forward.front.page")).forward(request, resp); 
		} else {
			Set<String> allActions = (Set<String>) request.getSession().getAttribute("allActions") ;
			if (allActions.contains("admin:show")) {
				chain.doFilter(req, resp);
			} else {
				request.setAttribute("msg", ActionMessageUtil.getMsg("unaction.msg"));
				request.setAttribute("url", ActionMessageUtil.getUrl("index.page"));
				req.getRequestDispatcher(ActionMessageUtil.getUrl("forward.front.page")).forward(request, resp); ;
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
