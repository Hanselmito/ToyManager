package com.github.hanselmito.toymanager.config;

import com.github.hanselmito.toymanager.model.Usuario;
import com.github.hanselmito.toymanager.repositories.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository usuarioRepository) {
        return username -> {
            Usuario usuario = usuarioRepository.findByEmail(username);
            if (usuario == null) {
                usuario = usuarioRepository.findByNombre(username);
            }
            if (usuario == null) {
                throw new UsernameNotFoundException("Usuario no encontrado");
            }
            return User.builder()
                    .username(usuario.getEmail())
                    .password(usuario.getContrasena())
                    .roles(usuario.getRol().name())
                    .build();
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/usuario/register","/api/usuario/email/{email}","/api/usuario/getById/{id}", "/api/usuario/login", "/api/usuario/updatePerfil", "/api/productos/crear", "/api/productos/todos", "/api/productos/conStock", "/api/productos/buscar", "/api/productos/porSku/{sku}", "/api/productos/{sku}"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/usuario/register","/api/usuario/email/{email}","/api/usuario/getById/{id}", "/api/usuario/login", "/api/usuario/updatePerfil", "/api/productos/crear", "/api/productos/todos", "/api/productos/conStock", "/api/productos/buscar", "/api/productos/porSku/{sku}", "/api/productos/{sku}").permitAll()
                        .anyRequest().authenticated());
        return http.build();
    }
}