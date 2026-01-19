package com.th.usuario.infrastrucutre.repository;


import com.th.usuario.infrastrucutre.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long> {

}
