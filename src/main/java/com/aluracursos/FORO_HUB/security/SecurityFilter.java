package com.aluracursos.FORO_HUB.security;

import com.aluracursos.FORO_HUB.errors.LoginException;
import com.aluracursos.FORO_HUB.models.User;
import com.aluracursos.FORO_HUB.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //extraer token del request
        String token = request.getHeader("Authorization");
        //validar token y si es valido continuar cadena de filtros (filterChain)
        if(token!=null){
            //Quitar "Bearer " del token
            token=token.replace("Bearer ","");
            var username = jwtTokenProvider.getUser(token);
            User user = userRepository.findByUsername(username).orElseThrow(()-> new LoginException("Token invalido"));
            var authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request,response);

    }
}
