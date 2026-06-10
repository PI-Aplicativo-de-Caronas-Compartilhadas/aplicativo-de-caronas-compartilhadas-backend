package com.generation.rachou.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "tb_viagem")
public class Viagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "A origem é obrigatória!")
	@Size(min = 3, max = 255, message = "O atributo nome deve ter no minimo 3 e no máximo 255 caracteres.")
	private String origem;
	
	@NotBlank(message = "O destino é obrigatório!")
	@Size(min = 3, max = 255, message = "O atributo nome deve ter no minimo 3 e no máximo 255 caracteres.")
	private String destino;
	
	@NotNull(message = "O preço informado deve ser maior que R$ 0,00!")
	private BigDecimal preco;
	
	@UpdateTimestamp
	private LocalDateTime previsaoSaida;
	
	private LocalDateTime previsaoChegada;
	
	@Size(min = 5, max = 50)
	private String status;

	@ManyToOne
	@JsonIgnoreProperties("viagem")
	private Usuario usuario;
	
	@ManyToOne
	@JsonIgnoreProperties("viagem")
	private Modalidade modalidade;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getOrigem() {
		return origem;
	}


	public void setOrigem(String origem) {
		this.origem = origem;
	}


	public String getDestino() {
		return destino;
	}


	public void setDestino(String destino) {
		this.destino = destino;
	}


	public BigDecimal getPreco() {
		return preco;
	}


	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}


	public LocalDateTime getPrevisaoSaida() {
		return previsaoSaida;
	}


	public void setPrevisaoSaida(LocalDateTime previsaoSaida) {
		this.previsaoSaida = previsaoSaida;
	}


	public LocalDateTime getPrevisaoChegada() {
		return previsaoChegada;
	}


	public void setPrevisaoChegada(LocalDateTime previsaoChegada) {
		this.previsaoChegada = previsaoChegada;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Modalidade getModalidade() {
		return modalidade;
	}


	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}
	
	

}
