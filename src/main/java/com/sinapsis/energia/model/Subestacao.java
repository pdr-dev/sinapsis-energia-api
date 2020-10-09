package com.sinapsis.energia.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Pedro Henrique
 */
@Entity(name = "tb_subestacao")
public class Subestacao {

	public Subestacao() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_subestacao")
	private Long idSubestacao;

	@Column(name = "codigo", nullable = false, unique = true, length = 3)
	private String codigo;

	@Column(name = "nome", length = 100)
	private String nome;

	@Column(name = "latitude", nullable = false, precision = 15, scale = 13)
	private BigDecimal latitude;

	@Column(name = "longitude", nullable = false, precision = 15, scale = 13)
	private BigDecimal longitude;

	/**
	 * @return the idSubestacao
	 */
	public Long getIdSubestacao() {
		return idSubestacao;
	}

	/**
	 * @param idSubestacao the idSubestacao to set
	 */
	public void setIdSubestacao(Long idSubestacao) {
		this.idSubestacao = idSubestacao;
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
	 * @return the latitude
	 */
	public BigDecimal getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public BigDecimal getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

}
