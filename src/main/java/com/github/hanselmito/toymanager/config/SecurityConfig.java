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

    /**
     * Configuración de seguridad para la aplicación.
     * Define las reglas de autorización y autenticación.
     *
     * @param "http" La configuración de seguridad HTTP.
     * @return El objeto SecurityFilterChain configurado.
     * @throws Exception Si ocurre un error durante la configuración.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Servicio de detalles de usuario para la autenticación.
     * Busca usuarios por email o nombre.
     *
     * @param usuarioRepository Repositorio de usuarios.
     * @return UserDetailsService configurado.
     */
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

    /**
     * Configuración del filtro de seguridad HTTP.
     * Define las rutas y permisos de acceso.
     *
     * @param http La configuración de seguridad HTTP.
     * @return El objeto SecurityFilterChain configurado.
     * @throws Exception Si ocurre un error durante la configuración.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/usuario/register","/api/usuario/email/{email}","/api/usuario/getById/{id}", "/api/usuario/login", "/api/usuario/updatePerfil", "/api/usuario/all", "/api/usuario/delete/{id}",
                        "/api/productos/crear", "/api/productos/actualizar/{sku}", "/api/productos/todos", "/api/productos/conStock", "/api/productos/buscar", "/api/productos/porSku/{sku}", "/api/productos/{sku}",
                        "/api/productos-usuarios/guardar", "/api/productos-usuarios/usuario/{idUsuario}", "/api/productos-usuarios/usuario/{idUsuario}/producto/{sku}", "/api/productos-usuarios/eliminar", "/api/productos-usuarios/todos",
                        "/api/categorias/crear", "/api/categorias/todas", "/api/categorias/buscar", "/api/categorias/categoriaPadre/{id}", "/api/categorias/agregarHija/{idCategoriaPadre}", "/api/categorias/eliminar/{id}",
                        "/api/productos-categoria/guardar", "/api/productos-categoria/categoria/{idCategoria}", "/api/productos-categoria/producto/{skuProducto}", "/api/productos-categoria/eliminar",
                        "/api/proveedores/crear", "/api/proveedores/todos", "/api/proveedores/buscar", "/api/proveedores/{cif}", "/api/proveedores/actualizar/{cif}", "/api/proveedores/eliminar/{cif}",
                        "/api/productos-proveedores/crear", "/api/productos-proveedores/{productoSku}/{proveedorCif}", "/api/productos-proveedores/{productoSku}/{proveedorCif}/actualizar", "/api/productos-proveedores/{productoSku}/{proveedorCif}/eliminar", "/api/productos-proveedores/todos"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/usuario/register","/api/usuario/email/{email}","/api/usuario/getById/{id}", "/api/usuario/login", "/api/usuario/updatePerfil", "/api/usuario/all", "/api/usuario/delete/{id}",
                                "/api/productos/crear", "/api/productos/actualizar/{sku}","/api/productos/todos", "/api/productos/conStock", "/api/productos/buscar", "/api/productos/porSku/{sku}", "/api/productos/{sku}",
                                "/api/productos-usuarios/guardar", "/api/productos-usuarios/usuario/{idUsuario}", "/api/productos-usuarios/usuario/{idUsuario}/producto/{sku}", "/api/productos-usuarios/eliminar", "/api/productos-usuarios/todos",
                                "/api/categorias/crear", "/api/categorias/todas", "/api/categorias/buscar", "/api/categorias/categoriaPadre/{id}", "/api/categorias/agregarHija/{idCategoriaPadre}", "/api/categorias/eliminar/{id}",
                                "/api/productos-categoria/guardar", "/api/productos-categoria/categoria/{idCategoria}", "/api/productos-categoria/producto/{skuProducto}", "/api/productos-categoria/eliminar",
                                "/api/proveedores/crear", "/api/proveedores/todos", "/api/proveedores/buscar", "/api/proveedores/{cif}", "/api/proveedores/actualizar/{cif}", "/api/proveedores/eliminar/{cif}",
                                "/api/productos-proveedores/crear", "/api/productos-proveedores/{productoSku}/{proveedorCif}", "/api/productos-proveedores/{productoSku}/{proveedorCif}/actualizar", "/api/productos-proveedores/{productoSku}/{proveedorCif}/eliminar", "/api/productos-proveedores/todos").permitAll()
                        .anyRequest().authenticated());
        return http.build();
    }
}