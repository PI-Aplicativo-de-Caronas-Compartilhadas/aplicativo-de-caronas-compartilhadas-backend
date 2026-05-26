package com.generation.rachou.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "tb_usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "O atributo nome é obrigatório!")
	@Size(min = 3, max = 255, message = "O atributo nome deve ter no minimo 3 e no máximo 255 caracteres.")
	private String nome;
	
	@Column(unique = true)
	@NotBlank(message = "O atributo email é obrigatório!")
	@Size(max = 50, message = "O atributo nome deve ter no máximo 50 caracteres.")
	private String email;
	
	@Column(unique = true)
	@NotBlank(message = "O atributo telefone é obrigatório!")
	@Size(max = 20, message = "O atributo telefone deve ter no máximo 20 caracteres.")
	private String telefone;
	
	@NotBlank(message = "O atributo senha é obrigatório!")
	@Size(min = 12, max = 50, message = "O atributo senha deve ter no minimo 12 e no máximo 50 caracteres.")
	private String senha;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
	
	
}
