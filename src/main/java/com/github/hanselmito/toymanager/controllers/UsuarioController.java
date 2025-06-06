package com.github.hanselmito.toymanager.controllers;

import com.github.hanselmito.toymanager.exception.ResourceNotFoundException;
import com.github.hanselmito.toymanager.model.Enum.RolTrabajador;
import com.github.hanselmito.toymanager.model.Usuario;
import com.github.hanselmito.toymanager.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<?> addUser(
            @RequestParam("nif") Integer nif,
            @RequestParam("nombre") String nombre,
            @RequestParam("email") String email,
            @RequestParam("contrasena") String contrasena,
            @RequestParam("imagen") MultipartFile imagen) {

        if (usuarioServices.existUser(email, nombre)) {
            return ResponseEntity.status(400).body("El usuario ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setId(nif);
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setContrasena(contrasena);
        usuario.setUltimoAcceso(Instant.now());

        try {
            usuario.setImagen(imagen.getBytes());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al procesar la imagen");
        }

        if (email.toLowerCase().endsWith("@admin.com")) {
            usuario.setRol(RolTrabajador.admin);
        } else {
            usuario.setRol(RolTrabajador.empleado);
        }

        Usuario nuevoUsuario = usuarioServices.saveUsuario(usuario);
        return ResponseEntity.status(201).body(nuevoUsuario);
    }

    @CrossOrigin
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> getUserByEmail(@PathVariable String email) {
        String normalizedEmail = email.trim().toLowerCase();
        Usuario usuario = usuarioServices.getUserByEmail(normalizedEmail);
        if (usuario == null) {
            throw new ResourceNotFoundException("El usuario con el email especificado no está registrado: ", normalizedEmail);
        }
        return ResponseEntity.ok(usuario);
    }

    @CrossOrigin
    @GetMapping("/getById/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Integer id) {
        Usuario usuario = usuarioServices.getUserById(id);
        if (usuario == null) {
            throw new ResourceNotFoundException("No se ha encontrado el usuario con ID: ", id);
        }
        return ResponseEntity.ok(usuario);
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<Usuario> validateCredentials(@RequestBody Usuario loginRequest) {
        Usuario usuario = usuarioServices.validateCredentials(loginRequest.getEmail(), loginRequest.getContrasena());
        if (usuario == null) {
            usuario = usuarioServices.validateCredentials(loginRequest.getNombre(), loginRequest.getContrasena());
        }
        if (usuario == null) {
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok(usuario);
    }

    @CrossOrigin
    @PutMapping("/updatePerfil")
    public ResponseEntity<Usuario> updatePerfil(
            @RequestParam("nif") Integer id,
            @RequestParam("nombre") String nombre,
            @RequestParam("email") String email,
            @RequestParam("contrasena") String contrasena,
            @RequestParam("imagen") MultipartFile imagen) {

        Usuario usuarioExistente = usuarioServices.getUserById(id);
        if (usuarioExistente == null) {
            throw new ResourceNotFoundException("No se ha encontrado el usuario con ID: ", id);
        }

        usuarioExistente.setNombre(nombre);
        usuarioExistente.setEmail(email);
        usuarioExistente.setContrasena(contrasena);

        try {
            usuarioExistente.setImagen(imagen.getBytes());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }

        usuarioExistente.setUltimoAcceso(Instant.now());

        Usuario usuarioActualizado = usuarioServices.saveUsuario(usuarioExistente);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioServices.findAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        if (usuarioServices.deleteUsuario(id)) {
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("No se ha encontrado el usuario con ID: ", id);
        }
    }
}