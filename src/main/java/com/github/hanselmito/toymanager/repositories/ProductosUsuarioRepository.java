package com.github.hanselmito.toymanager.repositories;

import com.github.hanselmito.toymanager.model.ProductosUsuario;
import com.github.hanselmito.toymanager.model.ProductosUsuarioId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosUsuarioRepository extends JpaRepository<ProductosUsuario, ProductosUsuarioId> {
    @SpringBootApplication
    @ComponentScan(basePackages = "com.github.hanselmito.toymanager")
    public class ToyManagerAplication {
        public static void main(String[] args) {
            SpringApplication.run(ToyManagerAplication.class, args);
        }
    }
}
