package com.github.hanselmito.toymanager.services;

import com.github.hanselmito.toymanager.Utils.PasswordUtil;
import com.github.hanselmito.toymanager.model.Usuario;
import com.github.hanselmito.toymanager.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario validateCredentials(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && PasswordUtil.hashPassword(password).equals(usuario.getContrasena())) {
            return usuario;
        }
        return null;
    }

    public Usuario saveUsuario(Usuario usuario) {
        usuario.setContrasena(PasswordUtil.hashPassword(usuario.getContrasena()));
        return usuarioRepository.save(usuario);
    }

    public boolean existUser(String email, String nombre) {
        return usuarioRepository.existsUsuarioByEmailOrNombre(email, nombre);
    }

    public Usuario getUserByEmail(String email) {
        String normalizedEmail = email.trim().toLowerCase();
        return usuarioRepository.findByEmail(normalizedEmail);
    }

    public Usuario getUserById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }
}