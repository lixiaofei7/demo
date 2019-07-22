package com.pandora.boot.demo.login;



import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "myfilter")
public class LoginFilter  implements Filter {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            System.out.println("Filter初始化中");
        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            System.out.println("开始进行过滤处理");

            HttpServletRequest request = (HttpServletRequest)servletRequest;
            if(request.getRequestURI().indexOf("/login") == -1 && request.getRequestURI().indexOf("/loginout") == -1&&
            request.getRequestURI().indexOf("/index") == -1 &&  request.getRequestURI().indexOf("/get") == -1){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            if("/login".equals(request.getRequestURI()) || "/doLogin".equals(request.getRequestURI())){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            HttpSession session = request.getSession();
            if(null == session || null == session.getAttribute("userName")){
                String type = request.getHeader("X-Requested-With");
                HttpServletResponse response = (HttpServletResponse)servletResponse;
                if("XMLHttpRequest".equalsIgnoreCase(type)){
                    response.setHeader("sessionstatus", "timeout");
                }else{//否则跳转到登录页面
                    response.sendRedirect("http://localhost:8080/login");
                }



                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }

        @Override
        public void destroy() {
            System.out.println("Filter销毁中");
        }

}
