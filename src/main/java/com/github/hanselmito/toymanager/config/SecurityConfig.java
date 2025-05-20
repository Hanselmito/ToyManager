package com.github.hanselmito.toymanager.config;

import com.github.hanselmito.toymanager.model.Usuario;
import com.github.hanselmito.toymanager.repositories.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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
                    .username(usuario.getEmail()) // Devuelve el email como nombre de usuario
                    .password(usuario.getContrasena())
                    .roles(usuario.getRol().name())
                    .build();
        };
    }
}