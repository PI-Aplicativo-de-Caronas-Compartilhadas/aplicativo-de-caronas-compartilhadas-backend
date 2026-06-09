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

import com.generation.rachou.model.Viagem;
import com.generation.rachou.repository.ViagemRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/viagem")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ViagemController {
	
	@Autowired
	private ViagemRepository viagemRepository;
	

	@GetMapping
	public ResponseEntity<List<Viagem>> buscarTodas(){
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

		return ResponseEntity.status(HttpStatus.CREATED).body(viagemRepository.save(viagem));
	}
	
	@PutMapping
	public ResponseEntity<Viagem> atualizarViagem(@Valid @RequestBody Viagem viagem) {

		if (viagemRepository.existsById(viagem.getId())) {
			return ResponseEntity.status(HttpStatus.OK).body(viagemRepository.save(viagem));
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void excluirViagem(@PathVariable Long id) {
		Optional<Viagem> viagem = viagemRepository.findById(id);
		
		if(viagem.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		viagemRepository.deleteById(id);
	}

}
