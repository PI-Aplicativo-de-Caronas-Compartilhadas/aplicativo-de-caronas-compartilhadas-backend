package com.generation.rachou.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.rachou.model.Modalidade;
import com.generation.rachou.repository.ModalidadeRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/modalidades")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ModalidadeController {

	@Autowired
	private ModalidadeRepository modalidadeRepository;

	// 1 Listar Todas as Modalidades
	@GetMapping
	public ResponseEntity<List<Modalidade>> getAll() {
		return ResponseEntity.ok(modalidadeRepository.findAll());
	}

	// 2 Buscar por ID
	@GetMapping("/{id}")
	public ResponseEntity<Modalidade> getById(@PathVariable Long id) {
		return modalidadeRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	// 3 Buscar por Nome 
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Modalidade>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(modalidadeRepository.findAllByNomeContainingIgnoreCase(nome));
	}

	// 4 Cadastrar Modalidade
	@PostMapping
	public ResponseEntity<Modalidade> post(@Valid @RequestBody Modalidade modalidade) {
		return ResponseEntity.status(HttpStatus.CREATED).body(modalidadeRepository.save(modalidade));
	}

	// 5 Atualizar Modalidade
	@PutMapping
	public ResponseEntity<Modalidade> put(@Valid @RequestBody Modalidade modalidade) {
		if (modalidadeRepository.existsById(modalidade.getId())) {
			return ResponseEntity.status(HttpStatus.OK).body(modalidadeRepository.save(modalidade));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	// 6 Deletar Modalidade
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Modalidade> modalidade = modalidadeRepository.findById(id);
		if (modalidade.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		modalidadeRepository.deleteById(id);
	}
}