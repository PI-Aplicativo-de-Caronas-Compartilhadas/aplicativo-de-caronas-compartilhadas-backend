package com.generation.rachou.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo nome é obrigatório!")
	@Size(min = 3, max = 255, message = "O atributo nome deve ter no minimo 3 e no máximo 255 caracteres.")
	private String nome;
	
	@Email
	@Column(unique = true)
	@NotBlank(message = "O atributo email é obrigatório!")
	@Size(max = 50, message = "O atributo nome deve ter no máximo 50 caracteres.")
	private String email;
	
	@Column(unique = true)
	@NotBlank(message = "O atributo telefone é obrigatório!")
	@Size(max = 20, message = "O atributo telefone deve ter no máximo 20 caracteres.")
	private String telefone;
	
	@NotBlank(message = "O atributo senha é obrigatório!")
	@Size(min = 12, message = "O atributo senha deve ter no minimo 12 caracteres.")
	private String senha;
	
	@Size(max = 5000, message = "O link da foto não pode ser maior do que 5000 caracteres")
	private String foto;
	
	@NotBlank(message = "O tipo do usuário é obrigatório!")
	@Size(min = 3, max = 50, message = "O tipo deve ter no minimo 3 e no máximo 50 caracteres.")
	private String tipo;	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties(value = "usuario", allowSetters = true)
	private List<Viagem> viagem;
	
	public Long getId() {
		return id;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Viagem> getViagem() {
		return viagem;
	}

	public void setViagem(List<Viagem> viagem) {
		this.viagem = viagem;
	}	
	
}
