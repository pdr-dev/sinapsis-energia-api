package com.sinapsis.energia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinapsis.energia.model.Rede;
import com.sinapsis.energia.model.Subestacao;

/**
 * @author Pedro Henrique
 */
public interface RedeRepository extends JpaRepository<Rede, Long> {

	Optional<Rede> findBySubestacao(Subestacao subestacao);

	Optional<Rede> findByCodigo(String codigo);

}
