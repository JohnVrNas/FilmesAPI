package com.aula.joaoJava.Filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.aula.joaoJava.User.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Rotas públicas
        if (path.startsWith("/user/novouser") || path.startsWith("/user/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        var authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Basic ")) {
            response.sendError(401, "Usuário não autorizado");
            return;
        }

        var authEncode = authorization.substring("Basic".length()).trim();
        byte[] authDecode = Base64.getDecoder().decode(authEncode);
        var authString = new String(authDecode);
        String[] credenciais = authString.split(":");

        String username = credenciais[0];
        String senha = credenciais[1];

        var user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            response.sendError(401, "Usuário sem autorização");
        } else {
            var verificaSenha = BCrypt.verifyer().verify(senha.toCharArray(), user.get().getSenha());
            if (verificaSenha.verified) {
                // Salva usuário autenticado no request
                request.setAttribute("user", user.get());
                filterChain.doFilter(request, response);
            } else {
                response.sendError(401, "Senha incorreta");
            }
        }
    }

}
