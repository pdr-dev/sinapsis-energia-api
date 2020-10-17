package com.sinapsis.energia.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sinapsis.energia.model.Rede;
import com.sinapsis.energia.model.Subestacao;
import com.sinapsis.energia.service.RedeService;

/**
 * @author Pedro Henrique
 */

@Service
public class RedeServiceImpl implements RedeService {

	@Override
	public Optional<Rede> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Rede> buscarPorCodigoDaRede(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Rede> buscarPorSubestacao(Subestacao subestacao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rede incluir(Rede rede) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Rede> alterar(Long idRede, Rede rede) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Long idRede) {
		// TODO Auto-generated method stub

	}

}
