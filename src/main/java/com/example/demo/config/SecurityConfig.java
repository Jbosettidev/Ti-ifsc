package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuração TEMPORÁRIA de segurança.
 * <p>
 * Sem essa classe, o Spring Boot detecta o spring-boot-starter-security no
 * classpath e trava automaticamente TODAS as rotas atrás de login HTTP
 * Basic — inclusive seus arquivos estáticos e o /api/levels. Isso é o
 * motivo mais provável do frontend não estar conseguindo carregar nada.
 * <p>
 * Aqui liberamos tudo (permitAll) por enquanto, só pra destravar os
 * testes. Quando o login/cadastro (Usuario) estiver pronto de verdade,
 * troque authorizeHttpRequests para exigir autenticação nas rotas que
 * fazem sentido (ex: /api/progress) e mantenha liberado só o necessário
 * (arquivos estáticos, login, cadastro).
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
            .csrf(csrf -> csrf.disable()); // CSRF é mais relevante pra forms tradicionais;
                                            // como o frontend chama a API via fetch/JSON,
                                            // desabilitar aqui evita bloqueios inesperados
                                            // em POST/PATCH/DELETE por enquanto.
        return http.build();
    }

    // Deixamos o PasswordEncoder já disponível aqui, porque o
    // UsuarioServices precisa dele pra parar de comparar senha em texto
    // puro (veja o comentário lá no arquivo).
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}