package com.github.hanselmito.toymanager.services;

import com.github.hanselmito.toymanager.Utils.PasswordUtil;
import com.github.hanselmito.toymanager.model.Usuario;
import com.github.hanselmito.toymanager.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Valida las credenciales del usuario.
     *
     * @param email    El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * @return El objeto Usuario si las credenciales son válidas, null en caso contrario.
     */
    public Usuario validateCredentials(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && PasswordUtil.hashPassword(password).equals(usuario.getContrasena())) {
            return usuario;
        }
        return null;
    }

    /**
     * Guarda un nuevo usuario en la base de datos.
     *
     * @param usuario El objeto Usuario a guardar.
     * @return El usuario guardado.
     */
    public Usuario saveUsuario(Usuario usuario) {
        usuario.setContrasena(PasswordUtil.hashPassword(usuario.getContrasena()));
        return usuarioRepository.save(usuario);
    }

    /**
     * Comprueba si un usuario existe por email o nombre.
     *
     * @param email  El correo electrónico del usuario.
     * @param nombre El nombre del usuario.
     * @return true si el usuario existe, false en caso contrario.
     */
    public boolean existUser(String email, String nombre) {
        return usuarioRepository.existsUsuarioByEmailOrNombre(email, nombre);
    }

    /**
     * Obtiene un usuario por su correo electrónico.
     *
     * @param email El correo electrónico del usuario.
     * @return El objeto Usuario si se encuentra, null en caso contrario.
     */
    public Usuario getUserByEmail(String email) {
        String normalizedEmail = email.trim().toLowerCase();
        return usuarioRepository.findByEmail(normalizedEmail);
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id El ID del usuario.
     * @return El objeto Usuario si se encuentra, null en caso contrario.
     */
    public Usuario getUserById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param "usuario" El objeto Usuario con los datos actualizados.
     * @return El usuario actualizado.
     */
    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param "usuario" El objeto Usuario con los datos actualizados.
     * @return El usuario actualizado.
     */
    public boolean deleteUsuario(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}