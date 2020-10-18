package com.sinapsis.energia.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Pedro Henrique
 */
@Entity(name = "tb_rede_mt")
public class Rede {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rede_mt")
	private Long idRedeMT;

	@JoinColumn(name = "id_subestacao", nullable = false)
	@ManyToOne(cascade = CascadeType.REMOVE)
//	@JoinColumn(name = "fk_subestacao_rede_mt")
	private Subestacao subestacao;

	@Column(name = "codigo", nullable = false, unique = true, length = 5)
	private String codigo;

	@Column(name = "nome", length = 100)
	private String nome;

	@Column(name = "tensao_nominal", precision = 5, scale = 2)
	private BigDecimal tensaoNominal;

	/**
	 * @return the idRedeMT
	 */
	public Long getIdRedeMT() {
		return idRedeMT;
	}

	/**
	 * @param idRedeMT the idRedeMT to set
	 */
	public void setIdRedeMT(Long idRedeMT) {
		this.idRedeMT = idRedeMT;
	}

	/**
	 * @return the subestacao
	 */
	public Subestacao getSubestacao() {
		return subestacao;
	}

	/**
	 * @param subestacao the subestacao to set
	 */
	public void setSubestacao(Subestacao subestacao) {
		this.subestacao = subestacao;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the tensaoNominal
	 */
	public BigDecimal getTensaoNominal() {
		return tensaoNominal;
	}

	/**
	 * @param tensaoNominal the tensaoNominal to set
	 */
	public void setTensaoNominal(BigDecimal tensaoNominal) {
		this.tensaoNominal = tensaoNominal;
	}
}
