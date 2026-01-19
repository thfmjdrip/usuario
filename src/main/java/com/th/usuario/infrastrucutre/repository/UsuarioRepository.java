package com.th.usuario.infrastrucutre.repository;


import com.th.usuario.infrastrucutre.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    boolean existsByEmail(String email);
    
    Optional<Object> findByEmail(String email);

    void deleteByEmail (String email);
}
