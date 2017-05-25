package javaweb.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD}, urlPatterns = { "/*" })
public class filtroAutenticacao implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("Filtrou");

		//Casting do HttpServelt Request
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		// Pega URL
		String url = httpServletRequest.getRequestURI();
		//Capturando Sessao
		HttpSession sessao = httpServletRequest.getSession();
		//Está logado?
		if (sessao.getAttribute("usuAutenticado")!=null || url.lastIndexOf("index.html")>-1 || url.lastIndexOf("autenticador.do") >-1 ){
			chain.doFilter(request, response); //Permite o fluxo da requisiacao
		}else{
		//redireciona para login
			((HttpServletResponse) response).sendRedirect("index.html");
		}
		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
