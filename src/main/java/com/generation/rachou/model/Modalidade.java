package com.generation.rachou.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "tb_modalidade")
public class Modalidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O atributo nome é obrigatório!")
	@Size(min = 3, max = 255, message = "O atributo nome deve ter no minimo 3 e no máximo 255 caracteres.")
	private String nome;
	
	@NotBlank(message = "A descrição é obrigatória!")
	@Size(min = 3, max = 255, message = "A descrição nome deve ter no minimo 3 e no máximo 255 caracteres.")
	private String descricao;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "modalidade", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties(value = "modalidade", allowSetters = true)
	private List<Viagem> viagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
