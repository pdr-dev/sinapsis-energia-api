package com.sinapsis.energia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinapsis.energia.model.Subestacao;

public interface SubestacaoRepository extends JpaRepository<Subestacao, Long> {
	Optional<Subestacao> findByCodigo(String codigo);
}
