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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.rachou.model.Modalidade;
import com.generation.rachou.model.Usuario;
import com.generation.rachou.model.Viagem;
import com.generation.rachou.repository.ModalidadeRepository;
import com.generation.rachou.repository.UsuarioRepository;
import com.generation.rachou.repository.ViagemRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/viagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ViagemController {

	@Autowired
	private ViagemRepository viagemRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ModalidadeRepository modalidadeRepository;

	@GetMapping
	public ResponseEntity<List<Viagem>> buscarTodas() {
		return ResponseEntity.ok(viagemRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Viagem> buscarPorId(@PathVariable Long id) {
		return viagemRepository.findById(id).map(rsp -> ResponseEntity.ok(rsp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("destino/{destino}")
	public ResponseEntity<List<Viagem>> buscarPorDestino(@PathVariable String destino) {
		return ResponseEntity.ok(viagemRepository.findAllByDestinoContainingIgnoreCase(destino));
	}

	@PostMapping
	public ResponseEntity<Viagem> cadastrarViagem(@Valid @RequestBody Viagem viagem) {

		viagem.setId(null);

		Usuario usuario = usuarioRepository.findById(viagem.getUsuario().getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

		Modalidade modalidade = modalidadeRepository.findById(viagem.getModalidade().getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Modalidade não encontrada"));

		viagem.setUsuario(usuario);
		viagem.setModalidade(modalidade);

		return ResponseEntity.status(HttpStatus.CREATED).body(viagemRepository.save(viagem));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void excluirViagem(@PathVariable Long id) {
		Optional<Viagem> viagem = viagemRepository.findById(id);

		if (viagem.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		viagemRepository.deleteById(id);
	}

}
