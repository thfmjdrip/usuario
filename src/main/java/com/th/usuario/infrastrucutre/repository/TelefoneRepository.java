package com.th.usuario.infrastrucutre.repository;

import com.th.usuario.infrastrucutre.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone,Long> {

}
