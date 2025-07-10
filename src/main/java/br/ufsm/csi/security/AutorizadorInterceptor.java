package br.ufsm.csi.security;

import br.ufsm.csi.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class AutorizadorInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();

        //libera acesso a pagina de login e recursos estaticos (css etc)
        if (uri.equals("/") || uri.endsWith("/login") || uri.contains("/usuario") || uri.startsWith("/css/") || uri.startsWith("/js/") || uri.startsWith("/assets/")) {
            return true;
        }

        Object usuarioLogadoObj = request.getSession().getAttribute("usuarioLogado");

        if (usuarioLogadoObj == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return false; //bloqueia
        }

        return true;
    }
}
