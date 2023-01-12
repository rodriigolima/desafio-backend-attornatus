package br.com.desafio.attornatus.interceptor;

import br.com.desafio.attornatus.model.Endereco;
import br.com.desafio.attornatus.repository.EnderecoRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;


public class PrincipalInterceptor implements HandlerInterceptor {
    private final EnderecoRepository repository;

    public PrincipalInterceptor(EnderecoRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if ("POST".equalsIgnoreCase(request.getMethod()) && request.getRequestURI().endsWith("/enderecos")) {
            Endereco endereco = (Endereco) request.getAttribute("principal");
            if(endereco == null) {
                return true;
            }
            if (endereco.isPrincipal() && repository.existsByPrincipal(true)) {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                return false;
            }
        }
        return true;
    }

}





