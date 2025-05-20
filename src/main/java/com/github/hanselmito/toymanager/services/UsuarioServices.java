package com.github.hanselmito.toymanager.services;

import com.github.hanselmito.toymanager.Utils.PasswordUtil;
import com.github.hanselmito.toymanager.exception.ResourceNotFoundException;
import com.github.hanselmito.toymanager.model.Usuario;
import com.github.hanselmito.toymanager.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(Usuario usuario) {
        usuario.setContrasena(PasswordUtil.hashPassword(usuario.getContrasena()));
        return usuarioRepository.save(usuario);
    }

    public Usuario obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }

    public Usuario actualizarUsuario(Integer id, Usuario usuarioActualizado) {
        Usuario usuario = obtenerUsuarioPorId(id);
        usuario.setNombre(usuarioActualizado.getNombre());
        usuario.setEmail(usuarioActualizado.getEmail());
        usuario.setImagen(usuarioActualizado.getImagen());
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(String.valueOf(id));
    }
}